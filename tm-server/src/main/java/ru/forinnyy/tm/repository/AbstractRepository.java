package ru.forinnyy.tm.repository;

import lombok.NonNull;
import ru.forinnyy.tm.api.repository.IRepository;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.model.AbstractModel;

import java.util.*;

public abstract class AbstractRepository<M extends AbstractModel> implements IRepository<M> {

    @NonNull
    private final Map<String, M> models = new LinkedHashMap<>();

    @Override
    public void clear() {
        models.clear();
    }

    @NonNull
    @Override
    public List<M> findAll() {
        return new ArrayList<>(models.values());
    }

    @NonNull
    @Override
    public List<M> findAll(@NonNull final Comparator<M> comparator) {
        @NonNull final List<M> result = new ArrayList<>(models.values());
        result.sort(comparator);
        return result;
    }

    @NonNull
    @Override
    public M add(@NonNull final M model) {
        models.put(model.getId(), model);
        return model;
    }

    @NonNull
    @Override
    public Collection<M> add(@NonNull Collection<M> models) {
        models.forEach(this::add);
        return models;
    }

    @NonNull
    @Override
    public Collection<M> set(@NonNull Collection<M> models) {
        clear();
        return add(models);
    }

    @Override
    public boolean existsById(final String id) {
        return findOneById(id) != null;
    }

    @NonNull
    @Override
    public M findOneById(@NonNull final String id) {
        return models.get(id);
    }

    @Override
    public M findOneByIndex(Integer index) {
        if (index == null) return null;
        @NonNull final List<String> list = new LinkedList<>(models.keySet());
        final String key = list.get(index);
        return models.get(key);
    }

    @Override
    public int getSize() {
        return models.size();
    }

    @NonNull
    @Override
    public M remove(@NonNull final M model) throws AbstractEntityException {
        models.remove(model.getId());
        return model;
    }

    @Override
    public M removeById(@NonNull final String id) throws AbstractEntityException {
        final M model = findOneById(id);
        return remove(model);
    }
    
    @Override
    public M removeByIndex(@NonNull final Integer index) throws AbstractEntityException {
        final M model = findOneByIndex(index);
        return remove(model);
    }

    public void removeAll(final Collection<M> collection) {
        if (collection == null) return;
        collection.stream().map(AbstractModel::getId).forEach(models::remove);
    }

}
