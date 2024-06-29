package ru.forinnyy.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.api.repository.IUserOwnedRepository;
import ru.forinnyy.tm.api.service.IUserOwnedService;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.EntityNotFoundException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.field.IdEmptyException;
import ru.forinnyy.tm.exception.field.IndexIncorrectException;
import ru.forinnyy.tm.exception.field.UserIdEmptyException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.AbstractUserOwnedModel;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractUserOwnedService<M extends AbstractUserOwnedModel, R extends IUserOwnedRepository<M>>
        extends AbstractService<M, R>
        implements IUserOwnedService<M> {

    public AbstractUserOwnedService(@NotNull final R repository) {
        super(repository);
    }

    @Override
    public void clear(@Nullable final String userId) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        repository.clear(userId);
    }

    @NotNull
    @Override
    public List<M> findAll(@Nullable final String userId) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        return repository.findAll(userId);
    }

    @NotNull
    @Override
    public List<M> findAll(@Nullable final String userId, @Nullable final Comparator<M> comparator) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (comparator == null) return repository.findAll(userId);
        return repository.findAll(userId, comparator);
    }

    @NotNull
    @Override
    public M add(@Nullable final String userId, @Nullable final M model) throws AbstractFieldException, AbstractEntityException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (model == null) throw new EntityNotFoundException();
        return repository.add(userId, model);
    }

    @Override
    public boolean existsById(@Nullable final String userId, @Nullable final String id) throws AbstractFieldException, AbstractUserException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        return repository.existsById(userId, id);
    }

    @Nullable
    @Override
    public M findOneById(@Nullable final String userId, @Nullable final String id) throws AbstractFieldException, AbstractUserException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        return repository.findOneById(userId, id);
    }

    @Nullable
    @Override
    public M findOneByIndex(@Nullable final String userId, @Nullable final Integer index) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null) throw new IndexIncorrectException();
        return repository.findOneByIndex(userId, index);
    }

    @Override
    public int getSize(@Nullable final String userId) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        return repository.getSize(userId);
    }

    @Nullable
    @Override
    public M remove(@Nullable final String userId, @Nullable final M model) throws AbstractFieldException, AbstractEntityException, AbstractUserException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (model == null) return null;
        return repository.remove(userId, model);
    }

    @Nullable
    @Override
    public M removeById(@Nullable final String userId, @Nullable final String id) throws AbstractFieldException, AbstractEntityException, AbstractUserException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        return repository.removeById(userId, id);
    }

    @Nullable
    @Override
    public M removeByIndex(@Nullable final String userId, @Nullable final Integer index) throws AbstractFieldException, AbstractEntityException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null) throw new IndexIncorrectException();
        return repository.removeByIndex(userId, index);
    }

    @NotNull
    @SuppressWarnings("unchecked")
    @Override
    public List<M> findAll(@Nullable final String userId, @Nullable final Sort sort) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (sort == null) return findAll(userId);
        return repository.findAll(userId, sort.getComparator());
    }

    @Override
    public void removeAll(@Nullable final String userId) throws UserIdEmptyException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        repository.removeAll(userId);
    }

}
