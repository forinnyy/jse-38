package ru.forinnyy.tm.api.controller;

import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;

public interface ITaskController {

    void showTasks();

    void clearTasks();

    void createTask() throws AbstractFieldException;

    void removeTaskById() throws AbstractFieldException;

    void removeTaskByIndex() throws AbstractFieldException;

    void showTaskById() throws AbstractFieldException;

    void showTaskByIndex() throws AbstractFieldException;

    void showTaskByProjectId();

    void updateTaskById() throws AbstractEntityException, AbstractFieldException;

    void updateTaskByIndex() throws AbstractEntityException, AbstractFieldException;

    void startTaskById() throws AbstractEntityException, AbstractFieldException;

    void startTaskByIndex() throws AbstractEntityException, AbstractFieldException;

    void completeTaskById() throws AbstractEntityException, AbstractFieldException;

    void completeTaskByIndex() throws AbstractEntityException, AbstractFieldException;

    void changeTaskStatusById() throws AbstractEntityException, AbstractFieldException;

    void changeTaskStatusByIndex() throws AbstractEntityException, AbstractFieldException;

}
