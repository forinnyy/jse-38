package ru.forinnyy.tm.api.controller;

import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;

public interface IProjectController {

    void clearProjects();

    void createProject() throws AbstractFieldException;

    void showProjects();

    void removeProjectById() throws AbstractFieldException, AbstractEntityException;

    void removeProjectByIndex() throws AbstractFieldException, AbstractEntityException;

    void showProjectById() throws AbstractFieldException;

    void showProjectByIndex() throws AbstractFieldException;

    void updateProjectById() throws AbstractEntityException, AbstractFieldException;

    void updateProjectByIndex() throws AbstractEntityException, AbstractFieldException;

    void startProjectById() throws AbstractEntityException, AbstractFieldException;

    void startProjectByIndex() throws AbstractEntityException, AbstractFieldException;

    void completeProjectById() throws AbstractEntityException, AbstractFieldException;

    void completeProjectByIndex() throws AbstractEntityException, AbstractFieldException;

    void changeProjectStatusById() throws AbstractEntityException, AbstractFieldException;

    void changeProjectStatusByIndex() throws AbstractEntityException, AbstractFieldException;

}
