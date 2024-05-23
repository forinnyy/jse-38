package ru.forinnyy.tm.api.repository;

import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.AbstractUserOwnedModel;

import java.util.Comparator;
import java.util.List;

public interface IUserOwnedRepository<M extends AbstractUserOwnedModel> extends IRepository<M> {

    void clear(String userId);

    List<M> findAll(String userId);

    List<M> findAll(String userId, Comparator<M> comparator);

    M add(String userId, M model);

    boolean existsById(String userId, String id) throws AbstractFieldException;

    M findOneById(String userId, String id) throws AbstractFieldException;

    M findOneByIndex(String userId, Integer index);

    int getSize(String userId);

    M remove(String userId, M model);

    M removeById(String userId, String id) throws AbstractFieldException;

    M removeByIndex(String userId, Integer index) throws AbstractFieldException;

}
