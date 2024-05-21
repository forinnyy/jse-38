package ru.forinnyy.tm.api.repository;

import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Project;

public interface IProjectRepository extends IRepository<Project> {

    Project create(String name) throws AbstractFieldException;

    Project create(String name, String description) throws AbstractFieldException;

}
