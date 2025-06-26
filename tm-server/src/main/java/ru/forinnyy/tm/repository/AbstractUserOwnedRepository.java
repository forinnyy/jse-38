package ru.forinnyy.tm.repository;

import lombok.NonNull;
import ru.forinnyy.tm.api.repository.IUserOwnedRepository;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.field.UserIdEmptyException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.AbstractUserOwnedModel;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractUserOwnedRepository<M extends AbstractUserOwnedModel>
        extends AbstractRepository<M>
        implements IUserOwnedRepository<M> {

    @Override
    public void clear(@NonNull final String userId) {
        @NonNull final List<M> models = findAll(userId);
        removeAll(models);
    }

    @NonNull
    @Override
    public List<M> findAll(@NonNull final String userId) {
        return findAll()
                .stream()
                .filter(m -> userId.equals(m.getUserId()))
                .collect(Collectors.toList());
    }

    @NonNull
    @Override
    public List<M> findAll(@NonNull final String userId, final Comparator<M> comparator) {
        @NonNull final List<M> result = findAll(userId);
        result.sort(comparator);
        return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    public List<M> findAll(@NonNull final String userId, @NonNull final Sort sort) throws AbstractFieldException {
        return findAll(userId, sort.getComparator());
    }

    @NonNull
    @Override
    public M add(@NonNull final String userId, @NonNull final M model) {
        model.setUserId(userId);
        return add(model);
    }

    @Override
    public boolean existsById(@NonNull final String userId, @NonNull final String id) throws AbstractUserException {
        return findOneById(userId, id) != null;
    }

    @Override
    public M findOneById(@NonNull final String userId, @NonNull final String id) throws AbstractUserException {
        return findAll()
                .stream()
                .filter(m -> userId.equals(m.getUserId()))
                .filter(m -> id.equals(m.getId()))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public M findOneByIndex(@NonNull final String userId, @NonNull final Integer index) {
        return findAll()
                .stream()
                .filter(m -> userId.equals(m.getUserId()))
                .skip(index)
                .findFirst()
                .orElse(null);
    }

    @Override
    public int getSize(final String userId) throws AbstractFieldException {
        if (userId == null) throw new UserIdEmptyException();
        return (int) findAll()
                .stream()
                .filter(m -> userId.equals(m.getUserId()))
                .count();
    }

    
    @Override
    public M remove(@NonNull final String userId, @NonNull final M model) throws AbstractEntityException, AbstractUserException {
        return removeById(userId, model.getId());
    }

    @Override
    public M removeById(@NonNull final String userId, @NonNull final String id) throws AbstractEntityException, AbstractUserException {
        final M model = findOneById(userId, id);
        return remove(model);
    }
    
    @Override
    public M removeByIndex(@NonNull final String userId, @NonNull final Integer index) throws AbstractEntityException {
        final M model = findOneByIndex(userId, index);
        return remove(model);
    }

    @Override
    public void removeAll(@NonNull final String userId) {
        @NonNull final List<M> list = findAll(userId);
        removeAll(list);
    }

}
