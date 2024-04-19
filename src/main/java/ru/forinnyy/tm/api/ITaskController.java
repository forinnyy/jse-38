package ru.forinnyy.tm.api;

public interface ITaskController {

    void showTasks();

    void clearTasks();

    void createTask();

    void removeTaskById();

    void removeTaskByIndex();

    void showTaskById();

    void showTaskByIndex();

    void showTaskByProjectId();

    void updateTaskById();

    void updateTaskByIndex();

    void startTaskById();

    void startTaskByIndex();

    void completeTaskById();

    void completeTaskByIndex();

    void changeTaskStatusById();

    void changeTaskStatusByIndex();

}
