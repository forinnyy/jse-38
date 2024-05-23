package ru.forinnyy.tm.repository;

import ru.forinnyy.tm.api.repository.IRepository;
import ru.forinnyy.tm.model.AbstractModel;

import java.util.ArrayList;
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
    public M remove(M model) {
        models.remove(model);
        return model;
    }

    @Override
    public M removeById(String id) {
        final M model = findOneById(id);
        models.remove(model);
        return model;
    }

    @Override
    public M removeByIndex(Integer index) {
        final M model = findOneByIndex(index);
        if (model == null) return null;
        models.remove(model);
        return model;
    }

}
