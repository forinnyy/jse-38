package ru.forinnyy.tm.repository;

import lombok.NonNull;
import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.model.Project;


public final class ProjectRepository extends AbstractUserOwnedRepository<Project>
        implements IProjectRepository {

    @NonNull
    @Override
    public Project create(@NonNull final String userId, @NonNull final String name) {
        @NonNull final Project project = new Project();
        project.setName(name);
        project.setUserId(userId);
        return add(userId, project);
    }

    @NonNull
    @Override
    public Project create(@NonNull final String userId, @NonNull final String name, @NonNull final String description) {
        @NonNull final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        return add(userId, project);
    }

}
