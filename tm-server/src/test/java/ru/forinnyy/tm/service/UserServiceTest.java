package ru.forinnyy.tm.service;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.forinnyy.tm.AbstractTest;
import ru.forinnyy.tm.exception.entity.UserNotFoundException;
import ru.forinnyy.tm.exception.field.EmailEmptyException;
import ru.forinnyy.tm.exception.field.LoginEmptyException;
import ru.forinnyy.tm.exception.field.PasswordEmptyException;
import ru.forinnyy.tm.exception.field.RoleEmptyException;
import ru.forinnyy.tm.exception.user.ExistsEmailException;
import ru.forinnyy.tm.exception.user.ExistsLoginException;
import ru.forinnyy.tm.repository.ProjectRepository;
import ru.forinnyy.tm.repository.TaskRepository;
import ru.forinnyy.tm.repository.UserRepository;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.util.HashUtil;

public final class UserServiceTest extends AbstractTest {

    private UserService userService;
    private PropertyService propertyService;
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository = new UserRepository();
        ProjectRepository projectRepository = new ProjectRepository();
        TaskRepository taskRepository = new TaskRepository();
        propertyService = new PropertyService();
        userService = new UserService(propertyService, userRepository, projectRepository, taskRepository);
    }

    @Test
    @SneakyThrows
    public void testCreateUserWithValidLoginAndPassword() {
        User user = userService.create("login", STRING);
        Assert.assertEquals("login", user.getLogin());

        Assert.assertThrows(LoginEmptyException.class, () -> userService.create(null, STRING));
        Assert.assertThrows(LoginEmptyException.class, () -> userService.create(EMPTY_STRING, STRING));

        Assert.assertThrows(PasswordEmptyException.class, () -> userService.create(STRING, null));
        Assert.assertThrows(PasswordEmptyException.class, () -> userService.create(STRING, EMPTY_STRING));

        Assert.assertThrows(ExistsLoginException.class, () -> userService.create("login", STRING));
    }

    @Test
    @SneakyThrows
    public void testCreateUserWithEmail() {
        User user = userService.create("login", STRING, "email");
        Assert.assertEquals("login", user.getLogin());

        Assert.assertThrows(LoginEmptyException.class, () -> userService.create(null, STRING, STRING));
        Assert.assertThrows(LoginEmptyException.class, () -> userService.create(EMPTY_STRING, STRING, STRING));

        Assert.assertThrows(PasswordEmptyException.class, () -> userService.create(STRING, null, STRING));
        Assert.assertThrows(PasswordEmptyException.class, () -> userService.create(STRING, EMPTY_STRING, STRING));

        Assert.assertThrows(ExistsLoginException.class, () -> userService.create("login", STRING, STRING));
        Assert.assertThrows(ExistsEmailException.class, () -> userService.create(STRING, STRING, "email"));
    }

    @Test
    @SneakyThrows
    public void testCreateUserWithRole() {
        User user = userService.create("login", STRING, Role.ADMIN);
        Assert.assertEquals("login", user.getLogin());

        Assert.assertThrows(LoginEmptyException.class, () -> userService.create(null, STRING, Role.ADMIN));
        Assert.assertThrows(LoginEmptyException.class, () -> userService.create(EMPTY_STRING, STRING, Role.ADMIN));

        Assert.assertThrows(PasswordEmptyException.class, () -> userService.create(STRING, null, Role.ADMIN));
        Assert.assertThrows(PasswordEmptyException.class, () -> userService.create(STRING, EMPTY_STRING, Role.ADMIN));

        Assert.assertThrows(ExistsLoginException.class, () -> userService.create("login", STRING, Role.ADMIN));
        Assert.assertThrows(RoleEmptyException.class, () -> userService.create(STRING, STRING, (Role) null));
    }


    @Test
    @SneakyThrows
    public void testRemoveByLogin() {
        String login = "testUser";
        User user = userService.create(login, "testPassword");

        User removedUser = userService.removeByLogin(login);
        Assert.assertNotNull(removedUser);
        Assert.assertEquals(login, removedUser.getLogin());
    }

    @Test
    public void testRemoveByLoginWhenUserNotFound() {
        String login = "nonExistentUser";

        Assert.assertThrows(UserNotFoundException.class, () -> userService.removeByLogin(login));
    }

    @Test
    @SneakyThrows
    public void testLockUserByLogin() {
        String login = "testUser";
        User user = userService.create(login, "testPassword");

        userService.lockUserByLogin(login);
        Assert.assertTrue(user.isLocked());
    }

    @Test
    @SneakyThrows
    public void testUnlockUserByLogin() {
        String login = "testUser";
        User user = userService.create(login, "testPassword");
        user.setLocked(true);

        userService.unlockUserByLogin(login);
        Assert.assertFalse(user.isLocked());
    }

    @Test
    @SneakyThrows
    public void testSetPassword() {
        User user = userService.create("testUser", "oldPassword");
        user.setId(UUID1);

        userRepository.add(user);

        User updatedUser = userService.setPassword(UUID1, "newPassword");
        Assert.assertNotNull(updatedUser);
        Assert.assertEquals(HashUtil.salt(propertyService, "newPassword"), updatedUser.getPasswordHash());
    }

    @Test
    @SneakyThrows
    public void testSetPasswordWhenPasswordEmpty() {
        String userId = UUID1;

        User user = userService.create("testUser", "testPassword");
        user.setId(userId);

        Assert.assertThrows(PasswordEmptyException.class, () -> userService.setPassword(userId, ""));
    }

    @Test
    @SneakyThrows
    public void testIsLoginExist() {
        String login = "testUser";
        userService.create(login, "testPassword");

        Boolean result = userService.isLoginExist(login);
        Assert.assertTrue(result);
    }

    @Test
    @SneakyThrows
    public void testIsEmailExist() {
        String email = "testUser@example.com";
        userService.create("testUser", "testPassword", email);

        Boolean result = userService.isEmailExist(email);
        Assert.assertTrue(result);
    }

    @Test
    public void testFindByLoginWhenLoginIsEmpty() {
        String login = "";

        Assert.assertThrows(LoginEmptyException.class, () -> userService.findByLogin(login));
    }

    @Test
    public void testFindByLoginWhenEmailIsEmpty() {
        String email = "";

        Assert.assertThrows(ExistsEmailException.class, () -> userService.findByEmail(email));
    }

    @Test
    public void testFindByLoginWhenUserDoesNotExist() {
        String login = "nonExistentUser";

        Assert.assertThrows(UserNotFoundException.class, () -> userService.findByLogin(login));
    }

    @Test
    public void testLockUserByLoginWhenUserDoesNotExist() {
        String login = "nonExistentUser";

        Assert.assertThrows(UserNotFoundException.class, () -> userService.lockUserByLogin(login));
    }

    @Test
    public void testUnlockUserByLoginWhenUserDoesNotExist() {
        String login = "nonExistentUser";

        Assert.assertThrows(UserNotFoundException.class, () -> userService.unlockUserByLogin(login));
    }

}
