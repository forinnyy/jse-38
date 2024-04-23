package ru.forinnyy.tm.api.repository;

import ru.forinnyy.tm.model.Project;

import java.util.Comparator;
import java.util.List;

public interface IProjectRepository {

    List<Project> findAll();

    List<Project> findAll(Comparator<Project> comparator);

    Project add(Project project);

    void clear();

    int getSize();

    Project create(String name);

    Project create(String name, String description);

    Project findOneById(String id);

    Project findOneByIndex(Integer index);

    Project remove(Project project);

    Project removeById(String id);

    Project removeByIndex(Integer index);

    boolean existsById(String projectId);

}
