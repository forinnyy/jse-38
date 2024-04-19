package ru.forinnyy.tm.controller;

import ru.forinnyy.tm.api.IProjectController;
import ru.forinnyy.tm.api.IProjectService;
import ru.forinnyy.tm.api.IProjectTaskService;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.Arrays;
import java.util.List;

public final class ProjectController implements IProjectController {

    private final IProjectService projectService;

    private final IProjectTaskService projectTaskService;

    public ProjectController(IProjectService projectService, IProjectTaskService projectTaskService) {
        this.projectService = projectService;
        this.projectTaskService = projectTaskService;
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

    private void showProject(final Project project) {
        if (project == null) return;
        System.out.println("ID: " + project.getId());
        System.out.println("NAME: " + project.getName());
        System.out.println("DESCRIPTION: " + project.getDescription());
        System.out.println("STATUS: " + Status.toName(project.getStatus()));
    }

    @Override
    public void removeProjectById() {
        System.out.println("[REMOVE PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Project project = projectService.removeById(id);
        if (project == null) {
            System.out.println("[FAIL]");
            return;
        }
        projectTaskService.removeProjectById(project.getId());
        System.out.println("[OK]");
    }

    @Override
    public void removeProjectByIndex() {
        System.out.println("[REMOVE PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() -1;
        final Project project = projectService.removeByIndex(index);
        if (project == null) {
            System.out.println("[FAIL]");
            return;
        }
        projectTaskService.removeProjectById(project.getId());
        System.out.println("[OK]");
    }

    @Override
    public void showProjectById() {
        System.out.println("[SHOW PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Project project = projectService.findOneById(id);
        if (project == null) {
            System.out.println("[FAIL]");
            return;
        }
        showProject(project);
        System.out.println("[OK]");
    }

    @Override
    public void showProjectByIndex() {
        System.out.println("[SHOW PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() -1;
        final Project project = projectService.findOneByIndex(index);
        if (project == null) {
            System.out.println("[FAIL]");
            return;
        }
        showProject(project);
        System.out.println("[OK]");
    }

    @Override
    public void updateProjectById() {
        System.out.println("[SHOW PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final Project project = projectService.updateById(id, name, description);
        if (project == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
    }

    @Override
    public void updateProjectByIndex() {
        System.out.println("[SHOW PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() -1;
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final Project project = projectService.updateByIndex(index, name, description);
        if (project == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
    }

    @Override
    public void startProjectById() {
        System.out.println("[START PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Project project = projectService.changeProjectStatusById(id, Status.IN_PROGRESS);
        if (project == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
    }

    @Override
    public void startProjectByIndex() {
        System.out.println("[START PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        final Project project = projectService.changeProjectStatusByIndex(index, Status.IN_PROGRESS);
        if (project == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
    }

    @Override
    public void completeProjectById() {
        System.out.println("[COMPLETE PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Project project = projectService.changeProjectStatusById(id, Status.COMPLETED);
        if (project == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
    }

    @Override
    public void completeProjectByIndex() {
        System.out.println("[COMPLETE PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        final Project project = projectService.changeProjectStatusByIndex(index, Status.COMPLETED);
        if (project == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
    }

    @Override
    public void changeProjectStatusById() {
        System.out.println("[CHANGE PROJECT STATUS BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        final String statusValue = TerminalUtil.nextLine();
        final Status status = Status.toStatus(statusValue);
        final Project project = projectService.changeProjectStatusById(id, status);
        if (project == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
    }

    @Override
    public void changeProjectStatusByIndex() {
        System.out.println("[CHANGE PROJECT STATUS BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        final String statusValue = TerminalUtil.nextLine();
        final Status status = Status.toStatus(statusValue);
        final Project project = projectService.changeProjectStatusByIndex(index, status);
        if (project == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
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
