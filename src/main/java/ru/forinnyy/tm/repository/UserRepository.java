package ru.forinnyy.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.api.repository.IUserRepository;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.util.HashUtil;

public final class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @NotNull
    @Override
    public User create(@NotNull final String login, @NotNull final String password) {
        @NotNull final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(HashUtil.salt(password));
        user.setRole(Role.USUAL);
        return add(user);
    }

    @NotNull
    @Override
    public User create(@NotNull final String login, @NotNull final String password, @NotNull final String email) {
        @NotNull final User user = create(login, password);
        user.setEmail(email);
        return user;
    }

    @NotNull
    @Override
    public User create(@NotNull final String login, @NotNull final String password, @NotNull final Role role) {
        @NotNull final User user = create(login, password);
        user.setRole(role);
        return user;
    }

    @Nullable
    @Override
    public User findByLogin(@NotNull final String login) {
        return findAll()
                .stream()
                .filter(m -> login.equals(m.getLogin()))
                .findFirst().orElse(null);
    }

    @Nullable
    @Override
    public User findByEmail(@NotNull final String email) {
        return findAll()
                .stream()
                .filter(m -> email.equals(m.getEmail()))
                .findFirst().orElse(null);
    }

    @NotNull
    @Override
    public Boolean isLoginExist(@NotNull final String login) {
        return findAll()
                .stream()
                .anyMatch(m -> login.equals(m.getLogin()));
    }

    @NotNull
    @Override
    public Boolean isEmailExist(@NotNull final String email) {
        return findAll()
                .stream()
                .anyMatch(m -> email.equals(m.getEmail()));
    }

}
