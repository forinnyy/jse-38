package ru.forinnyy.tm.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.repository.IRepository;
import ru.forinnyy.tm.api.repository.IUserRepository;
import ru.forinnyy.tm.api.service.IConnectionService;
import ru.forinnyy.tm.api.service.IService;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.EntityNotFoundException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.field.IdEmptyException;
import ru.forinnyy.tm.exception.field.IndexIncorrectException;
import ru.forinnyy.tm.model.AbstractModel;
import ru.forinnyy.tm.repository.AbstractRepository;
import ru.forinnyy.tm.repository.UserRepository;

import java.sql.Connection;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractService<M extends AbstractModel, R extends IRepository<M>>
        implements IService<M> {

    @NonNull
    protected final IConnectionService connectionService;

    public AbstractService(@NonNull IConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @NonNull
    public Connection getConnection() {
        return connectionService.getConnection();
    }

    protected abstract R getRepository(@NonNull Connection connection);

    @NonNull
    @Override
    @SneakyThrows
    public M add(@NonNull final M model) throws AbstractEntityException {
        @NonNull final Connection connection = getConnection();
        try {
            @NonNull final R repository = getRepository(connection);
            return repository.add(model);
        } catch (@NonNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public Collection<M> add(@NonNull Collection<M> models) {
        @NonNull final Connection connection = getConnection();
        try {
            @NonNull final R repository = getRepository(connection);
            return repository.add(models);
        } catch (@NonNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public Collection<M> set(@NonNull Collection<M> models) {
        @NonNull final Connection connection = getConnection();
        try {
            @NonNull final R repository = getRepository(connection);
            return repository.set(models);
        } catch (@NonNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    @SneakyThrows
    public void clear() {
        @NonNull final Connection connection = getConnection();
        try {
            @NonNull final R repository = getRepository(connection);
            repository.clear();
        } catch (@NonNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public List<M> findAll() {
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            return repository.findAll();
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public List<M> findAll(final Comparator<M> comparator) {
        if (comparator == null) return findAll();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            return repository.findAll(comparator);
        }
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    @SneakyThrows
    public List<M> findAll(final Sort sort) {
        if (sort == null) return findAll();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            return repository.findAll(sort.getComparator());
        }
    }

    @Override
    @SneakyThrows
    public boolean existsById(final String id) {
        if (id == null || id.isEmpty()) return false;
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            return repository.existsById(id);
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public M findOneById(final String id) {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            return repository.findOneById(id);
        }
    }
    
    @Override
    @SneakyThrows
    public M findOneByIndex(final Integer index) {
        if (index == null) throw new IndexIncorrectException();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            return repository.findOneByIndex(index);
        }
    }

    @Override
    @SneakyThrows
    public int getSize() {
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            return repository.getSize();
        }
    }

    @Override
    @SneakyThrows
    public M remove(final M model) {
        if (model == null) throw new EntityNotFoundException();
        @NonNull final Connection connection = getConnection();
        try {
            @NonNull final R repository = getRepository(connection);
            return repository.remove(model);
        } catch (@NonNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    @SneakyThrows
    public M removeById(final String id) {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        @NonNull final Connection connection = getConnection();
        try {
            @NonNull final R repository = getRepository(connection);
            return repository.removeById(id);
        } catch (@NonNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    @SneakyThrows
    public M removeByIndex(final Integer index) {
        if (index == null) throw new IndexIncorrectException();
        @NonNull final Connection connection = getConnection();
        try {
            @NonNull final R repository = getRepository(connection);
            return repository.removeByIndex(index);
        } catch (@NonNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    @SneakyThrows
    public void removeAll(final Collection<M> collection) {
        if (collection == null || collection.isEmpty()) return;
        @NonNull final Connection connection = getConnection();
        try {
            @NonNull final R repository = getRepository(connection);
            repository.removeAll(collection);
        } catch (@NonNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

}
