package ru.forinnyy.tm.api;

import ru.forinnyy.tm.model.Project;

import java.util.List;

public interface IProjectRepository {

    List<Project> findAll();

    Project add(Project project);

    void clear();

}
