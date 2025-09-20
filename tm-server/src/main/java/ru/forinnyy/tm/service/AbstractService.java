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
    public M add(@NonNull M model) {
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            final M result = repository.add(model);
            connection.commit();
            return result;
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public Collection<M> add(@NonNull Collection<M> models) {
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            final Collection<M> result = repository.add(models);
            connection.commit();
            return result;
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public Collection<M> set(@NonNull Collection<M> models) {
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            final Collection<M> result = repository.set(models);
            connection.commit();
            return result;
        }
    }

    @Override
    @SneakyThrows
    public void clear() {
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            repository.clear();
            connection.commit();
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
        if (index == null || index < 0) throw new IndexIncorrectException();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            final int size = repository.getSize();
            if (index > size) throw new IndexIncorrectException();
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
    public M remove(M model) {
        if (model == null) throw new EntityNotFoundException();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            final M result = repository.remove(model);
            connection.commit();
            return result;
        }
    }

    @Override
    @SneakyThrows
    public M removeById(final String id) {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            M model = repository.removeById(id);
            connection.commit();
            return model;
        }
    }

    @Override
    @SneakyThrows
    public M removeByIndex(final Integer index) {
        if (index == null) throw new IndexIncorrectException();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            final M result = repository.removeByIndex(index);
            connection.commit();
            return result;
        }
    }

    @Override
    @SneakyThrows
    public void removeAll(final Collection<M> collection) {
        if (collection == null || collection.isEmpty()) return;
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            repository.removeAll(collection);
            connection.commit();
        }
    }

}
