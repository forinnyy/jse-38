package ru.forinnyy.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.api.repository.IUserOwnedRepository;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.AbstractUserOwnedModel;

import java.util.List;

public interface IUserOwnedService<M extends AbstractUserOwnedModel> extends IUserOwnedRepository<M> {

    @NotNull
    List<M> findAll(String userId, Sort sort) throws AbstractFieldException;

}
