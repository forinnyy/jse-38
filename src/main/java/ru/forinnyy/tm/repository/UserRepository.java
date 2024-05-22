package ru.forinnyy.tm.repository;

import ru.forinnyy.tm.api.repository.IUserRepository;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.util.HashUtil;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public User create(String login, String password) {
        final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(HashUtil.salt(password));
        user.setRole(Role.USUAL);
        return add(user);
    }

    @Override
    public User create(String login, String password, String email) {
        final User user = create(login, password);
        user.setEmail(email);
        return user;
    }

    @Override
    public User create(String login, String password, Role role) {
        final User user = create(login, password);
        if (role != null) user.setRole(role);
        return user;
    }

    @Override
    public User findByLogin(final String login) {
        for (final User user : models) {
            if (login.equals(user.getLogin())) return user;
        }
        return null;
    }

    @Override
    public User findByEmail(final String email) {
        for (final User user : models) {
            if (email.equals(user.getEmail())) return user;
        }
        return null;
    }

    @Override
    public Boolean isLoginExist(final String login) {
        for (final User user : models) {
            if (login.equals(user.getLogin())) return true;
        }
        return false;
    }

    @Override
    public Boolean isEmailExist(final String email) {
        for (final User user : models) {
            if (email.equals(user.getEmail())) return true;
        }
        return false;
    }

}
