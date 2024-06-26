package ru.forinnyy.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.api.repository.IUserOwnedRepository;
import ru.forinnyy.tm.api.service.IProjectService;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.ProjectNotFoundException;
import ru.forinnyy.tm.exception.field.*;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Project;

public final class ProjectService extends AbstractUserOwnedService<Project, IProjectRepository>
        implements IProjectService {

    public ProjectService(IProjectRepository repository) {
        super(repository);
    }

    @NotNull
    @Override
    public Project create(@Nullable final String userId, @Nullable final String name) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        return repository.create(userId, name);
    }

    @NotNull
    @Override
    public Project create(@Nullable final String userId, @Nullable final String name, @Nullable final String description) throws AbstractFieldException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        return repository.create(userId, name, description);
    }

    @NotNull
    @Override
    public Project updateById(@Nullable final String userId, @Nullable final String id, @Nullable final String name, @Nullable final String description)
            throws AbstractFieldException, AbstractEntityException, AbstractUserException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new ProjectIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @Nullable final Project project = findOneById(userId, id);
        if (project == null) throw new ProjectNotFoundException();
        project.setName(name);
        project.setDescription(description);
        return project;
    }

    @NotNull
    @Override
    public Project updateByIndex(@Nullable final String userId, @Nullable final Integer index, @Nullable final String name, @Nullable final String description)
            throws AbstractFieldException, AbstractEntityException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0 || index > repository.getSize()) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        @Nullable final Project project = findOneByIndex(userId, index);
        if (project == null) throw new ProjectNotFoundException();
        project.setName(name);
        project.setDescription(description);
        return project;
    }

    @NotNull
    @Override
    public Project changeProjectStatusById(@Nullable final String userId, @Nullable final String id, @NotNull final Status status) throws AbstractFieldException, AbstractEntityException, AbstractUserException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (id == null || id.isEmpty()) throw new ProjectIdEmptyException();
        @Nullable final Project project = findOneById(userId, id);
        if (project == null) throw new ProjectNotFoundException();
        project.setStatus(status);
        return project;
    }

    @NotNull
    @Override
    public Project changeProjectStatusByIndex(@Nullable final String userId, @Nullable final Integer index, @NotNull final Status status) throws AbstractFieldException, AbstractEntityException {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        if (index == null || index < 0 || index > repository.getSize()) throw new IndexIncorrectException();
        if (index >= repository.getSize()) throw new IndexIncorrectException();
        @Nullable final Project project = findOneByIndex(userId, index);
        if (project == null) throw new ProjectNotFoundException();
        project.setStatus(status);
        return project;
    }

}
