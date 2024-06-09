package ru.forinnyy.tm.repository;

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
    public void clear(final String userId) {
        final List<M> models = findAll(userId);
        removeAll(models);
    }

    @Override
    public List<M> findAll(final String userId) {
        return findAll()
                .stream()
                .filter(m -> userId.equals(m.getUserId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<M> findAll(final String userId, final Comparator<M> comparator) {
        final List<M> result = findAll(userId);
        result.sort(comparator);
        return result;
    }

    @Override
    public List<M> findAll(final String userId, final Sort sort) throws AbstractFieldException {
        return findAll(userId, sort.getComparator());
    }

    @Override
    public M add(final String userId, final M model) {
        if (userId == null) return  null;
        model.setUserId(userId);
        return add(model);
    }

    @Override
    public boolean existsById(final String userId, final String id) throws AbstractUserException {
        return findOneById(userId, id) != null;
    }

    @Override
    public M findOneById(final String userId, final String id) throws AbstractUserException {
        return findAll()
                .stream()
                .filter(m -> userId.equals(m.getUserId()))
                .filter(m -> id.equals(m.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public M findOneByIndex(final String userId, final Integer index) {
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

    @Override
    public M remove(final String userId, final M model) throws AbstractEntityException, AbstractUserException {
        if (userId == null || model == null) return null;
        return removeById(userId, model.getId());
    }

    @Override
    public M removeById(final String userId, final String id) throws AbstractEntityException, AbstractUserException {
        if (userId == null || id == null) return null;
        final M model = findOneById(userId, id);
        if (model == null) return null;
        return remove(model);
    }

    @Override
    public M removeByIndex(final String userId, final Integer index) throws AbstractEntityException {
        final M model = findOneByIndex(userId, index);
        if (model == null) return null;
        return remove(model);
    }

    @Override
    public void removeAll(String userId) {
        final List<M> list = findAll(userId);
        removeAll(list);
    }

}
