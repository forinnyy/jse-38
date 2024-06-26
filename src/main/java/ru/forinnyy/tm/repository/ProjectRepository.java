package ru.forinnyy.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.model.Project;


public final class ProjectRepository extends AbstractUserOwnedRepository<Project>
        implements IProjectRepository {

    @NotNull
    @Override
    public Project create(@NotNull final String userId, @NotNull final String name) {
        @NotNull final Project project = new Project();
        project.setName(name);
        project.setUserId(userId);
        return add(userId, project);
    }

    @NotNull
    @Override
    public Project create(@NotNull final String userId, @NotNull final String name, @NotNull final String description) {
        @NotNull final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        return add(userId, project);
    }

}
