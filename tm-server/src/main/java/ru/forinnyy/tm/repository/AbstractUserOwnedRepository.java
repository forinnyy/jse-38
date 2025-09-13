package ru.forinnyy.tm.repository;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.DBConstraints;
import ru.forinnyy.tm.api.repository.IUserOwnedRepository;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.field.UserIdEmptyException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.AbstractUserOwnedModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractUserOwnedRepository<M extends AbstractUserOwnedModel>
        extends AbstractRepository<M>
        implements IUserOwnedRepository<M> {

    @NonNull
    private final String USER_ID_COLUMN = DBConstraints.COLUMN_USER_ID;

    public AbstractUserOwnedRepository(@NonNull Connection connection) {
        super(connection);
    }

    @NonNull
    @Override
    public abstract M add(@NonNull final String userId, @NonNull final M model);

    @Override
    @SneakyThrows
    public void clear(@NonNull final String userId) {
        @NonNull final String sql = String.format("DELETE FROM %s WHERE %s = ?", getTableName(), USER_ID_COLUMN);
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            statement.executeUpdate();
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public List<M> findAll(@NonNull final String userId) {
        @NonNull final List<M> result = new ArrayList<>();
        @NonNull final String sql = String.format("SELECT * FROM %s WHERE %s = ?", getTableName(), USER_ID_COLUMN);
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            @NonNull final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(fetch(resultSet));
            }
        }
        return result;
    }

    @NonNull
    @Override
    @SneakyThrows
    public List<M> findAll(@NonNull final String userId, final Comparator<M> comparator) {
        @NonNull final List<M> result = new ArrayList<>();
        @NonNull String sql = String.format("SELECT * FROM %s WHERE %s = ?", getTableName(), USER_ID_COLUMN);
        if (comparator != null) sql = String.format(sql + " ORDER BY %s", getSortType(comparator));
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            @NonNull final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(fetch(resultSet));
            }
        }
        return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    @SneakyThrows
    public List<M> findAll(@NonNull final String userId, @NonNull final Sort sort) {
        return findAll(userId, sort.getComparator());
    }

    @Override
    @SneakyThrows
    public boolean existsById(@NonNull final String userId, @NonNull final String id) {
        return findOneById(userId, id) != null;
    }

    @Override
    @SneakyThrows
    public M findOneById(@NonNull final String userId, @NonNull final String id) {
        @NonNull final String sql = String.format("SELECT * FROM %s WHERE id = ? AND %s = ?", getTableName(), USER_ID_COLUMN);
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.setString(2, userId);
            @NonNull final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) return null;
            return fetch(resultSet);
        }
    }
    
    @Override
    @SneakyThrows
    public M findOneByIndex(@NonNull final String userId, @NonNull final Integer index) {
        @NonNull final String sql = String.format("SELECT * FROM %s WHERE %s = ? LIMIT ?", getTableName(), USER_ID_COLUMN);
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            statement.setInt(2, index);
            @NonNull final ResultSet resultSet = statement.executeQuery();
            for (int i = 0; i < index - 1; i++) {
                resultSet.next();
            }
            return fetch(resultSet);
        }
    }

    @Override
    @SneakyThrows
    public int getSize(final String userId) {
        @NonNull final String sql = String.format("SELECT COUNT(*) FROM %s WHERE %s = ?", getTableName(), USER_ID_COLUMN);
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            @NonNull final ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("count");
        }
    }

    
    @Override
    @SneakyThrows
    public M remove(@NonNull final String userId, @NonNull final M model) {
        @NonNull final String sql = String.format("DELETE FROM %s WHERE id = ? AND %s = ?", getTableName(), USER_ID_COLUMN);
        try (@NonNull final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, model.getId());
            statement.setString(2, userId);
            statement.executeUpdate();
        }
        return model;
    }

    @Override
    @SneakyThrows
    public M removeById(@NonNull final String userId, @NonNull final String id) {
        final M model = findOneById(userId, id);
        return remove(model);
    }
    
    @Override
    @SneakyThrows
    public M removeByIndex(@NonNull final String userId, @NonNull final Integer index) {
        final M model = findOneByIndex(userId, index);
        return remove(model);
    }

    @Override
    public void removeAll(@NonNull final String userId) {
        @NonNull final List<M> list = findAll(userId);
        removeAll(list);
    }

}
