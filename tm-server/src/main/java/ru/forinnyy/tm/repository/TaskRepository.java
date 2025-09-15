package ru.forinnyy.tm.repository;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.DBConstraints;
import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public final class TaskRepository extends AbstractUserOwnedRepository<Task>
        implements ITaskRepository {

    public TaskRepository(@NonNull final Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return DBConstraints.TABLE_TASK;
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
            statement.setString(5, task.getProjectId());
            statement.executeUpdate();
        }
        return task;
    }

    @Override
    @SneakyThrows
    public void update(@NonNull final Task task) {
        @NonNull final String sql = String.format(
                "UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?",
                getTableName(),
                DBConstraints.COLUMN_NAME,
                DBConstraints.COLUMN_DESCRIPTION,
                DBConstraints.COLUMN_STATUS,
                DBConstraints.COLUMN_ID
        );
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setString(3, task.getStatus().toString());
            statement.setString(4, task.getId());
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
    public List<Task> findAllByProjectId(@NonNull final String userId, @NonNull final String projectId) {
        return findAll().stream()
                .filter(m -> userId.equals(m.getUserId()))
                .filter(m -> projectId.equals(m.getProjectId()))
                .collect(Collectors.toList());
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
