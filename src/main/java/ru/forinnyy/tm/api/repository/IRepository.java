package ru.forinnyy.tm.api.repository;

import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.AbstractModel;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface IRepository<M extends AbstractModel> {

    void clear();

    List<M> findAll();

    List<M> findAll(Comparator<M> comparator);

    M add(M model);

    boolean existsById(String id);

    M findOneById(String id) throws AbstractFieldException;

    M findOneByIndex(Integer index) throws AbstractFieldException;

    int getSize();

    M remove(M model) throws AbstractEntityException;

    M removeById(String id) throws AbstractFieldException, AbstractEntityException;

    M removeByIndex(Integer index) throws AbstractFieldException, AbstractEntityException;

    void removeAll(Collection<M> collection);

}
