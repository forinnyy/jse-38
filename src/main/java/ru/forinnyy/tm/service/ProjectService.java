package ru.forinnyy.tm.service;

import ru.forinnyy.tm.api.IProjectRepository;
import ru.forinnyy.tm.api.IProjectService;
import ru.forinnyy.tm.model.Project;

import java.util.List;

public final class ProjectService implements IProjectService {

    private final IProjectRepository projectRepository;

    public ProjectService(final IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project create(final String name, final String description) {
        if (name == null || name.isEmpty()) return null;
        if (description == null || description.isEmpty()) return null;
        final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        return add(project);
    }

    @Override
    public Project add(final Project project) {
        if (project == null) return null;
        return projectRepository.add(project);
    }

    @Override
    public void clear() {
        projectRepository.clear();
    }

}
