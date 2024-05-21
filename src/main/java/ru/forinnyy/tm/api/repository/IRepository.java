package ru.forinnyy.tm.api.repository;

import ru.forinnyy.tm.model.AbstractModel;

import java.util.Comparator;
import java.util.List;

public interface IRepository<M extends AbstractModel> {

    void clear();

    List<M> findAll();

    List<M> findAll(Comparator<M> comparator);

    M add(M model);

    boolean existsById(String id);

    M findOneById(String id);

    M findOneByIndex(Integer index);

    int getSize();

    M remove(M model);

    M removeById(String id);

    M removeByIndex(Integer index);

}
