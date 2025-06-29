package ru.forinnyy.tm.service;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import ru.forinnyy.tm.AbstractTest;
import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.api.repository.ITaskRepository;


import ru.forinnyy.tm.exception.entity.ProjectNotFoundException;
import ru.forinnyy.tm.exception.entity.TaskNotFoundException;
import ru.forinnyy.tm.exception.field.ProjectIdEmptyException;
import ru.forinnyy.tm.exception.field.TaskIdEmptyException;
import ru.forinnyy.tm.exception.field.UserIdEmptyException;
import ru.forinnyy.tm.exception.user.PermissionException;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.repository.ProjectRepository;
import ru.forinnyy.tm.repository.TaskRepository;



public final class ProjectTaskServiceTest extends AbstractTest {

    private final IProjectRepository projectRepository = new ProjectRepository();
    private final ITaskRepository taskRepository = new TaskRepository();
    private final ProjectTaskService projectTaskService = new ProjectTaskService(projectRepository, taskRepository);

    @Test
    @SneakyThrows
    public void testBindTaskToProject() {
        // Создаем проект для первого пользователя (UUID1)
        Project project = new Project();
        project.setId(UUID2);
        project.setUserId(UUID1);
        projectRepository.add(project);

        // Создаем проект для другого пользователя (UUID2)
        Project wrongProject = new Project();
        wrongProject.setId(UUID4);
        wrongProject.setUserId(UUID2); // другой userId
        projectRepository.add(wrongProject);

        // Создаем задачу для первого пользователя
        Task task = new Task();
        task.setId(UUID3);
        task.setUserId(UUID1);
        taskRepository.add(task);

        // Позитивный тест: привязка задачи к проекту
        Task bindedTask = projectTaskService.bindTaskToProject(UUID1, UUID2, UUID3);
        Assert.assertNotNull(bindedTask);
        Assert.assertEquals(UUID2, bindedTask.getProjectId());

        // Негативные тесты: проверка на пустые значения
        Assert.assertThrows(UserIdEmptyException.class,
                () -> projectTaskService.bindTaskToProject(null, UUID2, UUID3));
        Assert.assertThrows(UserIdEmptyException.class,
                () -> projectTaskService.bindTaskToProject(EMPTY_STRING, UUID2, UUID3));

        // Негативный тест: проверка выброса исключения, если projectId пустое
        Assert.assertThrows(ProjectIdEmptyException.class,
                () -> projectTaskService.bindTaskToProject(UUID1, null, UUID3));
        Assert.assertThrows(ProjectIdEmptyException.class,
                () -> projectTaskService.bindTaskToProject(UUID1, EMPTY_STRING, UUID3));

        // Негативный тест: проверка выброса исключения, если taskId пустое
        Assert.assertThrows(TaskIdEmptyException.class,
                () -> projectTaskService.bindTaskToProject(UUID1, UUID2, null));
        Assert.assertThrows(TaskIdEmptyException.class,
                () -> projectTaskService.bindTaskToProject(UUID1, UUID2, EMPTY_STRING));

        // Негативный тест: проверка выброса исключения, если проект не найден
        Assert.assertThrows(ProjectNotFoundException.class,
                () -> projectTaskService.bindTaskToProject(UUID1, UUID4, UUID3)); // UUID4 - неверный проект
        Assert.assertThrows(ProjectNotFoundException.class,
                () -> projectTaskService.bindTaskToProject(UUID1, UUID5, UUID3)); // UUID5 - неверный проект

        // Негативный тест: проверка выброса исключения, если задача не найдена
        Assert.assertThrows(TaskNotFoundException.class,
                () -> projectTaskService.bindTaskToProject(UUID1, UUID2, UUID4)); // UUID4 - неверная задача

        // Негативный тест: проверка выброса исключения, если проект принадлежит другому пользователю
//        Assert.assertThrows(PermissionException.class,
//                () -> projectTaskService.bindTaskToProject(UUID1, UUID4, UUID3)); // Проект с UUID4 принадлежит другому пользователю

        // Негативный тест: проверка выброса исключения, если задача принадлежит другому пользователю
        Task taskWithDifferentUser = new Task();
        taskWithDifferentUser.setId(UUID4);
        taskWithDifferentUser.setUserId(UUID2); // Задача для другого пользователя
        taskRepository.add(taskWithDifferentUser);

//        Assert.assertThrows(PermissionException.class,
//                () -> projectTaskService.bindTaskToProject(UUID1, UUID2, UUID4)); // Задача для другого пользователя
    }



}
