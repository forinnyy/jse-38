package ru.forinnyy.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.api.repository.IRepository;
import ru.forinnyy.tm.api.service.IService;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.EntityNotFoundException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.field.IdEmptyException;
import ru.forinnyy.tm.exception.field.IndexIncorrectException;
import ru.forinnyy.tm.exception.field.UserIdEmptyException;
import ru.forinnyy.tm.model.AbstractModel;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractService<M extends AbstractModel, R extends IRepository<M>>
        implements IService<M> {

    protected final R repository;

    public AbstractService(final R repository) {
        this.repository = repository;
    }

    @Override
    public void clear() {
        repository.clear();
    }

    @NotNull
    @Override
    public List<M> findAll() {
        return repository.findAll();
    }

    @NotNull
    @Override
    public List<M> findAll(final Comparator<M> comparator) {
        System.out.println(comparator);
        if (comparator == null) return findAll();
        return repository.findAll(comparator);
    }

    @NotNull
    @Override
    public List<M> findAll(final Sort sort) {
        if (sort == null) return findAll();
        return repository.findAll(sort.getComparator());
    }

    @NotNull
    @Override
    public M add(final M model) {
        if (model == null) return null;
        return repository.add(model);
    }

    @Override
    public boolean existsById(final String id) {
        if (id == null || id.isEmpty()) return false;
        return repository.existsById(id);
    }

    @Override
    public M findOneById(final String id) throws AbstractFieldException {
        if (id == null || id.isEmpty()) return null;
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

    @Override
    public M removeOne(final M model) throws AbstractEntityException, AbstractFieldException {
        if (model == null) throw new EntityNotFoundException();
        return repository.remove(model);
    }

}
