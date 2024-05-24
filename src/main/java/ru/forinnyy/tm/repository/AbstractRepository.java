package ru.forinnyy.tm.repository;

import ru.forinnyy.tm.api.repository.IRepository;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.EntityNotFoundException;
import ru.forinnyy.tm.model.AbstractModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractRepository<M extends AbstractModel> implements IRepository<M> {

    protected final List<M> models = new ArrayList<>();

    @Override
    public void clear() {
        models.clear();
    }

    @Override
    public List<M> findAll() {
        return models;
    }

    @Override
    public List<M> findAll(Comparator<M> comparator) {
        final List<M> result = new ArrayList<>(models);
        result.sort(comparator);
        return result;
    }

    @Override
    public M add(final M model) {
        models.add(model);
        return model;
    }

    @Override
    public boolean existsById(final String id) {
        return findOneById(id) != null;
    }

    @Override
    public M findOneById(final String id) {
        for (final M model : models) {
            if (id.equals(model.getId())) return model;
        }
        return null;
    }

    @Override
    public M findOneByIndex(Integer index) {
        return models.get(index);
    }

    @Override
    public int getSize() {
        return models.size();
    }

    @Override
    public M remove(M model) throws AbstractEntityException {
        if (model == null) throw new EntityNotFoundException();
        models.remove(model);
        return model;
    }

    @Override
    public M removeById(String id) throws AbstractEntityException {
        final M model = findOneById(id);
        if (model == null) throw new EntityNotFoundException();
        models.remove(model);
        return model;
    }

    @Override
    public M removeByIndex(Integer index) throws AbstractEntityException {
        final M model = findOneByIndex(index);
        if (model == null) throw new EntityNotFoundException();
        models.remove(model);
        return model;
    }

    public void removeAll(final Collection<M> collection) {
        if (collection == null) return;
        models.removeAll(collection);
    }

}
