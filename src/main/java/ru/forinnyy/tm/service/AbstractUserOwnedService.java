package ru.forinnyy.tm.service;

import ru.forinnyy.tm.api.repository.IUserOwnedRepository;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.field.IdEmptyException;
import ru.forinnyy.tm.exception.field.IndexIncorrectException;
import ru.forinnyy.tm.model.AbstractUserOwnedModel;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractUserOwnedService<M extends AbstractUserOwnedModel, R extends IUserOwnedRepository<M>>
        extends AbstractService<M, R>
        implements IUserOwnedRepository<M> {

    public AbstractUserOwnedService(final R repository) {
        super(repository);
    }

    @Override
    public void clear(final String userId) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        repository.clear(userId);
    }

    @Override
    public List<M> findAll(final String userId) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        return repository.findAll(userId);
    }

    @Override
    public List<M> findAll(final String userId, final Comparator<M> comparator) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (comparator == null) return repository.findAll(userId);
        return repository.findAll(userId, comparator);
    }

    @Override
    public M add(final String userId, final M model) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (model == null) return null;
        return repository.add(userId, model);
    }

    @Override
    public boolean existsById(final String userId, final String id) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        return repository.existsById(userId, id);
    }

    @Override
    public M findOneById(final String userId, final String id) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        return repository.findOneById(userId, id);
    }

    @Override
    public M findOneByIndex(final String userId, final Integer index) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null) throw new IndexIncorrectException();
        return repository.findOneByIndex(userId, index);
    }

    @Override
    public int getSize(final String userId) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        return repository.getSize(userId);
    }

    @Override
    public M remove(final String userId, M model) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (model == null) return null;
        return repository.remove(userId, model);
    }

    @Override
    public M removeById(final String userId, final String id) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        return repository.removeById(userId, id);
    }

    @Override
    public M removeByIndex(final String userId, final Integer index) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null) throw new IndexIncorrectException();
        return repository.findOneByIndex(userId, index);
    }

    @Override
    public List<M> findAll(final String userId, final Sort sort) {
        if (userId == null || userId.isEmpty()) throw UserIdEmptyException();
        if (sort == null) return findAll(userId);
        return repository.findAll(userId, sort.getComparator());
    }

}
