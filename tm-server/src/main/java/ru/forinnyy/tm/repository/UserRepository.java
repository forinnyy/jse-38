package ru.forinnyy.tm.repository;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.DBConstraints;
import ru.forinnyy.tm.api.repository.IUserRepository;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public final class UserRepository extends AbstractRepository<User> implements IUserRepository {

    public UserRepository(@NonNull final Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return DBConstraints.TABLE_USER;
    }

    @Override
    @SneakyThrows
    public void initTable() {
        @NonNull final String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s (" +
                        "%s VARCHAR(36) PRIMARY KEY, " +
                        "%s VARCHAR(255) NOT NULL UNIQUE, " +
                        "%s VARCHAR(255) NOT NULL, " +
                        "%s VARCHAR(255) UNIQUE, " +
                        "%s BOOLEAN NOT NULL DEFAULT FALSE, " +
                        "%s VARCHAR(255), " +
                        "%s VARCHAR(255), " +
                        "%s VARCHAR(255), " +
                        "%s VARCHAR(50) NOT NULL DEFAULT 'USUAL'" +
                        ")",
                getTableName(),
                DBConstraints.COLUMN_ID,
                DBConstraints.COLUMN_LOGIN,
                DBConstraints.COLUMN_PASSWORD,
                DBConstraints.COLUMN_EMAIL,
                DBConstraints.COLUMN_LOCKED,
                DBConstraints.COLUMN_FIRST_NAME,
                DBConstraints.COLUMN_LAST_NAME,
                DBConstraints.COLUMN_MIDDLE_NAME,
                DBConstraints.COLUMN_ROLE
        );
        try (@NonNull final Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }


    @NonNull
    @Override
    @SneakyThrows
    public User fetch(@NonNull final ResultSet row) {
        @NonNull final User user = new User();
        user.setId(row.getString(DBConstraints.COLUMN_ID));
        user.setLogin(row.getString(DBConstraints.COLUMN_LOGIN));
        user.setPasswordHash(row.getString(DBConstraints.COLUMN_PASSWORD));
        user.setEmail(row.getString(DBConstraints.COLUMN_EMAIL));
        user.setLocked(row.getBoolean(DBConstraints.COLUMN_LOCKED));
        user.setFirstName(row.getString(DBConstraints.COLUMN_FIRST_NAME));
        user.setLastName(row.getString(DBConstraints.COLUMN_LAST_NAME));
        user.setMiddleName(row.getString(DBConstraints.COLUMN_MIDDLE_NAME));

        final String roleStr = row.getString(DBConstraints.COLUMN_ROLE);
        if (roleStr != null && !roleStr.isEmpty()) {
            user.setRole(Role.valueOf(roleStr.toUpperCase(java.util.Locale.ROOT)));
        } else {
            user.setRole(Role.USUAL);
        }
        return user;
    }

    @NonNull
    @Override
    @SneakyThrows
    public User add(@NonNull final User user) {
        final String sql = String.format(
                "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                getTableName(),
                DBConstraints.COLUMN_ID,
                DBConstraints.COLUMN_LOGIN,
                DBConstraints.COLUMN_PASSWORD,
                DBConstraints.COLUMN_EMAIL,
                DBConstraints.COLUMN_LOCKED,
                DBConstraints.COLUMN_FIRST_NAME,
                DBConstraints.COLUMN_LAST_NAME,
                DBConstraints.COLUMN_MIDDLE_NAME,
                DBConstraints.COLUMN_ROLE
        );
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getId());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPasswordHash());

            final String email = user.getEmail();
            if (email == null || email.trim().isEmpty()) {
                statement.setNull(4, java.sql.Types.VARCHAR);
            } else {
                statement.setString(4, email.trim());
            }

            statement.setBoolean(5, user.getLocked());
            statement.setString(6, user.getFirstName());
            statement.setString(7, user.getLastName());
            statement.setString(8, user.getMiddleName());
            statement.setString(9, user.getRole().toString());
            statement.executeUpdate();
        }
        return user;
    }

    @Override
    @SneakyThrows
    public void update(@NonNull final User user) {
        final String sql = String.format(
                "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
                getTableName(),
                DBConstraints.COLUMN_LOGIN,
                DBConstraints.COLUMN_PASSWORD,
                DBConstraints.COLUMN_EMAIL,
                DBConstraints.COLUMN_LOCKED,
                DBConstraints.COLUMN_FIRST_NAME,
                DBConstraints.COLUMN_LAST_NAME,
                DBConstraints.COLUMN_MIDDLE_NAME,
                DBConstraints.COLUMN_ROLE,
                DBConstraints.COLUMN_ID
        );
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPasswordHash());

            final String email = user.getEmail();
            if (email == null || email.trim().isEmpty()) {
                statement.setNull(3, java.sql.Types.VARCHAR);
            } else {
                statement.setString(3, email.trim());
            }

            statement.setBoolean(4, user.getLocked());
            statement.setString(5, user.getFirstName());
            statement.setString(6, user.getLastName());
            statement.setString(7, user.getMiddleName());
            statement.setString(8, user.getRole().toString());
            statement.setString(9, user.getId());
            statement.executeUpdate();
        }
    }


    @Override
    @SneakyThrows
    public User findByLogin(@NonNull final String login) {
        @NonNull final String sql = String.format("SELECT * FROM %s WHERE %s = ? LIMIT 1", getTableName(), DBConstraints.COLUMN_LOGIN);
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            @NonNull final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) return null;
            return fetch(resultSet);
        }
    }

    @Override
    @SneakyThrows
    public User findByEmail(@NonNull final String email) {
        @NonNull final String sql = String.format("SELECT * FROM %s WHERE %s = ? LIMIT 1", getTableName(), DBConstraints.COLUMN_EMAIL);
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            @NonNull final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) return null;
            return fetch(resultSet);
        }
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
