package ru.forinnyy.tm.api.controller;

import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;

public interface IProjectTaskController {

    void bindTaskToProject() throws AbstractEntityException, AbstractFieldException;

    void unbindTaskToProject() throws AbstractEntityException, AbstractFieldException;

}
