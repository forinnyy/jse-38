package ru.forinnyy.tm.repository;

import lombok.NonNull;
import ru.forinnyy.tm.api.repository.IUserRepository;
import ru.forinnyy.tm.model.User;

public final class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public User findByLogin(@NonNull final String login) {
        return findAll()
                .stream()
                .filter(m -> login.equals(m.getLogin()))
                .findFirst().orElse(null);
    }

    @Override
    public User findByEmail(@NonNull final String email) {
        return findAll()
                .stream()
                .filter(m -> email.equals(m.getEmail()))
                .findFirst().orElse(null);
    }

    @NonNull
    @Override
    public Boolean isLoginExist(@NonNull final String login) {
        return findAll()
                .stream()
                .anyMatch(m -> login.equals(m.getLogin()));
    }

    @NonNull
    @Override
    public Boolean isEmailExist(@NonNull final String email) {
        return findAll()
                .stream()
                .anyMatch(m -> email.equals(m.getEmail()));
    }

}
