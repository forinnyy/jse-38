package ru.forinnyy.tm.api.repository;

import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Project;

import java.util.Comparator;
import java.util.List;

public interface IProjectRepository {

    List<Project> findAll();

    List<Project> findAll(Comparator<Project> comparator);

    Project add(Project project) throws AbstractEntityException;

    void clear();

    int getSize();

    Project create(String name) throws AbstractFieldException;

    Project create(String name, String description) throws AbstractFieldException;

    Project findOneById(String id) throws AbstractFieldException;

    Project findOneByIndex(Integer index) throws AbstractFieldException;

    Project remove(Project project) throws AbstractEntityException;

    Project removeById(String id) throws AbstractFieldException;

    Project removeByIndex(Integer index) throws AbstractFieldException;

    boolean existsById(String projectId);

}
