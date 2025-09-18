//package ru.forinnyy.tm.service;
//
//import lombok.SneakyThrows;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.experimental.categories.Category;
//import ru.forinnyy.tm.AbstractTest;
//import ru.forinnyy.tm.api.repository.IProjectRepository;
//import ru.forinnyy.tm.api.repository.ITaskRepository;
//import ru.forinnyy.tm.exception.entity.ProjectNotFoundException;
//import ru.forinnyy.tm.exception.entity.TaskNotFoundException;
//import ru.forinnyy.tm.exception.field.ProjectIdEmptyException;
//import ru.forinnyy.tm.exception.field.TaskIdEmptyException;
//import ru.forinnyy.tm.exception.field.UserIdEmptyException;
//import ru.forinnyy.tm.marker.UnitCategory;
//import ru.forinnyy.tm.model.Project;
//import ru.forinnyy.tm.model.Task;
//import ru.forinnyy.tm.repository.ProjectRepository;
//import ru.forinnyy.tm.repository.TaskRepository;
//
//
//@Category(UnitCategory.class)
//public final class ProjectTaskServiceTest extends AbstractTest {
//
//    private final IProjectRepository projectRepository = new ProjectRepository();
//    private final ITaskRepository taskRepository = new TaskRepository();
//    private final ProjectTaskService projectTaskService = new ProjectTaskService(projectRepository, taskRepository);
//
//    @Test
//    @SneakyThrows
//    public void testBindTaskToProject() {
//        Project project = new Project();
//        project.setId(UUID2);
//        project.setUserId(UUID1);
//        projectRepository.add(project);
//
//        Project wrongProject = new Project();
//        wrongProject.setId(UUID4);
//        wrongProject.setUserId(UUID2);
//        projectRepository.add(wrongProject);
//
//        Task task = new Task();
//        task.setId(UUID3);
//        task.setUserId(UUID1);
//        taskRepository.add(task);
//
//        Task bindedTask = projectTaskService.bindTaskToProject(UUID1, UUID2, UUID3);
//        Assert.assertNotNull(bindedTask);
//        Assert.assertEquals(UUID2, bindedTask.getProjectId());
//
//        Assert.assertThrows(UserIdEmptyException.class,
//                () -> projectTaskService.bindTaskToProject(null, UUID2, UUID3));
//        Assert.assertThrows(UserIdEmptyException.class,
//                () -> projectTaskService.bindTaskToProject(EMPTY_STRING, UUID2, UUID3));
//
//        Assert.assertThrows(ProjectIdEmptyException.class,
//                () -> projectTaskService.bindTaskToProject(UUID1, null, UUID3));
//        Assert.assertThrows(ProjectIdEmptyException.class,
//                () -> projectTaskService.bindTaskToProject(UUID1, EMPTY_STRING, UUID3));
//
//        Assert.assertThrows(TaskIdEmptyException.class,
//                () -> projectTaskService.bindTaskToProject(UUID1, UUID2, null));
//        Assert.assertThrows(TaskIdEmptyException.class,
//                () -> projectTaskService.bindTaskToProject(UUID1, UUID2, EMPTY_STRING));
//
//        Assert.assertThrows(ProjectNotFoundException.class,
//                () -> projectTaskService.bindTaskToProject(UUID1, UUID4, UUID3));
//        Assert.assertThrows(ProjectNotFoundException.class,
//                () -> projectTaskService.bindTaskToProject(UUID1, UUID5, UUID3));
//
//        Assert.assertThrows(TaskNotFoundException.class,
//                () -> projectTaskService.bindTaskToProject(UUID1, UUID2, UUID4));
//
//        // TODO PermissionException
//    }
//
//    @Test
//    @SneakyThrows
//    public void testUnbindTaskFromProject() {
//        Project project = new Project();
//        project.setId(UUID2);
//        project.setUserId(UUID1);
//        projectRepository.add(project);
//
//        Task task = new Task();
//        task.setId(UUID3);
//        task.setUserId(UUID1);
//        task.setProjectId(UUID2);
//        taskRepository.add(task);
//
//        Task unbindedTask = projectTaskService.unbindTaskFromProject(UUID1, UUID2, UUID3);
//        Assert.assertNotNull(unbindedTask);
//        Assert.assertNull(unbindedTask.getProjectId());
//
//        Assert.assertThrows(UserIdEmptyException.class,
//                () -> projectTaskService.unbindTaskFromProject(null, UUID2, UUID3));
//        Assert.assertThrows(UserIdEmptyException.class,
//                () -> projectTaskService.unbindTaskFromProject(EMPTY_STRING, UUID2, UUID3));
//
//        Assert.assertThrows(ProjectIdEmptyException.class,
//                () -> projectTaskService.unbindTaskFromProject(UUID1, null, UUID3));
//        Assert.assertThrows(ProjectIdEmptyException.class,
//                () -> projectTaskService.unbindTaskFromProject(UUID1, EMPTY_STRING, UUID3));
//
//        Assert.assertThrows(TaskIdEmptyException.class,
//                () -> projectTaskService.unbindTaskFromProject(UUID1, UUID2, null));
//        Assert.assertThrows(TaskIdEmptyException.class,
//                () -> projectTaskService.unbindTaskFromProject(UUID1, UUID2, EMPTY_STRING));
//
//        Assert.assertThrows(ProjectNotFoundException.class,
//                () -> projectTaskService.unbindTaskFromProject(UUID1, UUID4, UUID3));
//        Assert.assertThrows(ProjectNotFoundException.class,
//                () -> projectTaskService.unbindTaskFromProject(UUID1, UUID5, UUID3));
//
//        Assert.assertThrows(TaskNotFoundException.class,
//                () -> projectTaskService.unbindTaskFromProject(UUID1, UUID2, UUID4));
//
//        // TODO PermissionException
//    }
//
//    @Test
//    @SneakyThrows
//    public void testRemoveProjectById() {
//        Project project = new Project();
//        project.setId(UUID2);
//        project.setUserId(UUID1);
//        projectRepository.add(project);
//
//        Task task = new Task();
//        task.setId(UUID3);
//        task.setUserId(UUID1);
//        task.setProjectId(UUID2);
//        taskRepository.add(task);
//
//        projectTaskService.removeProjectById(UUID1, UUID2);
//
//        Assert.assertNull(projectRepository.findOneById(UUID1, UUID2));
//        Assert.assertTrue(taskRepository.findAllByProjectId(UUID1, UUID2).isEmpty());
//
//        Assert.assertThrows(UserIdEmptyException.class,
//                () -> projectTaskService.removeProjectById(null, UUID2));
//        Assert.assertThrows(UserIdEmptyException.class,
//                () -> projectTaskService.removeProjectById(EMPTY_STRING, UUID2));
//
//        Assert.assertThrows(ProjectIdEmptyException.class,
//                () -> projectTaskService.removeProjectById(UUID1, null));
//        Assert.assertThrows(ProjectIdEmptyException.class,
//                () -> projectTaskService.removeProjectById(UUID1, EMPTY_STRING));
//
//        Assert.assertThrows(ProjectNotFoundException.class,
//                () -> projectTaskService.removeProjectById(UUID1, UUID3));
//        Assert.assertThrows(ProjectNotFoundException.class,
//                () -> projectTaskService.removeProjectById(UUID4, UUID2));
//
//        // TODO PermissionException
//    }
//
//
//
//}
