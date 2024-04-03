package ru.forinnyy.tm.repository;

import ru.forinnyy.tm.api.IProjectRepository;
import ru.forinnyy.tm.model.Project;

import java.util.ArrayList;
import java.util.List;

public final class ProjectRepository implements IProjectRepository {

    private final List<Project> projects = new ArrayList<>();

    @Override
    public List<Project> findAll() {
        return projects;
    }

    @Override
    public Project add(final Project project) {
        projects.add(project);
        return project;
    }

    @Override
    public void clear() {
        projects.clear();
    }

}
