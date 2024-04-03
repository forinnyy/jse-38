package ru.forinnyy.tm.controller;

import ru.forinnyy.tm.api.IProjectController;
import ru.forinnyy.tm.api.IProjectService;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.List;

public final class ProjectController implements IProjectController {

    private final IProjectService projectService;

    public ProjectController(final IProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public void showProjects() {
        System.out.println("[SHOW PROJECTS]");
        final List<Project> projects = projectService.findAll();
        int index = 1;
        for (final Project project: projects) {
            System.out.println(index + ". " + project.getName());
            index++;
        }
        System.out.println("[OK]");
    }

    @Override
    public void createProject() {
        System.out.println("[CREATE PROJECT]");
        System.out.println("ENTER NAME: ");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION: ");
        final String description = TerminalUtil.nextLine();
        final Project project = projectService.create(name, description);
        if (project == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
    }

    @Override
    public void clearProjects() {
        System.out.println("[CLEAR PROJECTS]");
        projectService.clear();
        System.out.println("[OK]");
    }

}
