package ru.forinnyy.tm.service;

import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.api.service.IProjectService;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.ProjectNotFoundException;
import ru.forinnyy.tm.exception.field.*;
import ru.forinnyy.tm.model.Project;

public final class ProjectService extends AbstractService<Project, IProjectRepository> implements IProjectService {

    public ProjectService(IProjectRepository repository) {
        super(repository);
    }

    @Override
    public Project create(final String name) throws AbstractFieldException {
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        return repository.create(name);
    }

    @Override
    public Project create(final String name, final String description) throws AbstractFieldException {
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        return repository.create(name, description);
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
        if (index == null || index < 0 || index > repository.getSize()) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        final Project project = findOneByIndex(index);
        if (project == null) throw new ProjectNotFoundException();
        project.setName(name);
        project.setDescription(description);
        return project;
    }

    @Override
    public Project changeProjectStatusById(final String id, final Status status) throws AbstractFieldException, AbstractEntityException {
        if (id == null || id.isEmpty()) throw new ProjectIdEmptyException();
        final Project project = findOneById(id);
        if (project == null) throw new ProjectNotFoundException();
        project.setStatus(status);
        return project;
    }

    @Override
    public Project changeProjectStatusByIndex(final Integer index, final Status status) throws AbstractFieldException, AbstractEntityException {
        if (index == null || index < 0 || index > repository.getSize()) throw new IndexIncorrectException();
        if (index >= repository.getSize()) throw new IndexIncorrectException();
        final Project project = findOneByIndex(index);
        if (project == null) throw new ProjectNotFoundException();
        project.setStatus(status);
        return project;
    }

}
