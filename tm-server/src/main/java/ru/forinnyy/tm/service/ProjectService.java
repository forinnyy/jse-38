package ru.forinnyy.tm.service;

import lombok.NonNull;
import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.api.service.IConnectionService;
import ru.forinnyy.tm.api.service.IProjectService;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.ProjectNotFoundException;
import ru.forinnyy.tm.exception.field.*;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.repository.ProjectRepository;

import java.sql.Connection;

public final class ProjectService extends AbstractUserOwnedService<Project, IProjectRepository>
        implements IProjectService {

    public ProjectService(@NonNull final IConnectionService connectionService) {
        super(connectionService);
    }

    @NonNull
    public IProjectRepository getRepository(@NonNull final Connection connection) {
        return new ProjectRepository(connection);
    }

    @NonNull
    @Override
    public Project create(final String userId, final String name) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        return repository.create(userId, name);
    }

    @NonNull
    @Override
    public Project create(final String userId, final String name, final String description) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        return repository.create(userId, name, description);
    }

    @NonNull
    @Override
    public Project updateById(final String userId, final String id, final String name, final String description)
            throws AbstractFieldException, AbstractEntityException, AbstractUserException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new ProjectIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        final Project project = findOneById(userId, id);
        if (project == null) throw new ProjectNotFoundException();
        project.setName(name);
        project.setDescription(description);
        return project;
    }

    @NonNull
    @Override
    public Project updateByIndex(final String userId, final Integer index, final String name, final String description)
            throws AbstractFieldException, AbstractEntityException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0 || index >= repository.getSize()) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        final Project project = findOneByIndex(userId, index);
        if (project == null) throw new ProjectNotFoundException();
        project.setName(name);
        project.setDescription(description);
        return project;
    }

    @NonNull
    @Override
    public Project changeProjectStatusById(final String userId, final String id, @NonNull final Status status) throws AbstractFieldException, AbstractEntityException, AbstractUserException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new ProjectIdEmptyException();
        final Project project = findOneById(userId, id);
        if (project == null) throw new ProjectNotFoundException();
        project.setStatus(status);
        return project;
    }

    @NonNull
    @Override
    public Project changeProjectStatusByIndex(final String userId, final Integer index, @NonNull final Status status) throws AbstractFieldException, AbstractEntityException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0 || index >= repository.getSize()) throw new IndexIncorrectException();
        final Project project = findOneByIndex(userId, index);
        if (project == null) throw new ProjectNotFoundException();
        project.setStatus(status);
        return project;
    }

}
