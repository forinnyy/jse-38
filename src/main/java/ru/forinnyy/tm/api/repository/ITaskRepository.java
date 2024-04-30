package ru.forinnyy.tm.api.repository;

import ru.forinnyy.tm.exception.AbstractException;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Task;

import java.util.Comparator;
import java.util.List;

public interface ITaskRepository {

    List<Task> findAll();

    List<Task> findAll(Comparator<Task> comparator);

    List<Task> findAllByProjectId(String projectId);

    Task add(Task task) throws AbstractEntityException;

    void clear();

    int getSize();

    Task create(String name) throws AbstractFieldException;

    Task create(String name, String description) throws AbstractFieldException;

    Task findOneById(String id) throws AbstractFieldException;

    Task findOneByIndex(Integer index) throws AbstractFieldException;

    Task remove(Task project) throws AbstractEntityException;

    Task removeById(String id) throws AbstractFieldException;

    Task removeByIndex(Integer index) throws AbstractFieldException;

}
