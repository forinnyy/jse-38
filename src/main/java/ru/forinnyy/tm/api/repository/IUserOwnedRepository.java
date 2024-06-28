package ru.forinnyy.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.EntityNotFoundException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.field.UserIdEmptyException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.exception.user.PermissionException;
import ru.forinnyy.tm.model.AbstractUserOwnedModel;

import java.util.Comparator;
import java.util.List;

public interface IUserOwnedRepository<M extends AbstractUserOwnedModel> extends IRepository<M> {

    void clear(String userId) throws AbstractFieldException;

    @NotNull
    List<M> findAll(@Nullable String userId) throws AbstractFieldException;

    @NotNull
    List<M> findAll(@Nullable String userId, @Nullable Comparator<M> comparator) throws AbstractFieldException;

    @NotNull
    List<M> findAll(@Nullable String userId, Sort sort) throws AbstractFieldException;

    @NotNull
    M add(@Nullable String userId, @Nullable M model) throws AbstractFieldException, AbstractEntityException;

    boolean existsById(@Nullable String userId, @Nullable String id) throws AbstractFieldException, AbstractUserException;

    @Nullable
    M findOneById(@Nullable String userId, @Nullable String id) throws AbstractFieldException, AbstractUserException;

    @Nullable
    M findOneByIndex(@Nullable String userId, @Nullable Integer index) throws AbstractFieldException;

    int getSize(@Nullable String userId) throws AbstractFieldException;

    @Nullable
    M remove(@Nullable String userId, @Nullable M model) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    @Nullable
    M removeById(@Nullable String userId, @Nullable String id) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    @Nullable
    M removeByIndex(@Nullable String userId, @Nullable Integer index) throws AbstractFieldException, AbstractEntityException;

    void removeAll(@Nullable String userId) throws UserIdEmptyException;

}
