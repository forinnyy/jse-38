package ru.forinnyy.tm.api;

public interface IProjectController {

    void clearProjects();

    void createProject();

    void showProjects();

    void removeProjectById();

    void removeProjectByIndex();

    void showProjectById();

    void showProjectByIndex();

    void updateProjectById();

    void updateProjectByIndex();

}
