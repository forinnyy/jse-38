package ru.forinnyy.tm.controller;

import ru.forinnyy.tm.api.service.ITaskService;
import ru.forinnyy.tm.api.controller.ITaskController;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.util.TerminalUtil;

import java.util.Arrays;
import java.util.List;

public class TaskController implements ITaskController {

    private final ITaskService taskService;

    public TaskController(final ITaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void showTasks() {
        System.out.println("[TASK LIST]");
        System.out.println("ENTER SORT:");
        System.out.println(Arrays.toString(Sort.values()));
        final String sortType = TerminalUtil.nextLine();
        final Sort sort = Sort.toSort(sortType);
        final List<Task> tasks = taskService.findAll(sort);
        renderTasks(tasks);
    }

    private void showTask(final Task task) {
        if (task == null) return;
        System.out.println("ID: " + task.getId());
        System.out.println("NAME: " + task.getName());
        System.out.println("DESCRIPTION: " + task.getDescription());
        System.out.println("STATUS: " + Status.toName(task.getStatus()));
        System.out.println("PROJECT ID: " + task.getProjectId());
    }

    @Override
    public void clearTasks() {
        System.out.println("[CLEAR TASKS]");
        taskService.clear();
    }

    @Override
    public void createTask() throws AbstractFieldException {
        System.out.println("[CREATE TASK]");
        System.out.println("[ENTER NAME]");
        final String name = TerminalUtil.nextLine();
        System.out.println("[ENTER DESCRIPTION]");
        final String description = TerminalUtil.nextLine();
        taskService.create(name, description);
    }

    @Override
    public void removeTaskById() throws AbstractFieldException {
        System.out.println("[REMOVE TASK BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        taskService.removeById(id);
    }

    @Override
    public void removeTaskByIndex() throws AbstractFieldException {
        System.out.println("[REMOVE TASK BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() -1;
        taskService.removeByIndex(index);
    }

    @Override
    public void showTaskById() throws AbstractFieldException {
        System.out.println("[SHOW TASK BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        final Task task = taskService.findOneById(id);
        showTask(task);
    }

    @Override
    public void showTaskByIndex() throws AbstractFieldException {
        System.out.println("[SHOW TASK BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() -1;
        final Task task = taskService.findOneByIndex(index);
        showTask(task);
    }

    private void renderTasks(final List<Task> tasks) {
        int index = 1;
        for (final Task task: tasks) {
            if (task == null) continue;
            System.out.println(index + ". " + task.getName());
            index++;
        }
    }

    @Override
    public void showTaskByProjectId() {
        System.out.println("[TASK LIST BY PROJECT ID]");
        System.out.println("ENTER PROJECT ID:");
        final String projectId = TerminalUtil.nextLine();
        final List<Task> tasks = taskService.findAllByProjectId(projectId);
        renderTasks(tasks);
    }

    @Override
    public void updateTaskById() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[SHOW TASK BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        taskService.updateById(id, name, description);
    }

    @Override
    public void updateTaskByIndex() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[SHOW TASK BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() -1;
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        taskService.updateByIndex(index, name, description);
    }

    @Override
    public void startTaskById() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[START TASK BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        taskService.changeTaskStatusById(id, Status.IN_PROGRESS);
    }

    @Override
    public void startTaskByIndex() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[START TASK BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        taskService.changeTaskStatusByIndex(index, Status.IN_PROGRESS);
    }

    @Override
    public void completeTaskById() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[COMPLETE TASK BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        taskService.changeTaskStatusById(id, Status.COMPLETED);
    }

    @Override
    public void completeTaskByIndex() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[COMPLETE TASK BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        taskService.changeTaskStatusByIndex(index, Status.COMPLETED);
    }

    @Override
    public void changeTaskStatusById() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[CHANGE TASK STATUS BY ID]");
        System.out.println("ENTER ID:");
        final String id = TerminalUtil.nextLine();
        System.out.println("ENTER STATUS");
        System.out.println(Arrays.toString(Status.values()));
        final String statusValue = TerminalUtil.nextLine();
        final Status status = Status.toStatus(statusValue);
        taskService.changeTaskStatusById(id, status);
    }

    @Override
    public void changeTaskStatusByIndex() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[CHANGE TASK STATUS BY INDEX]");
        System.out.println("ENTER INDEX:");
        final Integer index = TerminalUtil.nextNumber() - 1;
        System.out.println("ENTER STATUS");
        System.out.println(Arrays.toString(Status.values()));
        final String statusValue = TerminalUtil.nextLine();
        final Status status = Status.toStatus(statusValue);
        taskService.changeTaskStatusByIndex(index, status);
    }

}
