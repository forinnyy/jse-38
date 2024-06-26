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
    List<M> findAll(String userId) throws AbstractFieldException;

    @NotNull
    List<M> findAll(String userId, Comparator<M> comparator) throws AbstractFieldException;

    @NotNull
    List<M> findAll(String userId, Sort sort) throws AbstractFieldException;

    @NotNull
    M add(String userId, M model) throws AbstractFieldException, AbstractEntityException;

    boolean existsById(String userId, String id) throws AbstractFieldException, AbstractUserException;

    @Nullable
    M findOneById(String userId, String id) throws AbstractFieldException, AbstractUserException;

    @Nullable
    M findOneByIndex(String userId, Integer index) throws AbstractFieldException;

    int getSize(String userId) throws AbstractFieldException;

    @Nullable
    M remove(String userId, M model) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    @Nullable
    M removeById(String userId, String id) throws AbstractFieldException, AbstractEntityException, AbstractUserException;

    @Nullable
    M removeByIndex(String userId, Integer index) throws AbstractFieldException, AbstractEntityException;

    void removeAll(String userId) throws UserIdEmptyException;

}
