package ru.forinnyy.tm.controller;

import ru.forinnyy.tm.api.controller.IProjectController;
import ru.forinnyy.tm.api.service.IProjectService;
import ru.forinnyy.tm.api.service.IProjectTaskService;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
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
        System.out.println("ENTER SORT:");
        System.out.println(Arrays.toString(Sort.values()));
        final String sortType = TerminalUtil.nextLine();
        final Sort sort = Sort.toSort(sortType);
        final List<Project> projects = projectService.findAll(sort);
        int index = 1;
        for (final Project project: projects) {
            if (project == null) continue;
            System.out.println(index + ". " + project.getName());
            index++;
        }
    }

    private void showProject(final Project project) {
        if (project == null) return;
        System.out.println("ID: " + project.getId());
        System.out.println("NAME: " + project.getName());
        System.out.println("DESCRIPTION: " + project.getDescription());
        System.out.println("STATUS: " + Status.toName(project.getStatus()));
    }

    @Override
    public void removeProjectById() throws AbstractFieldException, AbstractEntityException {
        System.out.println("[REMOVE PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Project project = projectService.removeById(id);
        projectTaskService.removeProjectById(project.getId());
    }

    @Override
    public void removeProjectByIndex() throws AbstractFieldException, AbstractEntityException {
        System.out.println("[REMOVE PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() -1;
        final Project project = projectService.removeByIndex(index);
        projectTaskService.removeProjectById(project.getId());
    }

    @Override
    public void showProjectById() throws AbstractFieldException {
        System.out.println("[SHOW PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Project project = projectService.findOneById(id);
        showProject(project);
    }

    @Override
    public void showProjectByIndex() throws AbstractFieldException {
        System.out.println("[SHOW PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() -1;
        final Project project = projectService.findOneByIndex(index);
        showProject(project);
    }

    @Override
    public void updateProjectById() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[SHOW PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        projectService.updateById(id, name, description);
    }

    @Override
    public void updateProjectByIndex() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[SHOW PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() -1;
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        projectService.updateByIndex(index, name, description);
    }

    @Override
    public void startProjectById() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[START PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        projectService.changeProjectStatusById(id, Status.IN_PROGRESS);
    }

    @Override
    public void startProjectByIndex() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[START PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        projectService.changeProjectStatusByIndex(index, Status.IN_PROGRESS);
    }

    @Override
    public void completeProjectById() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[COMPLETE PROJECT BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        projectService.changeProjectStatusById(id, Status.COMPLETED);
    }

    @Override
    public void completeProjectByIndex() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[COMPLETE PROJECT BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        projectService.changeProjectStatusByIndex(index, Status.COMPLETED);
    }

    @Override
    public void changeProjectStatusById() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[CHANGE PROJECT STATUS BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        final String statusValue = TerminalUtil.nextLine();
        final Status status = Status.toStatus(statusValue);
        projectService.changeProjectStatusById(id, status);
    }

    @Override
    public void changeProjectStatusByIndex() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[CHANGE PROJECT STATUS BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("ENTER STATUS:");
        System.out.println(Arrays.toString(Status.values()));
        final String statusValue = TerminalUtil.nextLine();
        final Status status = Status.toStatus(statusValue);
        projectService.changeProjectStatusByIndex(index, status);
    }

    @Override
    public void createProject() throws AbstractFieldException {
        System.out.println("[CREATE PROJECT]");
        System.out.println("ENTER NAME: ");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION: ");
        final String description = TerminalUtil.nextLine();
        projectService.create(name, description);
    }

    @Override
    public void clearProjects() {
        System.out.println("[CLEAR PROJECTS]");
        projectService.clear();
    }

}
