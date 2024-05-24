package ru.forinnyy.tm.api.repository;

import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.AbstractUserOwnedModel;

import java.util.Comparator;
import java.util.List;

public interface IUserOwnedRepository<M extends AbstractUserOwnedModel> extends IRepository<M> {

    void clear(String userId) throws AbstractFieldException;

    List<M> findAll(String userId) throws AbstractFieldException;

    List<M> findAll(String userId, Comparator<M> comparator) throws AbstractFieldException;

    List<M> findAll(String userId, Sort sort) throws AbstractFieldException;

    M add(String userId, M model) throws AbstractFieldException;

    boolean existsById(String userId, String id) throws AbstractFieldException;

    M findOneById(String userId, String id) throws AbstractFieldException;

    M findOneByIndex(String userId, Integer index) throws AbstractFieldException;

    int getSize(String userId) throws AbstractFieldException;

    M remove(String userId, M model) throws AbstractFieldException, AbstractEntityException;

    M removeById(String userId, String id) throws AbstractFieldException, AbstractEntityException;

    M removeByIndex(String userId, Integer index) throws AbstractFieldException, AbstractEntityException;

}
