package ru.forinnyy.tm.repository;

import ru.forinnyy.tm.api.repository.IUserRepository;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.util.HashUtil;

public final class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public User create(final String login, final String password) {
        final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(HashUtil.salt(password));
        user.setRole(Role.USUAL);
        return add(user);
    }

    @Override
    public User create(final String login, final String password, final String email) {
        final User user = create(login, password);
        user.setEmail(email);
        return user;
    }

    @Override
    public User create(final String login, final String password, final Role role) {
        final User user = create(login, password);
        if (role != null) user.setRole(role);
        return user;
    }

    @Override
    public User findByLogin(final String login) {
        return findAll()
                .stream()
                .filter(m -> login.equals(m.getLogin()))
                .findFirst().orElse(null);
    }

    @Override
    public User findByEmail(final String email) {
        return findAll()
                .stream()
                .filter(m -> email.equals(m.getEmail()))
                .findFirst().orElse(null);
    }

    @Override
    public Boolean isLoginExist(final String login) {
        return findAll()
                .stream()
                .anyMatch(m -> login.equals(m.getLogin()));
    }

    @Override
    public Boolean isEmailExist(final String email) {
        return findAll()
                .stream()
                .anyMatch(m -> email.equals(m.getEmail()));
    }

}
