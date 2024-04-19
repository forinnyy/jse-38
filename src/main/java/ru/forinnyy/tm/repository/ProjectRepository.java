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
    public boolean existsById(String projectId) {
        final Project project = findOneById(projectId);
        return (project != null);
    }

    @Override
    public int getSize() {
        return projects.size();
    }

    @Override
    public Project create(final String name) {
        final Project project = new Project();
        project.setName(name);
        return add(project);
    }

    @Override
    public Project create(final String name, final String description) {
        final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        return add(project);
    }

    @Override
    public void clear() {
        projects.clear();
    }

    @Override
    public Project findOneById(final String id) {
        for (final Project project: projects) {
            if (id.equals(project.getId())) return project;
        }
        return null;
    }

    @Override
    public Project findOneByIndex(final Integer index) {
        return projects.get(index);
    }

    @Override
    public Project remove(final Project project) {
        projects.remove(project);
        return project;
    }

    @Override
    public Project removeById(final String id) {
        final Project project = findOneById(id);
        if (project == null) return null;
        return remove(project);
    }

    @Override
    public Project removeByIndex(final Integer index) {
        final Project project = findOneByIndex(index);
        if (project == null) return null;
        return remove(project);
    }

}
