package ru.forinnyy.tm.repository;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.DBConstraints;
import ru.forinnyy.tm.api.repository.ISessionRepository;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.model.Session;

import java.sql.*;
import java.util.Date;

public final class SessionRepository extends AbstractUserOwnedRepository<Session> implements ISessionRepository {

    public SessionRepository(@NonNull final Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return DBConstraints.TABLE_SESSION;
    }

    @Override
    @SneakyThrows
    public void initTable() {
        @NonNull final String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s (" +
                        "%s VARCHAR(36) PRIMARY KEY, " +
                        "%s VARCHAR(36) NOT NULL, " +
                        "%s TIMESTAMP NOT NULL, " +
                        "%s VARCHAR(32), " +
                        "FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE" +
                        ")",
                getTableName(),
                DBConstraints.COLUMN_ID,
                DBConstraints.COLUMN_USER_ID,
                DBConstraints.COLUMN_DATE,
                DBConstraints.COLUMN_ROLE,
                DBConstraints.COLUMN_USER_ID,
                DBConstraints.TABLE_USER,
                DBConstraints.COLUMN_ID
        );
        try (@NonNull final Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }


    @NonNull
    @Override
    @SneakyThrows
    public Session fetch(@NonNull final ResultSet row) {
        @NonNull final Session session = new Session();
        session.setId(row.getString(DBConstraints.COLUMN_ID));
        session.setUserId(row.getString(DBConstraints.COLUMN_USER_ID));
        session.setDate(row.getTimestamp(DBConstraints.COLUMN_DATE));

        final String roleString = row.getString(DBConstraints.COLUMN_ROLE);
        if (roleString != null) {
            session.setRole(Role.valueOf(roleString));
        }

        return session;
    }

    @NonNull
    @Override
    @SneakyThrows
    public Session add(@NonNull final Session session) {
        @NonNull final String sql = String.format(
                "INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
                getTableName(),
                DBConstraints.COLUMN_ID,
                DBConstraints.COLUMN_USER_ID,
                DBConstraints.COLUMN_DATE,
                DBConstraints.COLUMN_ROLE
        );
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, session.getId());
            statement.setString(2, session.getUserId());
            statement.setTimestamp(3, new Timestamp(session.getDate().getTime()));

            if (session.getRole() != null) {
                statement.setString(4, session.getRole().toString());
            } else {
                statement.setNull(4, java.sql.Types.VARCHAR);
            }

            statement.executeUpdate();
        }
        return session;
    }

    @NonNull
    @Override
    public Session add(@NonNull final String userId, @NonNull final Session session) {
        session.setUserId(userId);
        return add(session);
    }

    @Override
    @SneakyThrows
    public Session findOneById(@NonNull final String id) {
        @NonNull final String sql = String.format(
                "SELECT * FROM %s WHERE %s = ? LIMIT 1",
                getTableName(),
                DBConstraints.COLUMN_ID
        );
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            @NonNull final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) return null;
            return fetch(resultSet);
        }
    }

    @Override
    @SneakyThrows
    public void removeExpiredSessions(@NonNull final Date currentDate) {
        @NonNull final String sql = String.format(
                "DELETE FROM %s WHERE %s < ?",
                getTableName(),
                DBConstraints.COLUMN_DATE
        );
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setTimestamp(1, new Timestamp(currentDate.getTime()));
            statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public void removeAllByUserId(@NonNull final String userId) {
        @NonNull final String sql = String.format(
                "DELETE FROM %s WHERE %s = ?",
                getTableName(),
                DBConstraints.COLUMN_USER_ID
        );
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            statement.executeUpdate();
        }
    }

}