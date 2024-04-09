package ru.forinnyy.tm.api;

public interface ITaskController {

    void showTasks();

    void clearTasks();

    void createTask();

    void removeTaskById();

    void removeTaskByIndex();

    void showTaskById();

    void showTaskByIndex();

    void updateTaskById();

    void updateTaskByIndex();

}
