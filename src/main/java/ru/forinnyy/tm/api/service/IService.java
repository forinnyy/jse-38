package ru.forinnyy.tm.api.service;

import ru.forinnyy.tm.api.repository.IRepository;
import ru.forinnyy.tm.api.repository.IUserOwnedRepository;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.AbstractModel;

import java.util.List;


public interface IService<M extends AbstractModel> extends IRepository<M> {

    List<M> findAll(Sort sort);

    M removeOne(M model) throws AbstractEntityException, AbstractFieldException;

}
