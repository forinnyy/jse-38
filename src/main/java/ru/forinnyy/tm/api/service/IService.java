package ru.forinnyy.tm.api.service;

import ru.forinnyy.tm.api.repository.IRepository;
import ru.forinnyy.tm.api.repository.IUserOwnedRepository;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.model.AbstractModel;

import java.util.List;


public interface IService<M extends AbstractModel> extends IRepository<M> {

    List<M> findAll(Sort sort);

}
