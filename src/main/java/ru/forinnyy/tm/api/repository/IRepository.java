package ru.forinnyy.tm.api.repository;

import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.AbstractModel;

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

    M remove(M model);

    M removeById(String id) throws AbstractFieldException;

    M removeByIndex(Integer index) throws AbstractFieldException;

}
