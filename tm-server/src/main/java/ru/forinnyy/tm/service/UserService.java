package ru.forinnyy.tm.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.api.repository.IUserRepository;
import ru.forinnyy.tm.api.service.IConnectionService;
import ru.forinnyy.tm.api.service.IPropertyService;
import ru.forinnyy.tm.api.service.IUserService;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.UserNotFoundException;
import ru.forinnyy.tm.exception.field.*;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.exception.user.ExistsEmailException;
import ru.forinnyy.tm.exception.user.ExistsLoginException;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.repository.UserRepository;
import ru.forinnyy.tm.util.HashUtil;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;


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
            @NonNull final IConnectionService connectionService,
            @NonNull final IProjectRepository projectRepository,
            @NonNull final ITaskRepository taskRepository
            ) {
        super(connectionService);
        this.propertyService = propertyService;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @NonNull
    public IUserRepository getRepository(@NonNull Connection connection) {
        return new UserRepository(connection);
    }

    @NonNull
    @Override
    @SneakyThrows
    public User create(final String login, final String password) {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        @NonNull User user = new User();
        user.setLogin(login);
        user.setPasswordHash(HashUtil.salt(propertyService, password));
        user.setRole(Role.USUAL);
        @NonNull final Connection connection = getConnection();
        try {
            @NonNull final IUserRepository repository = getRepository(connection);
            user = repository.add(user);
            connection.commit();
        } catch (@NonNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return user;
    }

    @NonNull
    @Override
    @SneakyThrows
    public User create(final String login, final String password, final String email) {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (isEmailExist(email)) throw new ExistsEmailException();
        @NonNull User user = create(login, password);
        user.setEmail(email);
        @NonNull final Connection connection = getConnection();
        try {
            @NonNull final IUserRepository repository = getRepository(connection);
            user = repository.add(user);
            connection.commit();
        } catch (@NonNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return user;
    }

    @NonNull
    @Override
    @SneakyThrows
    public User create(final String login, final String password, final Role role) {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (role == null) throw new RoleEmptyException();
        @NonNull User user = create(login, password);
        user.setRole(role);
        @NonNull final Connection connection = getConnection();
        try {
            @NonNull final IUserRepository repository = getRepository(connection);
            user = repository.add(user);
            connection.commit();
        } catch (@NonNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
        return user;
    }

    @NonNull
    @Override
    @SneakyThrows
    public User findByLogin(final String login) {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final IUserRepository repository = getRepository(connection);
            return repository.findByLogin(login);
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public User findByEmail(final String email) {
        if (email == null || email.isEmpty()) throw new ExistsEmailException();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final IUserRepository repository = getRepository(connection);
            return repository.findByEmail(email);
        }
    }

    @NonNull
    @Override
    public User removeByLogin(final String login) throws AbstractFieldException, AbstractEntityException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        final User user = findByLogin(login);
        return this.remove(user);
    }

    @NonNull
    @Override
    public User removeByEmail(final String email) throws AbstractUserException, AbstractFieldException, AbstractEntityException {
        if (email == null || email.isEmpty()) throw new EmailEmptyException();
        final User user = findByEmail(email);
        return this.remove(user);
    }

    @NonNull
    @SneakyThrows
    public User remove(@NonNull final User model) throws AbstractEntityException {
        @NonNull final User user = super.remove(model);
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
        user.setLocked(true);
    }

    @Override
    public void unlockUserByLogin(final String login) throws AbstractFieldException, AbstractEntityException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        final User user = findByLogin(login);
        user.setLocked(false);
    }

    @NonNull
    @Override
    public List<String> listProfiles() {
        return findAll().stream().map(User::getLogin).collect(Collectors.toList());
    }

}
