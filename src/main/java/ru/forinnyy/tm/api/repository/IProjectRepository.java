package ru.forinnyy.tm.api.repository;

import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Project;

public interface IProjectRepository extends IUserOwnedRepository<Project> {

    Project create(String userId, String name) throws AbstractFieldException;

    Project create(String userId, String name, String description) throws AbstractFieldException;

}
