package ru.forinnyy.tm.api;

import ru.forinnyy.tm.model.Project;

import java.util.List;

public interface IProjectRepository {

    List<Project> findAll();

    Project add(Project project);

    void clear();

    Project create(String name);

    Project create(String name, String description);

    Project findOneById(String id);

    Project findOneByIndex(Integer index);

    Project remove(Project project);

    Project removeById(String id);

    Project removeByIndex(Integer index);

}
