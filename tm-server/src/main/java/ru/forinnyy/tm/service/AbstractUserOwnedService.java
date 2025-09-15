package ru.forinnyy.tm.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.repository.IUserOwnedRepository;
import ru.forinnyy.tm.api.service.IConnectionService;
import ru.forinnyy.tm.api.service.IUserOwnedService;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.EntityNotFoundException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.field.IdEmptyException;
import ru.forinnyy.tm.exception.field.IndexIncorrectException;
import ru.forinnyy.tm.exception.field.UserIdEmptyException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.AbstractUserOwnedModel;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractUserOwnedService<M extends AbstractUserOwnedModel, R extends IUserOwnedRepository<M>>
        extends AbstractService<M, R>
        implements IUserOwnedService<M> {

    public AbstractUserOwnedService(@NonNull final IConnectionService connectionService) {
        super(connectionService);
    }

    @NonNull
    @Override
    @SneakyThrows
    public M add(final String userId, final M model) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (model == null) throw new EntityNotFoundException();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            return repository.add(userId, model);
        }
    }

    @Override
    @SneakyThrows
    public void clear(final String userId) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            repository.clear(userId);
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public List<M> findAll(final String userId) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            return repository.findAll(userId);
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public List<M> findAll(final String userId, final Comparator<M> comparator) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (comparator == null) {
            try (@NonNull final Connection connection = getConnection()) {
                @NonNull final R repository = getRepository(connection);
                return repository.findAll(userId);
            }
        } else {
            try (@NonNull final Connection connection = getConnection()) {
                @NonNull final R repository = getRepository(connection);
                return repository.findAll(userId, comparator);
            }
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public List<M> findAll(final String userId, final Sort sort) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (sort == null) return findAll(userId);
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            return repository.findAll(userId, sort.getComparator());
        }
    }

    @Override
    @SneakyThrows
    public M findOneById(final String userId, final String id) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            return repository.findOneById(userId, id);
        }
    }

    @Override
    @SneakyThrows
    public M findOneByIndex(final String userId, final Integer index) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null) throw new IndexIncorrectException();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            return repository.findOneByIndex(userId, index);
        }
    }

    @Override
    @SneakyThrows
    public int getSize(final String userId) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            return repository.getSize(userId);
        }
    }

    @Override
    @SneakyThrows
    public boolean existsById(final String userId, final String id) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            return repository.existsById(userId, id);
        }
    }

    @Override
    @SneakyThrows
    public M remove(final String userId, final M model) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (model == null) return null;
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            return repository.remove(userId, model);
        }
    }

    @Override
    @SneakyThrows
    public M removeById(final String userId, final String id) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            return repository.removeById(userId, id);
        }
    }

    @Override
    @SneakyThrows
    public M removeByIndex(final String userId, final Integer index) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null) throw new IndexIncorrectException();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            return repository.removeByIndex(userId, index);
        }
    }

    @Override
    @SneakyThrows
    public void removeAll(final String userId) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            repository.removeAll(userId);
        }
    }

}
