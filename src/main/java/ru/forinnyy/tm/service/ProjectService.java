package ru.forinnyy.tm.service;

import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.api.service.IProjectService;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.model.Project;

import java.util.Comparator;
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
    public List<Project> findAll(Sort sort) {
        if (sort == null) return findAll();
        final Comparator<Project> comparator = sort.getComparator();
        return findAll(comparator);
    }

    @Override
    public List<Project> findAll(Comparator<Project> comparator) {
        if (comparator == null) return findAll();
        return projectRepository.findAll(comparator);
    }

    @Override
    public Project create(String name) {
        if (name == null || name.isEmpty()) return null;
        return projectRepository.create(name);
    }

    @Override
    public Project create(final String name, final String description) {
        if (name == null || name.isEmpty()) return null;
        if (description == null || description.isEmpty()) return null;
        return projectRepository.create(name, description);
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

    @Override
    public Project findOneById(String id) {
        if (id == null || id.isEmpty()) return null;
        return projectRepository.findOneById(id);
    }

    @Override
    public Project findOneByIndex(Integer index) {
        if (index == null || index < 0) return null;
        return projectRepository.findOneByIndex(index);
    }

    @Override
    public Project remove(Project project) {
        if (project == null) return null;
        return projectRepository.remove(project);
    }

    @Override
    public Project removeById(String id) {
        if (id == null || id.isEmpty()) return null;
        return projectRepository.removeById(id);
    }

    @Override
    public Project removeByIndex(Integer index) {
        if (index == null || index < 0) return null;
        return projectRepository.removeByIndex(index);
    }

    @Override
    public boolean existsById(String projectId) {
        return projectRepository.existsById(projectId);
    }

    @Override
    public Project updateById(final String id, final String name, final String description) {
        if (id == null || id.isEmpty()) return null;
        if (name == null || name.isEmpty()) return null;
        final Project project = findOneById(id);
        if (project == null) return null;
        project.setName(name);
        project.setDescription(description);
        return project;
    }

    @Override
    public int getSize() {
        return projectRepository.getSize();
    }

    @Override
    public Project updateByIndex(final Integer index, final String name, final String description) {
        if (index == null || index < 0) return null;
        if (name == null || name.isEmpty()) return null;
        final Project project = findOneByIndex(index);
        project.setName(name);
        project.setDescription(description);
        return project;
    }

    @Override
    public Project changeProjectStatusById(String id, Status status) {
        if (id == null || id.isEmpty()) return null;
        final Project project = findOneById(id);
        if (project == null) return null;
        project.setStatus(status);
        return project;
    }

    @Override
    public Project changeProjectStatusByIndex(Integer index, Status status) {
        if (index == null || index < 0) return null;
        if (index >= projectRepository.getSize()) return null;
        final Project project = findOneByIndex(index);
        if (project == null) return null;
        project.setStatus(status);
        return project;
    }

}
