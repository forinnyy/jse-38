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
import ru.forinnyy.tm.exception.entity.UserNotFoundException;
import ru.forinnyy.tm.exception.field.*;
import ru.forinnyy.tm.exception.user.ExistsEmailException;
import ru.forinnyy.tm.exception.user.ExistsLoginException;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.repository.ProjectRepository;
import ru.forinnyy.tm.repository.TaskRepository;
import ru.forinnyy.tm.repository.UserRepository;
import ru.forinnyy.tm.util.HashUtil;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;


public final class UserService extends AbstractService<User, IUserRepository>
        implements IUserService {

    @NonNull
    private final IPropertyService propertyService;

    public UserService(
            @NonNull final IPropertyService propertyService,
            @NonNull final IConnectionService connectionService
            ) {
        super(connectionService);
        this.propertyService = propertyService;
    }

    @NonNull
    public IUserRepository getRepository(@NonNull final Connection connection) {
        return new UserRepository(connection);
    }

    @NonNull
    public ITaskRepository getTaskRepository(@NonNull final Connection connection) {
        return new TaskRepository(connection);
    }

    @NonNull
    public IProjectRepository getProjectRepository(@NonNull final Connection connection) {
        return new ProjectRepository(connection);
    }

    @Override
    @SneakyThrows
    public void initTable() {
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final IUserRepository repository = getRepository(connection);
            repository.initTable();
            connection.commit();
        }
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

        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final IUserRepository repository = getRepository(connection);
            @NonNull final User createdUser = repository.add(user);
            connection.commit();
            return createdUser;
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public User create(final String login, final String password, final String email) {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (isEmailExist(email)) throw new ExistsEmailException();

        @NonNull final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(HashUtil.salt(propertyService, password));
        user.setEmail(email);
        user.setRole(Role.USUAL);

        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final IUserRepository repository = getRepository(connection);
            @NonNull final User createdUser = repository.add(user);
            connection.commit();
            return createdUser;
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public User create(final String login, final String password, final Role role) {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (role == null) throw new RoleEmptyException();

        @NonNull final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(HashUtil.salt(propertyService, password));
        user.setRole(role);

        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final IUserRepository repository = getRepository(connection);
            @NonNull final User createdUser = repository.add(user);
            connection.commit();
            return createdUser;
        }
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
    @SneakyThrows
    public User removeByLogin(final String login) {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        final User user = findByLogin(login);
        return this.remove(user);
    }

    @NonNull
    @Override
    @SneakyThrows
    public User removeByEmail(final String email) {
        if (email == null || email.isEmpty()) throw new EmailEmptyException();
        final User user = findByEmail(email);
        return this.remove(user);
    }

    @NonNull
    @SneakyThrows
    public User remove(@NonNull final User model) {
        @NonNull final User user = super.remove(model);
        @NonNull final String userId = user.getId();

        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final ITaskRepository taskRepository = getTaskRepository(connection);
            @NonNull final IProjectRepository projectRepository = getProjectRepository(connection);

            taskRepository.removeAll(userId);
            projectRepository.removeAll(userId);

            connection.commit();
            return user;
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public Boolean isLoginExist(final String login) {
        if (login == null || login.isEmpty()) return false;
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final IUserRepository repository = getRepository(connection);
            return repository.isLoginExist(login);
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public Boolean isEmailExist(final String email) {
        if (email == null || email.isEmpty()) return false;
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final IUserRepository repository = getRepository(connection);
            return repository.isEmailExist(email);
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public User setPassword(@NonNull final String id, @NonNull final String password) {
        if (password.isEmpty()) throw new PasswordEmptyException();

        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final IUserRepository repository = getRepository(connection);
            final User user = repository.findOneById(id);
            if (user == null) throw new UserNotFoundException(); // Нужно добавить это исключение

            user.setPasswordHash(HashUtil.salt(propertyService, password));
            repository.update(user);

            connection.commit();
            return user;
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public User updateUser(
            @NonNull final String id,
            @NonNull final String firstName,
            @NonNull final String lastName,
            @NonNull final String middleName
    ) {
        if (id.isEmpty()) throw new IdEmptyException();

        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final IUserRepository repository = getRepository(connection);
            final User user = repository.findOneById(id);
            if (user == null) throw new UserNotFoundException();

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setMiddleName(middleName);
            repository.update(user);

            connection.commit();
            return user;
        }
    }

    @Override
    @SneakyThrows
    public void lockUserByLogin(final String login) {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();

        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final IUserRepository repository = getRepository(connection);
            final User user = repository.findByLogin(login);
            if (user == null) throw new UserNotFoundException();

            user.setLocked(true);
            repository.update(user);

            connection.commit();
        }
    }

    @Override
    @SneakyThrows
    public void unlockUserByLogin(final String login) {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();

        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final IUserRepository repository = getRepository(connection);
            final User user = repository.findByLogin(login);
            if (user == null) throw new UserNotFoundException();

            user.setLocked(false);
            repository.update(user);

            connection.commit();
        }
    }

    @NonNull
    @Override
    public List<String> listProfiles() {
        return findAll().stream().map(User::getLogin).collect(Collectors.toList());
    }

}
