package ru.forinnyy.tm.command.task;

import ru.forinnyy.tm.api.service.IProjectTaskService;
import ru.forinnyy.tm.api.service.ITaskService;
import ru.forinnyy.tm.command.AbstractCommand;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.model.Task;

import java.util.List;

public abstract class AbstractTaskCommand extends AbstractCommand {

    protected ITaskService getTaskService() {
        return getServiceLocator().getTaskService();
    }

    protected IProjectTaskService getProjectTaskService() {
        return getServiceLocator().getProjectTaskService();
    }

    @Override
    public String getArgument() {
        return null;
    }

    protected void showTask(final Task task) {
        if (task == null) return;
        System.out.println("ID: " + task.getId());
        System.out.println("NAME: " + task.getName());
        System.out.println("DESCRIPTION: " + task.getDescription());
        System.out.println("STATUS: " + Status.toName(task.getStatus()));
        System.out.println("PROJECT ID: " + task.getProjectId());
    }

    protected void renderTasks(final List<Task> tasks) {
        int index = 1;
        for (final Task task: tasks) {
            if (task == null) continue;
            System.out.println(index + ". " + task.getName());
            index++;
        }
    }

    @Override
    public Role[] getRoles() {
        return Role.values();
    }

}
