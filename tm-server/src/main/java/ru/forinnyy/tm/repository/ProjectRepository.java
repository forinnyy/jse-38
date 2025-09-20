package ru.forinnyy.tm.repository;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.DBConstraints;
import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.model.Project;

import java.sql.*;


public final class ProjectRepository extends AbstractUserOwnedRepository<Project>
        implements IProjectRepository {

    public ProjectRepository(@NonNull final Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return DBConstraints.TABLE_PROJECT;
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
                        "FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE" +
                        ")",
                getTableName(),
                DBConstraints.COLUMN_ID,
                DBConstraints.COLUMN_NAME,
                DBConstraints.COLUMN_CREATED,
                DBConstraints.COLUMN_DESCRIPTION,
                DBConstraints.COLUMN_USER_ID,
                DBConstraints.COLUMN_STATUS,
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
    public Project fetch(@NonNull final ResultSet row) {
        @NonNull final Project project = new Project();
        project.setId(row.getString(DBConstraints.COLUMN_ID));
        project.setName(row.getString(DBConstraints.COLUMN_NAME));
        project.setCreated(row.getTimestamp(DBConstraints.COLUMN_CREATED));
        project.setDescription(row.getString(DBConstraints.COLUMN_DESCRIPTION));
        project.setUserId(row.getString(DBConstraints.COLUMN_USER_ID));
        project.setStatus(Status.toStatus(row.getString(DBConstraints.COLUMN_STATUS)));
        return project;
    }

    @NonNull
    @Override
    @SneakyThrows
    public Project add(@NonNull final Project project) {
        @NonNull final String sql = String.format(
                "INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?)",
                getTableName(),
                DBConstraints.COLUMN_ID,
                DBConstraints.COLUMN_NAME,
                DBConstraints.COLUMN_CREATED,
                DBConstraints.COLUMN_DESCRIPTION,
                DBConstraints.COLUMN_USER_ID,
                DBConstraints.COLUMN_STATUS
        );
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, project.getId());
            statement.setString(2, project.getName());
            statement.setTimestamp(3, new Timestamp(project.getCreated().getTime()));
            statement.setString(4, project.getDescription());
            statement.setString(5, project.getUserId());
            statement.setString(6, project.getStatus().toString());
            statement.executeUpdate();
        }
        return project;
    }

    @NonNull
    @Override
    public Project add(@NonNull final String userId, @NonNull final Project project) {
        project.setUserId(userId);
        return add(project);
    }

    @Override
    @SneakyThrows
    public void update(@NonNull final Project project) {
        @NonNull final String sql = String.format(
                "UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?",
                getTableName(),
                DBConstraints.COLUMN_NAME,
                DBConstraints.COLUMN_DESCRIPTION,
                DBConstraints.COLUMN_STATUS,
                DBConstraints.COLUMN_ID
        );
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setString(3, project.getStatus().toString());
            statement.setString(4, project.getId());
            statement.executeUpdate();
        }
    }

    @NonNull
    @Override
    public Project create(@NonNull final String userId, @NonNull final String name) {
        @NonNull final Project project = new Project();
        project.setName(name);
        project.setUserId(userId);
        return add(userId, project);
    }

    @NonNull
    @Override
    public Project create(@NonNull final String userId, @NonNull final String name, @NonNull final String description) {
        @NonNull final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        return add(userId, project);
    }

}
