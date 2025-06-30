package ru.forinnyy.tm.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import ru.forinnyy.tm.AbstractTest;
import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.api.repository.ISessionRepository;
import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.api.repository.IUserRepository;
import ru.forinnyy.tm.api.service.IPropertyService;
import ru.forinnyy.tm.api.service.ISessionService;
import ru.forinnyy.tm.api.service.IUserService;
import ru.forinnyy.tm.exception.field.LoginEmptyException;
import ru.forinnyy.tm.exception.field.PasswordEmptyException;
import ru.forinnyy.tm.exception.user.AccessDeniedException;
import ru.forinnyy.tm.exception.user.PermissionException;
import ru.forinnyy.tm.model.Session;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.repository.ProjectRepository;
import ru.forinnyy.tm.repository.SessionRepository;
import ru.forinnyy.tm.repository.TaskRepository;
import ru.forinnyy.tm.repository.UserRepository;

import javax.security.sasl.AuthenticationException;
import java.util.Date;


public final class AuthServiceTest extends AbstractTest {

    private final IUserRepository userRepository = new UserRepository();
    private final IProjectRepository projectRepository = new ProjectRepository();
    private final ITaskRepository taskRepository = new TaskRepository();
    private final IPropertyService propertyService = new PropertyService();
    private final ISessionRepository sessionRepository = new SessionRepository();


    private final ISessionService sessionService = new SessionService(sessionRepository);
    private final IUserService userService = new UserService(
            propertyService, userRepository, projectRepository, taskRepository
    );
    private final AuthService authService = new AuthService(
            propertyService, userService, sessionService
    );

    @Test
    @SneakyThrows
    public void testLoginAndToken() {
        userService.create("testUser", "testPassword");
        @NonNull final User lockedUser = userService.create("lockedUser", "lockedPassword");
        lockedUser.setLocked(true);

        String token = authService.login("testUser", "testPassword");
        Assert.assertNotNull(token);

        Assert.assertThrows(LoginEmptyException.class,
                () -> authService.login(null, "testPassword"));
        Assert.assertThrows(LoginEmptyException.class,
                () -> authService.login(EMPTY_STRING, "testPassword"));

        Assert.assertThrows(PasswordEmptyException.class,
                () -> authService.login("testUser", null));
        Assert.assertThrows(PasswordEmptyException.class,
                () -> authService.login("testUser", EMPTY_STRING));

        Assert.assertThrows(AuthenticationException.class,
                () -> authService.login("testUser", "wrongPassword"));
        Assert.assertThrows(AccessDeniedException.class,
                () -> authService.login("lockedUser", "lockedPassword"));
    }

    @Test
    @SneakyThrows
    public void testValidateToken() {
        userService.create("testUser", "testPassword", "testUser@example.com");

        String token = authService.login("testUser", "testPassword");
        Session session = authService.validateToken(token);
        Assert.assertNotNull(session);

        Assert.assertThrows(AccessDeniedException.class, () -> authService.validateToken(null));

        Assert.assertThrows(AccessDeniedException.class,
                () -> authService.validateToken("invalidToken"));

        authService.invalidate(session);

        authService.invalidate(null);
        Assert.assertThrows(AccessDeniedException.class,
                () -> authService.validateToken(token));

    }

    @Test
    @SneakyThrows
    public void testInvalidate() {
        userService.create("testUser", "testPassword", "testUser@example.com");

        String token = authService.login("testUser", "testPassword");
        Session session = authService.validateToken(token);
        System.out.println(session.getDate());
        Date timeoutSessionDate = Date.from(session.getDate().toInstant().plusMillis(11000));
        session.setDate(timeoutSessionDate);
        System.out.println(session.getDate());
    }

    @Test
    @SneakyThrows
    public void testTimeout() {
        userService.create("testUser", "testPassword", "testUser@example.com");
        String token = authService.login("testUser", "testPassword");
        Session session = authService.validateToken(token);

        System.out.println(session.getDate());

        Date timeoutSessionDate = Date.from(session.getDate().toInstant().plusSeconds(3 * 60 * 1000 + 1000));
        session.setDate(timeoutSessionDate);

        System.out.println(session.getDate());

//        Assert.assertThrows(AccessDeniedException.class, () -> authService.validateToken(token));
    }

    @Test
    @SneakyThrows
    public void testRegistry() {
        User user = authService.registry("newUser", "newPassword", "newUser@example.com");
        Assert.assertEquals("newUser", user.getLogin());

        Assert.assertThrows(NullPointerException.class,
                () -> authService.registry(null, null, null));
        Assert.assertThrows(NullPointerException.class,
                () -> authService.registry(null, null, "email@example.com"));
        Assert.assertThrows(NullPointerException.class,
                () -> authService.registry(null, "password", "email@example.com"));
        Assert.assertThrows(NullPointerException.class,
                () -> authService.registry(null, "password", null));
        Assert.assertThrows(NullPointerException.class,
                () -> authService.registry("newUser", null, "email@example.com"));
        Assert.assertThrows(NullPointerException.class,
                () -> authService.registry("newUser", null, null));
        Assert.assertThrows(NullPointerException.class,
                () -> authService.registry("newUser", "newPassword", null));
    }

    @Test
    @SneakyThrows
    public void testCheck() {
        userService.create("testUser", "testPassword", "testUser@example.com");
        @NonNull final User lockedUser = userService.create("lockedUser", "lockedPassword");
        lockedUser.setLocked(true);

        User checkedUser = authService.check("testUser", "testPassword");
        Assert.assertNotNull(checkedUser);
        Assert.assertEquals("testUser", checkedUser.getLogin());

        Assert.assertThrows(LoginEmptyException.class,
                () -> authService.check(null , "testPassword"));
        Assert.assertThrows(LoginEmptyException.class,
                () -> authService.check(EMPTY_STRING, "testPassword"));

        Assert.assertThrows(PasswordEmptyException.class,
                () -> authService.check("testUser" , null));
        Assert.assertThrows(PasswordEmptyException.class,
                () -> authService.check("testUser", EMPTY_STRING));

        Assert.assertThrows(PermissionException.class,
                () -> authService.check("lockedUser", "lockedPassword"));
        Assert.assertThrows(PermissionException.class,
                () -> authService.check("testUser", "wrongPassword"));
    }

}

