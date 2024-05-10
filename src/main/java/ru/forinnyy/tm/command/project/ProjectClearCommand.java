package ru.forinnyy.tm.command.project;

public final class ProjectClearCommand extends AbstractProjectCommand {

    private static final String NAME = "project-clear";

    private static final String DESCRIPTION = "Delete all projects.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() {
        System.out.println("[CLEAR PROJECTS]");
        getProjectService().clear();
    }

}
