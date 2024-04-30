package ru.forinnyy.tm.service;

import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.api.service.IProjectService;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.ProjectNotFoundException;
import ru.forinnyy.tm.exception.field.*;
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
    public Project create(final String name) throws AbstractFieldException {
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        return projectRepository.create(name);
    }

    @Override
    public Project create(final String name, final String description) throws AbstractFieldException {
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        return projectRepository.create(name, description);
    }

    @Override
    public Project add(final Project project) throws AbstractEntityException {
        if (project == null) throw new ProjectNotFoundException();
        return projectRepository.add(project);
    }

    @Override
    public void clear() {
        projectRepository.clear();
    }

    @Override
    public Project findOneById(String id) throws AbstractFieldException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        return projectRepository.findOneById(id);
    }

    @Override
    public Project findOneByIndex(Integer index) throws AbstractFieldException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        return projectRepository.findOneByIndex(index);
    }

    @Override
    public Project remove(Project project) throws AbstractEntityException {
        if (project == null) throw new ProjectNotFoundException();
        return projectRepository.remove(project);
    }

    @Override
    public Project removeById(String id) throws AbstractFieldException {
        if (id == null || id.isEmpty()) throw new ProjectIdEmptyException();
        return projectRepository.removeById(id);
    }

    @Override
    public Project removeByIndex(Integer index) throws AbstractFieldException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        return projectRepository.removeByIndex(index);
    }

    @Override
    public boolean existsById(String projectId) {
        if (projectId == null || projectId.isEmpty()) return false;
        return projectRepository.existsById(projectId);
    }

    @Override
    public int getSize() {
        return projectRepository.getSize();
    }

    @Override
    public Project updateById(final String id, final String name, final String description) throws AbstractFieldException, AbstractEntityException {
        if (id == null || id.isEmpty()) throw new ProjectIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        final Project project = findOneById(id);
        if (project == null) throw new ProjectNotFoundException();
        project.setName(name);
        project.setDescription(description);
        return project;
    }

    @Override
    public Project updateByIndex(final Integer index, final String name, final String description) throws AbstractFieldException, AbstractEntityException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        final Project project = findOneByIndex(index);
        if (project == null) throw new ProjectNotFoundException();
        project.setName(name);
        project.setDescription(description);
        return project;
    }

    @Override
    public Project changeProjectStatusById(String id, Status status) throws AbstractFieldException, AbstractEntityException {
        if (id == null || id.isEmpty()) throw new ProjectIdEmptyException();
        final Project project = findOneById(id);
        if (project == null) throw new ProjectNotFoundException();
        project.setStatus(status);
        return project;
    }

    @Override
    public Project changeProjectStatusByIndex(Integer index, Status status) throws AbstractFieldException, AbstractEntityException {
        if (index == null || index < 0) throw new IndexIncorrectException();
        if (index >= projectRepository.getSize()) throw new IndexIncorrectException();
        final Project project = findOneByIndex(index);
        if (project == null) throw new ProjectNotFoundException();
        project.setStatus(status);
        return project;
    }

}
