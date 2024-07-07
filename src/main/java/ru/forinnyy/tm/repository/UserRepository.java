package ru.forinnyy.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.api.repository.IUserRepository;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.util.HashUtil;

public final class UserRepository extends AbstractRepository<User> implements IUserRepository {

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
