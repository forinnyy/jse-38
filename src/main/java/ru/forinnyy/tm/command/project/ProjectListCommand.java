package ru.forinnyy.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.Arrays;
import java.util.List;

public final class ProjectListCommand extends AbstractProjectCommand {

    @NotNull
    private static final String NAME = "project-list";

    @NotNull
    private static final String DESCRIPTION = "Show project list.";

    @NotNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @NotNull
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractUserException, AbstractFieldException {
        System.out.println("[SHOW PROJECTS]");
        System.out.println("ENTER SORT:");
        System.out.println(Arrays.toString(Sort.values()));
        @NotNull final String sortType = TerminalUtil.nextLine();
        @Nullable final Sort sort = Sort.toSort(sortType);
        @NotNull final String userId = getUserId();
        @NotNull final List<Project> projects = getProjectService().findAll(userId, sort);
        int index = 1;
        for (@Nullable final Project project: projects) {
            if (project == null) continue;
            System.out.println(index + ". " + project.getName());
            index++;
        }
    }

}
