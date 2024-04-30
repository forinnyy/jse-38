package ru.forinnyy.tm.controller;

import ru.forinnyy.tm.api.controller.IProjectTaskController;
import ru.forinnyy.tm.api.service.IProjectTaskService;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.util.TerminalUtil;

public class ProjectTaskController implements IProjectTaskController {

    private final IProjectTaskService projectTaskService;

    public ProjectTaskController(IProjectTaskService projectTaskService) {
        this.projectTaskService = projectTaskService;
    }

    @Override
    public void bindTaskToProject() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[BIND TASK TO PROJECT]");
        System.out.println("ENTER PROJECT ID:");
        final String projectId = TerminalUtil.nextLine();
        System.out.println("ENTER TASK ID:");
        final String taskId = TerminalUtil.nextLine();
        projectTaskService.bindTaskToProject(projectId, taskId);
    }

    @Override
    public void unbindTaskToProject() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[UNBIND TASK FROM PROJECT]");
        System.out.println("ENTER PROJECT ID:");
        final String projectId = TerminalUtil.nextLine();
        System.out.println("ENTER TASK ID:");
        final String taskId = TerminalUtil.nextLine();
        projectTaskService.unbindTaskFromProject(projectId, taskId);
    }

}
