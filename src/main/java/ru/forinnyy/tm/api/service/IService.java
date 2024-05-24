package ru.forinnyy.tm.api.service;

import ru.forinnyy.tm.api.repository.IRepository;
import ru.forinnyy.tm.api.repository.IUserOwnedRepository;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.model.AbstractModel;
import ru.forinnyy.tm.model.AbstractUserOwnedModel;

import java.util.List;

public interface IService<M extends AbstractUserOwnedModel> extends IUserOwnedRepository<M> {

    List<M> findAll(Sort sort);

}
