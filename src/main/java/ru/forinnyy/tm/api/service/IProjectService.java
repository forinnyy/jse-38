package ru.forinnyy.tm.api.service;

import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.api.repository.IUserOwnedRepository;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Project;

import java.util.List;

public interface IProjectService extends IUserOwnedService<Project> {

    Project create(String userId, String name) throws AbstractFieldException;

    Project create(String userId, String name, String description) throws AbstractFieldException;

    Project updateById(String userId, String id, String name, String description) throws AbstractFieldException, AbstractEntityException;

    Project updateByIndex(String userId, Integer index, String name, String description) throws AbstractFieldException, AbstractEntityException;

    Project changeProjectStatusById(String userId, String id, Status status) throws AbstractFieldException, AbstractEntityException;

    Project changeProjectStatusByIndex(String userId, Integer index, Status status) throws AbstractFieldException, AbstractEntityException;

}
