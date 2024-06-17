package ru.forinnyy.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.api.repository.IRepository;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.EntityNotFoundException;
import ru.forinnyy.tm.model.AbstractModel;

import java.util.*;

public abstract class AbstractRepository<M extends AbstractModel> implements IRepository<M> {

    @NotNull
    private final Map<String, M> models = new LinkedHashMap<>();

    @Override
    public void clear() {
        models.clear();
    }

    @NotNull
    @Override
    public List<M> findAll() {
        return new ArrayList<>(models.values());
    }

    @NotNull
    @Override
    public List<M> findAll(Comparator<M> comparator) {
        final List<M> result = new ArrayList<>(models.values());
        result.sort(comparator);
        return result;
    }

    @NotNull
    @Override
    public M add(@NotNull final M model) {
        models.put(model.getId(), model);
        return model;
    }

    @Override
    public boolean existsById(@Nullable final String id) {
        return findOneById(id) != null;
    }

    @Override
    public M findOneById(final String id) {
        return models.get(id);
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
        models.remove(model.getId());
        return model;
    }

    @Override
    public M removeById(String id) throws AbstractEntityException {
        final M model = findOneById(id);
        return remove(model);
    }

    @Override
    public M removeByIndex(Integer index) throws AbstractEntityException {
        final M model = findOneByIndex(index);
        return remove(model);
    }

    public void removeAll(final Collection<M> collection) {
        collection.stream().map(AbstractModel::getId).forEach(models::remove);
    }

}
