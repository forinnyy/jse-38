package ru.forinnyy.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.api.repository.IUserOwnedRepository;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.AbstractUserOwnedModel;
import ru.forinnyy.tm.model.User;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractUserOwnedRepository<M extends AbstractUserOwnedModel>
        extends AbstractRepository<M>
        implements IUserOwnedRepository<M> {

    @Override
    public void clear(@NotNull final String userId) {
        @NotNull final List<M> models = findAll(userId);
        removeAll(models);
    }

    @NotNull
    @Override
    public List<M> findAll(@NotNull final String userId) {
        return findAll()
                .stream()
                .filter(m -> userId.equals(m.getUserId()))
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    public List<M> findAll(@NotNull final String userId, @Nullable final Comparator<M> comparator) {
        @NotNull final List<M> result = findAll(userId);
        result.sort(comparator);
        return result;
    }

    @NotNull
    @SuppressWarnings("unchecked")
    @Override
    public List<M> findAll(@NotNull final String userId, @NotNull final Sort sort) throws AbstractFieldException {
        return findAll(userId, sort.getComparator());
    }

    @NotNull
    @Override
    public M add(@NotNull final String userId, @NotNull final M model) {
        model.setUserId(userId);
        return add(model);
    }

    @Override
    public boolean existsById(@NotNull final String userId, @NotNull final String id) throws AbstractUserException {
        return findOneById(userId, id) != null;
    }

    @Nullable
    @Override
    public M findOneById(@NotNull final String userId, @NotNull final String id) throws AbstractUserException {
        return findAll()
                .stream()
                .filter(m -> userId.equals(m.getUserId()))
                .filter(m -> id.equals(m.getId()))
                .findFirst()
                .orElse(null);
    }

    @Nullable
    @Override
    public M findOneByIndex(@NotNull final String userId, @NotNull final Integer index) {
        return findAll()
                .stream()
                .filter(m -> userId.equals(m.getUserId()))
                .skip(index)
                .findFirst()
                .orElse(null);
    }

    @Override
    public int getSize(final String userId) {
        return (int) findAll()
                .stream()
                .filter(m -> userId.equals(m.getUserId()))
                .count();
    }

    @Nullable
    @Override
    public M remove(@NotNull final String userId, @NotNull final M model) throws AbstractEntityException, AbstractUserException {
        return removeById(userId, model.getId());
    }

    @Nullable
    @Override
    public M removeById(@NotNull final String userId, @NotNull final String id) throws AbstractEntityException, AbstractUserException {
        @Nullable final M model = findOneById(userId, id);
        if (model == null) return null;
        return remove(model);
    }

    @Nullable
    @Override
    public M removeByIndex(@NotNull final String userId, @NotNull final Integer index) throws AbstractEntityException {
        @Nullable final M model = findOneByIndex(userId, index);
        if (model == null) return null;
        return remove(model);
    }

    @Override
    public void removeAll(@NotNull String userId) {
        @NotNull final List<M> list = findAll(userId);
        removeAll(list);
    }

}
