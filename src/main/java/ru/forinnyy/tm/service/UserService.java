package ru.forinnyy.tm.service;

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

    @Override
    public User create(final String login, final String password) throws AbstractUserException, AbstractFieldException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(HashUtil.salt(password));
        user.setRole(Role.USUAL);
        return repository.add(user);
    }

    @Override
    public User create(final String login, final String password, final String email) throws AbstractUserException, AbstractFieldException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (isEmailExist(email)) throw new ExistsEmailException();
        final User user = create(login, password);
        user.setEmail(email);
        return user;
    }

    @Override
    public User create(final String login, final String password, final Role role) throws AbstractUserException, AbstractFieldException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (role == null) throw new RoleEmptyException();
        final User user = create(login, password);
        if (role != null) user.setRole(role);
        return user;
    }

    @Override
    public User findByLogin(final String login) throws AbstractFieldException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        final User user = repository.findByLogin(login);
        if (user == null) return null;
        return user;
    }

    @Override
    public User findByEmail(final String email) throws AbstractUserException {
        if (email == null || email.isEmpty()) throw new ExistsEmailException();
        final User user = repository.findByEmail(email);
        if (user == null) return null;
        return user;
    }

    @Override
    public User removeByLogin(final String login) throws AbstractFieldException, AbstractEntityException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        final User user = findByLogin(login);
        if (user == null) throw new UserNotFoundException();
        return this.removeOne(user);
    }

    @Override
    public User removeByEmail(final String email) throws AbstractUserException, AbstractFieldException, AbstractEntityException {
        if (email == null || email.isEmpty()) throw new EmailEmptyException();
        final User user = findByEmail(email);
        if (user == null) throw new UserNotFoundException();
        return this.removeOne(user);
    }

    public User removeOne(final User model) throws AbstractEntityException, AbstractFieldException {
        if (model == null) return null;
        final User user = super.removeOne(model);
        if (user == null) return null;
        final String userId = user.getId();
        taskRepository.removeAll(userId);
        projectRepository.removeAll(userId);
        return user;
    }

    @Override
    public Boolean isLoginExist(final String login) {
        if (login == null || login.isEmpty()) return false;
        return repository.isLoginExist(login);
    }

    @Override
    public Boolean isEmailExist(final String email) {
        if (email == null || email.isEmpty()) return false;
        return repository.isEmailExist(email);
    }

    @Override
    public User setPassword(final String id, final String password) throws AbstractFieldException, AbstractEntityException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        final User user = findOneById(id);
        if (user == null) throw new UserNotFoundException();
        user.setPasswordHash(HashUtil.salt(password));
        return user;
    }

    @Override
    public User updateUser(
            final String id,
            final String firstName,
            final String lastName,
            final String middleName
    ) throws AbstractFieldException, AbstractEntityException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        final User user = findOneById(id);
        if (user == null) throw new UserNotFoundException();
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
