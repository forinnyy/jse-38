package ru.forinnyy.tm.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.forinnyy.tm.AbstractTest;
import ru.forinnyy.tm.component.Bootstrap;
import ru.forinnyy.tm.dto.Domain;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.model.Project;
import ru.forinnyy.tm.model.Task;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.util.HashUtil;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class DomainServiceTest extends AbstractTest {

    private DomainService domainService;
    private List<Project> projects;
    private List<Task> tasks;
    private List<User> users;

    @Before
    public void init() {
        Bootstrap bootstrap = new Bootstrap();
        domainService = (DomainService) bootstrap.getDomainService();
        projects = Arrays.asList(
                new Project("Test Project 1", Status.NOT_STARTED),
                new Project("Test Project 2", Status.COMPLETED)
        );
        tasks = Arrays.asList(new Task("Test Task 1"), new Task("Test Task 2"));

        User admin = new User();
        admin.setLogin("admin");
        admin.setPasswordHash(HashUtil.salt(bootstrap.getPropertyService(), "password"));
        admin.setEmail("admin@mail.ru");
        admin.setRole(Role.ADMIN);

        User test = new User();
        test.setLogin("test");
        test.setPasswordHash(HashUtil.salt(bootstrap.getPropertyService(), "password"));
        test.setEmail("test@mail.ru");
        test.setRole(Role.USUAL);

        users = Arrays.asList(admin, test);
    }

    private void setData() {
        Domain domain = new Domain();
        domain.setProjects(projects);
        domain.setTasks(tasks);
        domain.setUsers(users);
        domainService.setDomain(domain);
    }

    @Ignore
    @Test
    public void testSetDomain() {
        setData();

        Domain domain = domainService.getDomain();

        assertNotNull(domain.getProjects());
        assertNotNull(domain.getTasks());
        assertNotNull(domain.getUsers());

        assertEquals(2, domain.getProjects().size());
        assertEquals("Test Project 1", domain.getProjects().get(0).getName());
        assertEquals("Test Project 2", domain.getProjects().get(1).getName());

        assertEquals(2, domain.getTasks().size());
        assertEquals("Test Task 1", domain.getTasks().get(0).getName());
        assertEquals("Test Task 2", domain.getTasks().get(1).getName());

        assertEquals(2, domain.getUsers().size());
        assertEquals("admin", domain.getUsers().get(0).getLogin());
        assertEquals("test", domain.getUsers().get(1).getLogin());
    }

    @Ignore
    @Test
    public void testSaveAndLoadDataBackup() throws Exception {
        setData();
        domainService.saveDataBackup();
        domainService.loadDataBackup();

        Domain domain = domainService.getDomain();

        assertNotNull(domain.getProjects());
        assertNotNull(domain.getTasks());
        assertNotNull(domain.getUsers());

        assertEquals(2, domain.getProjects().size());
        assertEquals("Test Project 1", domain.getProjects().get(0).getName());
        assertEquals("Test Project 2", domain.getProjects().get(1).getName());

        assertEquals(2, domain.getTasks().size());
        assertEquals("Test Task 1", domain.getTasks().get(0).getName());
        assertEquals("Test Task 2", domain.getTasks().get(1).getName());

        assertEquals(2, domain.getUsers().size());
        assertEquals("admin", domain.getUsers().get(0).getLogin());
        assertEquals("test", domain.getUsers().get(1).getLogin());
    }

    @Ignore
    @Test
    public void testSaveAndLoadDataBase64() throws Exception {
        setData();
        domainService.saveDataBase64();
        domainService.loadDataBase64();

        Domain domain = domainService.getDomain();

        assertNotNull(domain.getProjects());
        assertNotNull(domain.getTasks());
        assertNotNull(domain.getUsers());

        assertEquals(2, domain.getProjects().size());
        assertEquals("Test Project 1", domain.getProjects().get(0).getName());
        assertEquals("Test Project 2", domain.getProjects().get(1).getName());

        assertEquals(2, domain.getTasks().size());
        assertEquals("Test Task 1", domain.getTasks().get(0).getName());
        assertEquals("Test Task 2", domain.getTasks().get(1).getName());

        assertEquals(2, domain.getUsers().size());
        assertEquals("admin", domain.getUsers().get(0).getLogin());
        assertEquals("test", domain.getUsers().get(1).getLogin());
    }

    @Ignore
    @Test
    public void testSaveAndLoadDataJson() throws Exception {
        setData();
        domainService.saveDataJsonFasterXml();
        domainService.loadDataJsonFasterXml();

        Domain domain = domainService.getDomain();

        assertNotNull(domain.getProjects());
        assertNotNull(domain.getTasks());
        assertNotNull(domain.getUsers());

        assertEquals(2, domain.getProjects().size());
        assertEquals("Test Project 1", domain.getProjects().get(0).getName());
        assertEquals("Test Project 2", domain.getProjects().get(1).getName());

        assertEquals(2, domain.getTasks().size());
        assertEquals("Test Task 1", domain.getTasks().get(0).getName());
        assertEquals("Test Task 2", domain.getTasks().get(1).getName());

        assertEquals(2, domain.getUsers().size());
        assertEquals("admin", domain.getUsers().get(0).getLogin());
        assertEquals("test", domain.getUsers().get(1).getLogin());
    }

    @Ignore
    @Test
    public void testFileExistenceAfterSave() throws Exception {
        File backupFile = new File(DomainService.FILE_BACKUP);
        if (backupFile.exists()) {
            backupFile.delete();
        }
        domainService.saveDataBackup();
        assertTrue(backupFile.exists());
        backupFile.delete();
    }

    @Ignore
    @Test
    public void testFilterProjects() {
        setData();

        List<Project> completedProjects = domainService.getDomain().getProjects().stream()
                .filter(project -> project.getStatus() == Status.COMPLETED)
                .collect(Collectors.toList());

        assertEquals(1, completedProjects.size());
        assertEquals("Test Project 2", completedProjects.get(0).getName());
    }

    @After
    public void cleanup() {
        new File(DomainService.FILE_BACKUP).delete();
        new File(DomainService.FILE_BASE64).delete();
        new File(DomainService.FILE_JSON).delete();
        new File(DomainService.FILE_XML).delete();
        new File(DomainService.FILE_YAML).delete();
        domainService.setDomain(null);
    }

}
