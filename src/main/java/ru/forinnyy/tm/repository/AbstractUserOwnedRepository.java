package ru.forinnyy.tm.repository;

import ru.forinnyy.tm.api.repository.IUserOwnedRepository;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.AbstractUserOwnedModel;

import java.util.*;

public abstract class AbstractUserOwnedRepository<M extends AbstractUserOwnedModel>
        extends AbstractRepository<M>
        implements IUserOwnedRepository<M> {

    @Override
    public void clear(final String userId) {
        final List<M> models = findAll(userId);
        removeAll(models);
    }

    @Override
    public List<M> findAll(final String userId) {
        if (userId == null) return Collections.emptyList();
        final List<M> result = new ArrayList<>();
        for (final M model : models) {
            if (userId.equals(model.getUserId())) result.add(model);
        }
        return result;
    }

    @Override
    public List<M> findAll(final String userId, final Comparator<M> comparator) {
        final List<M> result = findAll(userId);
        result.sort(comparator);
        return result;
    }

    @Override
    public List<M> findAll(final String userId, final Sort sort) throws AbstractFieldException {
        final List<M> result = findAll(userId, sort.getComparator());
        return result;
    }

    @Override
    public M add(final String userId, final M model) {
        if (userId == null) return  null;
        model.setUserId(userId);
        return add(model);
    }

    @Override
    public boolean existsById(final String userId, final String id) throws AbstractFieldException {
        return findOneById(userId, id) != null;
    }

    @Override
    public M findOneById(final String userId, final String id) throws AbstractFieldException {
        if (userId == null || id == null) return null;
        for (final M model : models) {
            if (!id.equals(model.getId())) continue;
            if (!userId.equals(model.getUserId())) continue;
            return model;
        }
        return null;
    }

    @Override
    public M findOneByIndex(final String userId, final Integer index) {
        return findAll(userId).get(index);
    }

    @Override
    public int getSize(final String userId) {
        int count = 0;
        for (final M model : models) {
            if (userId.equals(model.getUserId())) count++;
        }
        return count;
    }

    @Override
    public M remove(final String userId, final M model) throws AbstractFieldException {
        if (userId == null || model == null) return null;
        return removeById(userId, model.getId());
    }

    @Override
    public M removeById(final String userId, final String id) throws AbstractFieldException {
        if (userId == null || id == null) return null;
        final M model = findOneById(userId, id);
        if (model == null) return null;
        try {
            return remove(model);
        } catch (ru.forinnyy.tm.exception.entity.AbstractEntityException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public M removeByIndex(final String userId, final Integer index) throws AbstractFieldException {
        final M model = findOneByIndex(userId, index);
        if (model == null) return null;
        try {
            return remove(model);
        } catch (ru.forinnyy.tm.exception.entity.AbstractEntityException e) {
            throw new RuntimeException(e);
        }
    }
}
