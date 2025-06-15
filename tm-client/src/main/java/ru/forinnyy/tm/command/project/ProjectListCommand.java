package ru.forinnyy.tm.command.project;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.ProjectListRequest;
import ru.forinnyy.tm.dto.response.ProjectListResponse;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ProjectListCommand extends AbstractProjectCommand {

    @NonNull
    private static final String NAME = "project-list";

    @NonNull
    private static final String DESCRIPTION = "Show project list.";

    @NonNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @NonNull
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractUserException, AbstractFieldException {
        System.out.println("[SHOW PROJECTS]");
        System.out.println("ENTER SORT:");
        System.out.println(Arrays.toString(Sort.values()));
        @NonNull final String sortType = TerminalUtil.nextLine();
        final Sort sort = Sort.toSort(sortType);

        @NonNull final ProjectListRequest request = new ProjectListRequest(getToken());
        request.setSort(sort);

        @NonNull final ProjectListResponse response = getProjectEndpointClient().listProject(request);
        if (response.getProjects() == null) response.setProjects(Collections.emptyList());
        @NonNull final List<Project> projects = response.getProjects();
        int index = 1;
        for (final Project project: projects) {
            if (project == null) continue;
            System.out.println(index + ". " + project.getName());
            index++;
        }
    }

}
