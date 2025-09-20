package ru.forinnyy.tm.repository;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.DBConstraints;
import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class TaskRepository extends AbstractUserOwnedRepository<Task>
        implements ITaskRepository {

    public TaskRepository(@NonNull final Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return DBConstraints.TABLE_TASK;
    }

    @Override
    @SneakyThrows
    public void initTable() {
        @NonNull final String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s (" +
                        "%s VARCHAR(36) PRIMARY KEY, " +
                        "%s VARCHAR(255) NOT NULL, " +
                        "%s TIMESTAMP NOT NULL, " +
                        "%s TEXT, " +
                        "%s VARCHAR(36) NOT NULL, " +
                        "%s VARCHAR(32) NOT NULL, " +
                        "%s VARCHAR(36), " +
                        "FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE, " +
                        "FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE" +
                        ")",
                getTableName(),
                DBConstraints.COLUMN_ID,
                DBConstraints.COLUMN_NAME,
                DBConstraints.COLUMN_CREATED,
                DBConstraints.COLUMN_DESCRIPTION,
                DBConstraints.COLUMN_USER_ID,
                DBConstraints.COLUMN_STATUS,
                DBConstraints.COLUMN_PROJECT_ID,
                DBConstraints.COLUMN_USER_ID,
                DBConstraints.TABLE_USER,
                DBConstraints.COLUMN_ID,
                DBConstraints.COLUMN_PROJECT_ID,
                DBConstraints.TABLE_PROJECT,
                DBConstraints.COLUMN_ID
        );
        try (@NonNull final Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }


    @NonNull
    @Override
    @SneakyThrows
    public Task fetch(@NonNull final ResultSet row) {
        @NonNull final Task task = new Task();
        task.setId(row.getString(DBConstraints.COLUMN_ID));
        task.setName(row.getString(DBConstraints.COLUMN_NAME));
        task.setCreated(row.getTimestamp(DBConstraints.COLUMN_CREATED));
        task.setDescription(row.getString(DBConstraints.COLUMN_DESCRIPTION));
        task.setUserId(row.getString(DBConstraints.COLUMN_USER_ID));
        task.setStatus(Status.toStatus(row.getString(DBConstraints.COLUMN_STATUS)));
        task.setProjectId(row.getString(DBConstraints.COLUMN_PROJECT_ID));
        return task;
    }

    @NonNull
    @Override
    @SneakyThrows
    public Task add(@NonNull final Task task) {
        @NonNull final String sql = String.format(
                "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?)",
                getTableName(),
                DBConstraints.COLUMN_ID,
                DBConstraints.COLUMN_NAME,
                DBConstraints.COLUMN_CREATED,
                DBConstraints.COLUMN_DESCRIPTION,
                DBConstraints.COLUMN_USER_ID,
                DBConstraints.COLUMN_STATUS,
                DBConstraints.COLUMN_PROJECT_ID
        );
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, task.getId());
            statement.setString(2, task.getName());
            statement.setTimestamp(3, new Timestamp(task.getCreated().getTime()));
            statement.setString(4, task.getDescription());
            statement.setString(5, task.getUserId());
            statement.setString(6, task.getStatus().toString());
            statement.setString(7, task.getProjectId());
            statement.executeUpdate();
        }
        return task;
    }

    @Override
    @SneakyThrows
    public void update(@NonNull final Task task) {
        @NonNull final String sql = String.format(
                "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ? AND %s = ?",
                getTableName(),
                DBConstraints.COLUMN_NAME,
                DBConstraints.COLUMN_DESCRIPTION,
                DBConstraints.COLUMN_STATUS,
                DBConstraints.COLUMN_PROJECT_ID,
                DBConstraints.COLUMN_ID,
                DBConstraints.COLUMN_USER_ID
        );
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setString(3, task.getStatus().toString());
            statement.setString(4, task.getProjectId());
            statement.setString(5, task.getId());
            statement.setString(6, task.getUserId());
            statement.executeUpdate();
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public Task add(@NonNull final String userId, @NonNull final Task task) {
        task.setUserId(userId);
        return add(task);
    }

    @NonNull
    @Override
    @SneakyThrows
    public List<Task> findAllByProjectId(@NonNull final String userId, @NonNull final String projectId) {
        @NonNull final String sql = String.format(
                "SELECT * FROM %s WHERE %s = ? AND %s = ?",
                getTableName(),
                DBConstraints.COLUMN_USER_ID,
                DBConstraints.COLUMN_PROJECT_ID
        );

        @NonNull final List<Task> tasks = new ArrayList<>();
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            statement.setString(2, projectId);

            try (@NonNull final ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    @NonNull final Task task = fetch(resultSet);
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    @Override
    @SneakyThrows
    public void removeAllByProjectId(@NonNull final String userId, @NonNull final String projectId) {
        @NonNull final String sql = String.format(
                "DELETE FROM %s WHERE %s = ? AND %s = ?",
                getTableName(),
                DBConstraints.COLUMN_USER_ID,
                DBConstraints.COLUMN_PROJECT_ID
        );

        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            statement.setString(2, projectId);
            statement.executeUpdate();
        }
    }

    @NonNull
    @Override
    public Task create(@NonNull final String userId, @NonNull final String name) {
        @NonNull final Task task = new Task();
        task.setName(name);
        return add(userId, task);
    }

    @NonNull
    @Override
    public Task create(@NonNull final String userId, @NonNull final String name, @NonNull final String description) {
        @NonNull final Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        return add(userId, task);
    }

}
