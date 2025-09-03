package ru.forinnyy.tm.endpoint;

import lombok.NonNull;
import org.junit.*;
import org.junit.experimental.categories.Category;
import ru.forinnyy.tm.api.endpoint.IAuthEndpoint;
import ru.forinnyy.tm.api.endpoint.IUserEndpoint;
import ru.forinnyy.tm.api.service.IPropertyService;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.UserRegistryResponse;
import ru.forinnyy.tm.marker.SoapCategory;
import ru.forinnyy.tm.service.PropertyService;

@Category(SoapCategory.class)
public final class UserEndpointTest {

    @NonNull
    private static final IPropertyService propertyService = new PropertyService();

    @NonNull
    private static final IAuthEndpoint authEndpoint = IAuthEndpoint.newInstance(propertyService);

    @NonNull
    private static final IUserEndpoint userEndpoint = IUserEndpoint.newInstance(propertyService);

    private static String adminToken;
    private static String userToken;
    private static String testUserToken;

    @BeforeClass
    public static void init() {
        @NonNull final UserLoginRequest adminRequest = new UserLoginRequest("admin", "admin");
        adminToken = authEndpoint.login(adminRequest).getToken();
        @NonNull final UserLoginRequest userRequest = new UserLoginRequest("user", "user");
        userToken = authEndpoint.login(userRequest).getToken();
    }

    @Before
    public void beforeMethod() {
        createTestUser("testuser", "test@email.com", "password");
        @NonNull final UserLoginRequest testUserRequest = new UserLoginRequest("testuser", "password");
        testUserToken = authEndpoint.login(testUserRequest).getToken();
    }

    private void createTestUser(@NonNull final String login, @NonNull final String email, @NonNull final String password) {
            try {
                @NonNull final UserRemoveRequest removeRequest = new UserRemoveRequest(adminToken);
                removeRequest.setLogin(login);
                userEndpoint.removeUser(removeRequest);
            } catch (Exception e) {
            }

            @NonNull final UserRegistryRequest registryRequest = new UserRegistryRequest();
            registryRequest.setLogin(login);
            registryRequest.setEmail(email);
            registryRequest.setPassword(password);
            userEndpoint.registryUser(registryRequest);

            try {
                @NonNull final UserUnlockRequest unlockRequest = new UserUnlockRequest(adminToken);
                unlockRequest.setLogin(login);
                userEndpoint.unlockUser(unlockRequest);
            } catch (Exception e) {
            }
    }

    @Test
    public void testRegistryUser() {
        @NonNull final UserRegistryRequest nullRequest = new UserRegistryRequest();
        Assert.assertThrows(Exception.class, () -> userEndpoint.registryUser(nullRequest));

        @NonNull final UserRegistryRequest emptyRequest = new UserRegistryRequest();
        emptyRequest.setLogin("");
        emptyRequest.setEmail("");
        emptyRequest.setPassword("");
        Assert.assertThrows(Exception.class, () -> userEndpoint.registryUser(emptyRequest));

        @NonNull final UserRegistryRequest request = new UserRegistryRequest();
        request.setLogin("newuser");
        request.setEmail("newuser@email.com");
        request.setPassword("password123");

        @NonNull final UserRegistryResponse response = userEndpoint.registryUser(request);
        Assert.assertNotNull(response);

        @NonNull final UserLoginRequest loginRequest = new UserLoginRequest("newuser", "password123");
        @NonNull final String token = authEndpoint.login(loginRequest).getToken();
        Assert.assertNotNull(token);
    }

    @Test
    public void testLockUser() {
        @NonNull final UserLockRequest nullTokenRequest = new UserLockRequest(null);
        nullTokenRequest.setLogin("testuser");
        Assert.assertThrows(Exception.class, () -> userEndpoint.lockUser(nullTokenRequest));

        @NonNull final UserLockRequest userTokenRequest = new UserLockRequest(userToken);
        userTokenRequest.setLogin("testuser");
        Assert.assertThrows(Exception.class, () -> userEndpoint.lockUser(userTokenRequest));

        @NonNull final UserLockRequest request = new UserLockRequest(adminToken);
        request.setLogin("testuser");
        userEndpoint.lockUser(request);

        @NonNull final UserLoginRequest loginRequest = new UserLoginRequest("testuser", "password");
        Assert.assertThrows(Exception.class, () -> authEndpoint.login(loginRequest));
    }

    @Test
    public void testUnlockUser() {
        @NonNull final UserLockRequest lockRequest = new UserLockRequest(adminToken);
        lockRequest.setLogin("testuser");
        userEndpoint.lockUser(lockRequest);

        @NonNull final UserUnlockRequest nullTokenRequest = new UserUnlockRequest(null);
        nullTokenRequest.setLogin("testuser");
        Assert.assertThrows(Exception.class, () -> userEndpoint.unlockUser(nullTokenRequest));

        @NonNull final UserUnlockRequest userTokenRequest = new UserUnlockRequest(userToken);
        userTokenRequest.setLogin("testuser");
        Assert.assertThrows(Exception.class, () -> userEndpoint.unlockUser(userTokenRequest));

        @NonNull final UserUnlockRequest request = new UserUnlockRequest(adminToken);
        request.setLogin("testuser");
        userEndpoint.unlockUser(request);

        @NonNull final UserLoginRequest loginRequest = new UserLoginRequest("testuser", "password");
        @NonNull final String token = authEndpoint.login(loginRequest).getToken();
        Assert.assertNotNull(token);
    }

    @Test
    public void testRemoveUser() {
        createTestUser("usertoremove", "remove@email.com", "password");

        @NonNull final UserRemoveRequest nullTokenRequest = new UserRemoveRequest(null);
        nullTokenRequest.setLogin("usertoremove");
        Assert.assertThrows(Exception.class, () -> userEndpoint.removeUser(nullTokenRequest));

        @NonNull final UserRemoveRequest userTokenRequest = new UserRemoveRequest(userToken);
        userTokenRequest.setLogin("usertoremove");
        Assert.assertThrows(Exception.class, () -> userEndpoint.removeUser(userTokenRequest));

        @NonNull final UserRemoveRequest request = new UserRemoveRequest(adminToken);
        request.setLogin("usertoremove");
        userEndpoint.removeUser(request);

        @NonNull final UserLoginRequest loginRequest = new UserLoginRequest("usertoremove", "password");
        Assert.assertThrows(Exception.class, () -> authEndpoint.login(loginRequest));
    }

    @Test
    public void testChangeUserPassword() {
        @NonNull final UserChangePasswordRequest nullTokenRequest = new UserChangePasswordRequest(null);
        nullTokenRequest.setPassword("newpassword");
        Assert.assertThrows(Exception.class, () -> userEndpoint.changeUserPassword(nullTokenRequest));

        @NonNull final UserChangePasswordRequest request = new UserChangePasswordRequest(testUserToken);
        request.setPassword("newpassword123");
        userEndpoint.changeUserPassword(request);

        @NonNull final UserLoginRequest loginRequest = new UserLoginRequest("testuser", "newpassword123");
        @NonNull final String token = authEndpoint.login(loginRequest).getToken();
        Assert.assertNotNull(token);

        @NonNull final UserLoginRequest oldLoginRequest = new UserLoginRequest("testuser", "password");
        Assert.assertThrows(Exception.class, () -> authEndpoint.login(oldLoginRequest));
    }

    @Test
    public void testUpdateUserProfile() {
        @NonNull final UserUpdateProfileRequest nullTokenRequest = new UserUpdateProfileRequest(null);
        nullTokenRequest.setFirstName("John");
        nullTokenRequest.setLastName("Doe");
        nullTokenRequest.setMiddleName("Middle");
        Assert.assertThrows(Exception.class, () -> userEndpoint.updateUserProfile(nullTokenRequest));

        @NonNull final UserUpdateProfileRequest request = new UserUpdateProfileRequest(testUserToken);
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setMiddleName("Middle");
        userEndpoint.updateUserProfile(request);
    }

    @Test
    public void testUpdateUserProfileWithInvalidData() {
        @NonNull final UserUpdateProfileRequest emptyRequest = new UserUpdateProfileRequest(testUserToken);
        emptyRequest.setFirstName("");
        emptyRequest.setLastName("");
        emptyRequest.setMiddleName("");

        try {
            userEndpoint.updateUserProfile(emptyRequest);
        } catch (Exception e) {
        }
    }

    @Test
    public void testAdminPrivileges() {
        @NonNull final UserLockRequest userLockRequest = new UserLockRequest(userToken);
        userLockRequest.setLogin("testuser");
        Assert.assertThrows(Exception.class, () -> userEndpoint.lockUser(userLockRequest));

        @NonNull final UserUnlockRequest userUnlockRequest = new UserUnlockRequest(userToken);
        userUnlockRequest.setLogin("testuser");
        Assert.assertThrows(Exception.class, () -> userEndpoint.unlockUser(userUnlockRequest));

        @NonNull final UserRemoveRequest userRemoveRequest = new UserRemoveRequest(userToken);
        userRemoveRequest.setLogin("testuser");
        Assert.assertThrows(Exception.class, () -> userEndpoint.removeUser(userRemoveRequest));
    }

    @AfterClass
    public static void tearDown() {
        try {
            @NonNull final UserRemoveRequest removeRequest1 = new UserRemoveRequest(adminToken);
            removeRequest1.setLogin("testuser");
            userEndpoint.removeUser(removeRequest1);
        } catch (Exception e) {
        }

        try {
            @NonNull final UserRemoveRequest removeRequest2 = new UserRemoveRequest(adminToken);
            removeRequest2.setLogin("newuser");
            userEndpoint.removeUser(removeRequest2);
        } catch (Exception e) {
        }

        try {
            @NonNull final UserRemoveRequest removeRequest3 = new UserRemoveRequest(adminToken);
            removeRequest3.setLogin("usertoremove");
            userEndpoint.removeUser(removeRequest3);
        } catch (Exception e) {
        }
    }

}