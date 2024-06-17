package ru.forinnyy.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.util.TerminalUtil;

public final class ProjectShowByIndexCommand extends AbstractProjectCommand {

    @NotNull
    private static final String NAME = "project-show-by-index";

    @NotNull
    private static final String DESCRIPTION = "Show project by index.";

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
    public void execute() throws AbstractFieldException, AbstractUserException {
        System.out.println("[SHOW PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        @NotNull final Integer index = TerminalUtil.nextNumber() -1;
        @NotNull final String userId = getUserId();
        @Nullable final Project project = getProjectService().findOneByIndex(userId, index);
        showProject(project);
    }

}
