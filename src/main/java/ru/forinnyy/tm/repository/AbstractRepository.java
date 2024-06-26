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
    public List<M> findAll(@NotNull Comparator<M> comparator) {
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

    @Nullable
    @Override
    public M findOneById(@Nullable final String id) {
        return models.get(id);
    }

    @Nullable
    @Override
    public M findOneByIndex(@Nullable Integer index) {
        if (index == null) return null;
        @NotNull final List<String> list = new LinkedList<>(models.keySet());
        @Nullable final String key = list.get(index);
        return models.get(key);
    }

    @Override
    public int getSize() {
        return models.size();
    }

    @Nullable
    @Override
    public M remove(@Nullable M model) throws AbstractEntityException {
        if (model == null) throw new EntityNotFoundException();
        models.remove(model.getId());
        return model;
    }

    @Nullable
    @Override
    public M removeById(@Nullable String id) throws AbstractEntityException {
        @Nullable final M model = findOneById(id);
        return remove(model);
    }

    @Nullable
    @Override
    public M removeByIndex(@Nullable Integer index) throws AbstractEntityException {
        @Nullable final M model = findOneByIndex(index);
        return remove(model);
    }

    public void removeAll(@Nullable final Collection<M> collection) {
        if (collection == null) return;
        collection.stream().map(AbstractModel::getId).forEach(models::remove);
    }

}
