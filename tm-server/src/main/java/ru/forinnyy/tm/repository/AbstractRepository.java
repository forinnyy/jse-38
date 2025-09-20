package ru.forinnyy.tm.repository;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.DBConstraints;
import ru.forinnyy.tm.api.repository.IRepository;
import ru.forinnyy.tm.comparator.CreatedComparator;
import ru.forinnyy.tm.comparator.StatusComparator;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.IndexIncorrectException;
import ru.forinnyy.tm.model.AbstractModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public abstract class AbstractRepository<M extends AbstractModel> implements IRepository<M> {

    @NonNull
    protected final Connection connection;

    public AbstractRepository(@NonNull final Connection connection) {
        this.connection = connection;
    }

    protected abstract String getTableName();

    @NonNull
    protected String getSortType(@NonNull final Comparator comparator) {
        if (comparator == CreatedComparator.INSTANCE) return DBConstraints.COLUMN_CREATED;
        if (comparator == StatusComparator.INSTANCE) return DBConstraints.COLUMN_STATUS;
        else return DBConstraints.COLUMN_NAME;
    }

    @NonNull
    public abstract M fetch(@NonNull final ResultSet row);

    @NonNull
    public abstract M add(@NonNull final M model);

    @NonNull
    @Override
    public Collection<M> add(@NonNull Collection<M> models) {
        models.forEach(this::add);
        return models;
    }

    @NonNull
    @Override
    @SneakyThrows
    public List<M> findAll() {
        @NonNull final List<M> result = new ArrayList<>();
        @NonNull final String sql = String.format("SELECT * FROM %s", getTableName());
        try (@NonNull final Statement statement = connection.createStatement()) {
            @NonNull final ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(fetch(resultSet));
            }
        }
        return result;
    }

    @NonNull
    @Override
    @SneakyThrows
    public List<M> findAll(@NonNull final Comparator<M> comparator) {
        @NonNull final List<M> result = new ArrayList<>();
        @NonNull String sql;
        if (comparator == null) sql = String.format("SELECT * FROM %s", getTableName());
        else sql = String.format("SELECT * FROM %s ORDER BY %s", getTableName(), getSortType(comparator));
        try (@NonNull final Statement statement = connection.createStatement()) {
            @NonNull final ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(fetch(resultSet));
            }
        }
        return result;
    }

    @Override
    public boolean existsById(final String id) {
        return findOneById(id) != null;
    }

    @Override
    @SneakyThrows
    public M findOneById(@NonNull final String id) {
        @NonNull final String sql = String.format("SELECT * FROM %s WHERE id = ? LIMIT 1", getTableName());
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            @NonNull final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) return null;
            return fetch(resultSet);
        }
    }

    @Override
    @SneakyThrows
    public M findOneByIndex(final Integer index) {
        if (index == null || index < 0) return null;

        final String orderBy = DBConstraints.COLUMN_CREATED;
        final String sql = String.format(
                "SELECT * FROM %s ORDER BY %s LIMIT 1 OFFSET ?",
                getTableName(), orderBy
        );

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, index);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return fetch(rs);
            }
        }
    }


    @Override
    @SneakyThrows
    public void clear() {
        @NonNull final String sql = String.format("DELETE FROM %s", getTableName());
        try (@NonNull final Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public M remove(@NonNull final M model) throws AbstractEntityException {
        @NonNull final String sql = String.format("DELETE FROM %s WHERE id = ?", getTableName());
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, model.getId());
            statement.executeUpdate();
        }
        return model;
    }

    @Override
    @SneakyThrows
    public M removeById(@NonNull final String id) {
        final M model = findOneById(id);
        return remove(model);
    }

    @Override
    @SneakyThrows
    public M removeByIndex(@NonNull final Integer index) {
        final M model = findOneByIndex(index);
        return remove(model);
    }

    @NonNull
    @Override
    public Collection<M> set(@NonNull Collection<M> models) {
        clear();
        return add(models);
    }

    @Override
    @SneakyThrows
    public int getSize() {
        @NonNull final String sql = String.format("SELECT COUNT(*) FROM %s", getTableName());
        try (@NonNull final Statement statement = connection.createStatement()) {
            @NonNull final ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt("count");
        }
    }

    @SneakyThrows
    public void removeAll(final Collection<M> collection) {
        for (M model: collection) {
            remove(model);
        }
    }

}
