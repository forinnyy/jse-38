package ru.forinnyy.tm.service;

import lombok.NonNull;
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

    @NonNull
    public R getRepository(@NonNull Connection connection) {
        return new UserRepository(connection);
    }

    @NonNull
    @Override
    public Collection<M> add(@NonNull Collection<M> models) {
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final R repository = getRepository(connection);
            return repository.add(models);
        }
        return repository.add(models);
    }

    @NonNull
    @Override
    public Collection<M> set(@NonNull Collection<M> models) {
        return repository.set(models);
    }

    @Override
    public void clear() {
        repository.clear();
    }

    @NonNull
    @Override
    public List<M> findAll() {
        return repository.findAll();
    }

    @NonNull
    @Override
    public List<M> findAll(final Comparator<M> comparator) {
        if (comparator == null) return findAll();
        return repository.findAll(comparator);
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public List<M> findAll(final Sort sort) {
        if (sort == null) return findAll();
        return repository.findAll(sort.getComparator());
    }

    @NonNull
    @Override
    public M add(@NonNull final M model) throws AbstractEntityException {
        return repository.add(model);
    }

    @Override
    public boolean existsById(final String id) {
        if (id == null || id.isEmpty()) return false;
        return repository.existsById(id);
    }

    @NonNull
    @Override
    public M findOneById(final String id) throws AbstractFieldException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        return repository.findOneById(id);
    }
    
    @Override
    public M findOneByIndex(final Integer index) throws AbstractFieldException {
        if (index == null) throw new IndexIncorrectException();
        return repository.findOneByIndex(index);
    }

    @Override
    public int getSize() {
        return repository.getSize();
    }

    @NonNull
    @Override
    public M remove(final M model) throws AbstractEntityException {
        if (model == null) throw new EntityNotFoundException();
        return repository.remove(model);
    }

    @Override
    public M removeById(final String id) throws AbstractFieldException, AbstractEntityException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        return repository.removeById(id);
    }

    @Override
    public M removeByIndex(final Integer index) throws AbstractFieldException, AbstractEntityException {
        if (index == null) throw new IndexIncorrectException();
        return repository.removeByIndex(index);
    }

    @Override
    public void removeAll(final Collection<M> collection) {
        if (collection == null || collection.isEmpty()) return;
        repository.removeAll(collection);
    }

}
