package ru.forinnyy.tm.controller;

import ru.forinnyy.tm.api.ITaskService;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.List;

public class TaskController implements ru.forinnyy.tm.api.ITaskController {

    private final ITaskService taskService;

    public TaskController(final ITaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void showTasks() {
        System.out.println("[SHOW TASKS]");
        final List<Task> tasks = taskService.findAll();
        int index = 1;
        for (final Task task: tasks) {
            System.out.println(index + ". " + task.getName());
            index++;
        }
        System.out.println("[OK]");
    }

    @Override
    public void clearTasks() {
        System.out.println("[CLEAR TASKS]");
        taskService.clear();
        System.out.println("[OK]");
    }

    @Override
    public void createTask() {
        System.out.println("[CREATE TASK]");
        System.out.println("[ENTER NAME]");
        final String name = TerminalUtil.nextLine();
        System.out.println("[ENTER DESCRIPTION]");
        final String description = TerminalUtil.nextLine();
        final Task task = taskService.create(name, description);
        if (task == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
    }

}
