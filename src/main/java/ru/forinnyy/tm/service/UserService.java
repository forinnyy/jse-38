package ru.forinnyy.tm.service;

import lombok.NonNull;
import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.api.repository.IUserRepository;
import ru.forinnyy.tm.api.service.IPropertyService;
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

    @NonNull
    private final IProjectRepository projectRepository;

    @NonNull
    private final ITaskRepository taskRepository;

    @NonNull
    private final IPropertyService propertyService;

    public UserService(
            @NonNull final IPropertyService propertyService,
            @NonNull final IUserRepository userRepository,
            @NonNull final IProjectRepository projectRepository,
            @NonNull final ITaskRepository taskRepository
            ) {
        super(userRepository);
        this.propertyService = propertyService;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;

    }

    @NonNull
    @Override
    public User create(final String login, final String password) throws AbstractUserException, AbstractFieldException, AbstractEntityException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        @NonNull final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(HashUtil.salt(propertyService, password));
        user.setRole(Role.USUAL);
        return repository.add(user);
    }

    @NonNull
    @Override
    public User create(final String login, final String password, final String email) throws AbstractUserException, AbstractFieldException, AbstractEntityException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (isEmailExist(email)) throw new ExistsEmailException();
        @NonNull final User user = create(login, password);
        user.setEmail(email);
        return user;
    }

    @NonNull
    @Override
    public User create(final String login, final String password, final Role role) throws AbstractUserException, AbstractFieldException, AbstractEntityException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (role == null) throw new RoleEmptyException();
        @NonNull final User user = create(login, password);
        user.setRole(role);
        return user;
    }

    @NonNull
    @Override
    public User findByLogin(final String login) throws AbstractFieldException, AbstractEntityException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        final User user = repository.findByLogin(login);
        if (user == null) throw new UserNotFoundException();
        return user;
    }

    @NonNull
    @Override
    public User findByEmail(final String email) throws AbstractUserException, AbstractEntityException {
        if (email == null || email.isEmpty()) throw new ExistsEmailException();
        final User user = repository.findByEmail(email);
        if (user == null) throw new UserNotFoundException();
        return user;
    }

    @NonNull
    @Override
    public User removeByLogin(final String login) throws AbstractFieldException, AbstractEntityException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        final User user = findByLogin(login);
        return this.removeOne(user);
    }

    @NonNull
    @Override
    public User removeByEmail(final String email) throws AbstractUserException, AbstractFieldException, AbstractEntityException {
        if (email == null || email.isEmpty()) throw new EmailEmptyException();
        final User user = findByEmail(email);
        return this.removeOne(user);
    }

    @NonNull
    public User removeOne(@NonNull final User model) throws AbstractEntityException, AbstractFieldException {
        @NonNull final User user = super.removeOne(model);
        @NonNull final String userId = user.getId();
        taskRepository.removeAll(userId);
        projectRepository.removeAll(userId);
        return user;
    }

    @NonNull
    @Override
    public Boolean isLoginExist(final String login) {
        if (login == null || login.isEmpty()) return false;
        return repository.isLoginExist(login);
    }

    @NonNull
    @Override
    public Boolean isEmailExist(final String email) {
        if (email == null || email.isEmpty()) return false;
        return repository.isEmailExist(email);
    }

    @NonNull
    @Override
    public User setPassword(@NonNull final String id, @NonNull final String password) throws AbstractFieldException, AbstractEntityException {
        if (password.isEmpty()) throw new PasswordEmptyException();
        final User user = findOneById(id);
        user.setPasswordHash(HashUtil.salt(propertyService, password));
        return user;
    }

    @NonNull
    @Override
    public User updateUser(
            @NonNull final String id,
            @NonNull final String firstName,
            @NonNull final String lastName,
            @NonNull final String middleName
    ) throws AbstractFieldException, AbstractEntityException {
        if (id.isEmpty()) throw new IdEmptyException();
        final User user = findOneById(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setMiddleName(middleName);
        return user;
    }

    @Override
    public void lockUserByLogin(final String login) throws AbstractFieldException, AbstractEntityException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        final User user = findByLogin(login);
        if (user == null) throw new UserNotFoundException();
        user.setLocked(true);
    }

    @Override
    public void unlockUserByLogin(final String login) throws AbstractFieldException, AbstractEntityException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        final User user = findByLogin(login);
        if (user == null) throw new UserNotFoundException();
        user.setLocked(false);
    }

}
