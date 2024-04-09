package ru.forinnyy.tm.controller;

import ru.forinnyy.tm.api.ITaskService;
import ru.forinnyy.tm.model.Project;
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

    private void showTask(final Task task) {
        if (task == null) return;
        System.out.println("ID: " + task.getId());
        System.out.println("NAME: " + task.getName());
        System.out.println("DESCRIPTION: " + task.getDescription());
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

    @Override
    public void removeTaskById() {
        System.out.println("[REMOVE TASK BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Task task = taskService.removeById(id);
        if (task == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
    }

    @Override
    public void removeTaskByIndex() {
        System.out.println("[REMOVE TASK BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() -1;
        final Task task = taskService.removeByIndex(index);
        if (task == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
    }

    @Override
    public void showTaskById() {
        System.out.println("[SHOW TASK BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Task task = taskService.findOneById(id);
        if (task == null) {
            System.out.println("[FAIL]");
            return;
        }
        showTask(task);
        System.out.println("[OK]");
    }

    @Override
    public void showTaskByIndex() {
        System.out.println("[SHOW TASK BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() -1;
        final Task task = taskService.findOneByIndex(index);
        if (task == null) {
            System.out.println("[FAIL]");
            return;
        }
        showTask(task);
        System.out.println("[OK]");
    }

    @Override
    public void updateTaskById() {
        System.out.println("[SHOW TASK BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final Task task = taskService.updateById(id, name, description);
        if (task == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
    }

    @Override
    public void updateTaskByIndex() {
        System.out.println("[SHOW TASK BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() -1;
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final Task task = taskService.updateByIndex(index, name, description);
        if (task == null) System.out.println("[FAIL]");
        else System.out.println("[OK]");
    }

}
