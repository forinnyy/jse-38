package ru.forinnyy.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.api.repository.IUserRepository;
import ru.forinnyy.tm.api.service.IUserService;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.*;
import ru.forinnyy.tm.exception.field.*;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.exception.user.ExistsEmailException;
import ru.forinnyy.tm.exception.user.ExistsLoginException;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.util.HashUtil;


public final class UserService extends AbstractService<User, IUserRepository>
        implements IUserService {

    private final IProjectRepository projectRepository;

    private final ITaskRepository taskRepository;

    public UserService(
            final IUserRepository userRepository,
            final IProjectRepository projectRepository,
            final ITaskRepository taskRepository
            ) {
        super(userRepository);
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @NotNull
    @Override
    public User create(@Nullable final String login, @Nullable final String password) throws AbstractUserException, AbstractFieldException, AbstractEntityException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        @NotNull final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(HashUtil.salt(password));
        user.setRole(Role.USUAL);
        return repository.add(user);
    }

    @NotNull
    @Override
    public User create(@Nullable final String login, @Nullable final String password, @Nullable final String email) throws AbstractUserException, AbstractFieldException, AbstractEntityException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (isEmailExist(email)) throw new ExistsEmailException();
        @NotNull final User user = create(login, password);
        user.setEmail(email);
        return user;
    }

    @NotNull
    @Override
    public User create(@Nullable final String login, @Nullable final String password, @Nullable final Role role) throws AbstractUserException, AbstractFieldException, AbstractEntityException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (role == null) throw new RoleEmptyException();
        @NotNull final User user = create(login, password);
        user.setRole(role);
        return user;
    }

    @Nullable
    @Override
    public User findByLogin(@Nullable final String login) throws AbstractFieldException, AbstractEntityException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @Nullable final User user = repository.findByLogin(login);
        if (user == null) throw new UserNotFoundException();
        return user;
    }

    @Nullable
    @Override
    public User findByEmail(@Nullable final String email) throws AbstractUserException, AbstractEntityException {
        if (email == null || email.isEmpty()) throw new ExistsEmailException();
        @Nullable final User user = repository.findByEmail(email);
        if (user == null) throw new UserNotFoundException();
        return user;
    }

    @Override
    public User removeByLogin(@Nullable final String login) throws AbstractFieldException, AbstractEntityException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        @Nullable final User user = findByLogin(login);
        if (user == null) throw new UserNotFoundException();
        return this.removeOne(user);
    }

    @Nullable
    @Override
    public User removeByEmail(@Nullable final String email) throws AbstractUserException, AbstractFieldException, AbstractEntityException {
        if (email == null || email.isEmpty()) throw new EmailEmptyException();
        @Nullable final User user = findByEmail(email);
        if (user == null) throw new UserNotFoundException();
        return this.removeOne(user);
    }

    @Nullable
    public User removeOne(@Nullable final User model) throws AbstractEntityException, AbstractFieldException {
        if (model == null) return null;
        @Nullable final User user = super.removeOne(model);
        if (user == null) return null;
        @NotNull final String userId = user.getId();
        taskRepository.removeAll(userId);
        projectRepository.removeAll(userId);
        return user;
    }

    @NotNull
    @Override
    public Boolean isLoginExist(@Nullable final String login) {
        if (login == null || login.isEmpty()) return false;
        return repository.isLoginExist(login);
    }

    @NotNull
    @Override
    public Boolean isEmailExist(@Nullable final String email) {
        if (email == null || email.isEmpty()) return false;
        return repository.isEmailExist(email);
    }

    @NotNull
    @Override
    public User setPassword(@Nullable final String id, @Nullable final String password) throws AbstractFieldException, AbstractEntityException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        @Nullable final User user = findOneById(id);
        if (user == null) throw new UserNotFoundException();
        user.setPasswordHash(HashUtil.salt(password));
        return user;
    }

    @NotNull
    @Override
    public User updateUser(
            @Nullable final String id,
            @Nullable final String firstName,
            @Nullable final String lastName,
            @Nullable final String middleName
    ) throws AbstractFieldException, AbstractEntityException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @Nullable final User user = findOneById(id);
        if (user == null) throw new UserNotFoundException();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setMiddleName(middleName);
        return user;
    }

    @Override
    public void lockUserByLogin(@Nullable final String login) throws AbstractFieldException, AbstractEntityException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        final User user = findByLogin(login);
        if (user == null) throw new UserNotFoundException();
        user.setLocked(true);
    }

    @Override
    public void unlockUserByLogin(@Nullable final String login) throws AbstractFieldException, AbstractEntityException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        final User user = findByLogin(login);
        if (user == null) throw new UserNotFoundException();
        user.setLocked(false);
    }

}
