package ru.forinnyy.tm.endpoint;

import lombok.NonNull;
import org.junit.*;
import org.junit.experimental.categories.Category;
import ru.forinnyy.tm.api.endpoint.IAuthEndpoint;
import ru.forinnyy.tm.api.endpoint.IUserEndpoint;
import ru.forinnyy.tm.api.service.IPropertyService;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.UserLoginResponse;
import ru.forinnyy.tm.dto.response.UserLogoutResponse;
import ru.forinnyy.tm.dto.response.UserProfileResponse;
import ru.forinnyy.tm.marker.SoapCategory;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.service.PropertyService;

@Category(SoapCategory.class)
public final class AuthEndpointTest {

    @NonNull
    private static final IPropertyService propertyService = new PropertyService();

    @NonNull
    private static final IAuthEndpoint authEndpoint = IAuthEndpoint.newInstance(propertyService);

    @NonNull
    private static final IUserEndpoint userEndpoint = IUserEndpoint.newInstance(propertyService);

    private static String adminToken;
    private static String testUserToken;

    @BeforeClass
    public static void init() {
        @NonNull final UserLoginRequest adminRequest = new UserLoginRequest("admin", "admin");
        adminToken = authEndpoint.login(adminRequest).getToken();
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
    public void testLogin() {
        @NonNull final UserLoginRequest validRequest = new UserLoginRequest("testuser", "password");
        @NonNull final UserLoginResponse response = authEndpoint.login(validRequest);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getToken());
        Assert.assertFalse(response.getToken().isEmpty());

        @NonNull final UserLoginRequest invalidPasswordRequest = new UserLoginRequest("testuser", "wrongpassword");
        Assert.assertThrows(Exception.class, () -> authEndpoint.login(invalidPasswordRequest));

        @NonNull final UserLoginRequest invalidLoginRequest = new UserLoginRequest("nonexistent", "password");
        Assert.assertThrows(Exception.class, () -> authEndpoint.login(invalidLoginRequest));

        @NonNull final UserLoginRequest nullLoginRequest = new UserLoginRequest(null, "password");
        Assert.assertThrows(Exception.class, () -> authEndpoint.login(nullLoginRequest));

        @NonNull final UserLoginRequest nullPasswordRequest = new UserLoginRequest("testuser", null);
        Assert.assertThrows(Exception.class, () -> authEndpoint.login(nullPasswordRequest));

        @NonNull final UserLoginRequest emptyLoginRequest = new UserLoginRequest("", "password");
        Assert.assertThrows(Exception.class, () -> authEndpoint.login(emptyLoginRequest));

        @NonNull final UserLoginRequest emptyPasswordRequest = new UserLoginRequest("testuser", "");
        Assert.assertThrows(Exception.class, () -> authEndpoint.login(emptyPasswordRequest));
    }

    @Test
    public void testLogout() {
        @NonNull final UserLogoutRequest nullTokenRequest = new UserLogoutRequest(null);
        Assert.assertThrows(Exception.class, () -> authEndpoint.logout(nullTokenRequest));

        @NonNull final UserLogoutRequest invalidTokenRequest = new UserLogoutRequest("invalid-token");
        Assert.assertThrows(Exception.class, () -> authEndpoint.logout(invalidTokenRequest));

        @NonNull final UserLogoutRequest validRequest = new UserLogoutRequest(testUserToken);
        @NonNull final UserLogoutResponse response = authEndpoint.logout(validRequest);

        Assert.assertNotNull(response);

        @NonNull final UserProfileRequest profileRequest = new UserProfileRequest(testUserToken);
        Assert.assertThrows(Exception.class, () -> authEndpoint.profile(profileRequest));
    }

    @Test
    public void testProfile() {
        @NonNull final UserProfileRequest nullTokenRequest = new UserProfileRequest(null);
        Assert.assertThrows(Exception.class, () -> authEndpoint.profile(nullTokenRequest));

        @NonNull final UserProfileRequest invalidTokenRequest = new UserProfileRequest("invalid-token");
        Assert.assertThrows(Exception.class, () -> authEndpoint.profile(invalidTokenRequest));

        @NonNull final UserProfileRequest validRequest = new UserProfileRequest(testUserToken);
        @NonNull final UserProfileResponse response = authEndpoint.profile(validRequest);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getUser());

        @NonNull final User user = response.getUser();
        Assert.assertEquals("testuser", user.getLogin());
        Assert.assertEquals("test@email.com", user.getEmail());
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void testProfileAfterLogout() {
        @NonNull final UserProfileRequest profileRequest = new UserProfileRequest(testUserToken);
        @NonNull final UserProfileResponse profileResponse = authEndpoint.profile(profileRequest);
        Assert.assertNotNull(profileResponse.getUser());

        @NonNull final UserLogoutRequest logoutRequest = new UserLogoutRequest(testUserToken);
        authEndpoint.logout(logoutRequest);

        Assert.assertThrows(Exception.class, () -> authEndpoint.profile(profileRequest));
    }

    @Test
    public void testLoginWithLockedUser() {
        @NonNull final UserLockRequest lockRequest = new UserLockRequest(adminToken);
        lockRequest.setLogin("testuser");
        userEndpoint.lockUser(lockRequest);

        @NonNull final UserLoginRequest loginRequest = new UserLoginRequest("testuser", "password");
        Assert.assertThrows(Exception.class, () -> authEndpoint.login(loginRequest));

        @NonNull final UserUnlockRequest unlockRequest = new UserUnlockRequest(adminToken);
        unlockRequest.setLogin("testuser");
        userEndpoint.unlockUser(unlockRequest);

        @NonNull final UserLoginResponse response = authEndpoint.login(loginRequest);
        Assert.assertNotNull(response.getToken());
    }

    @Test
    public void testMultipleLogins() {
        @NonNull final UserLoginRequest request = new UserLoginRequest("testuser", "password");

        @NonNull final UserLoginResponse response1 = authEndpoint.login(request);
        Assert.assertNotNull(response1.getToken());

        @NonNull final UserLoginResponse response2 = authEndpoint.login(request);
        Assert.assertNotNull(response2.getToken());

        Assert.assertNotEquals(response1.getToken(), response2.getToken());

        @NonNull final UserProfileRequest profileRequest1 = new UserProfileRequest(response1.getToken());
        @NonNull final UserProfileResponse profileResponse1 = authEndpoint.profile(profileRequest1);
        Assert.assertNotNull(profileResponse1.getUser());

        @NonNull final UserProfileRequest profileRequest2 = new UserProfileRequest(response2.getToken());
        @NonNull final UserProfileResponse profileResponse2 = authEndpoint.profile(profileRequest2);
        Assert.assertNotNull(profileResponse2.getUser());
    }

    @Test
    public void testStandardUsers() {
        @NonNull final UserLoginRequest adminRequest = new UserLoginRequest("admin", "admin");
        @NonNull final UserLoginResponse adminResponse = authEndpoint.login(adminRequest);
        Assert.assertNotNull(adminResponse.getToken());

        @NonNull final UserProfileRequest adminProfileRequest = new UserProfileRequest(adminResponse.getToken());
        @NonNull final UserProfileResponse adminProfileResponse = authEndpoint.profile(adminProfileRequest);
        Assert.assertNotNull(adminProfileResponse.getUser());
        Assert.assertEquals("admin", adminProfileResponse.getUser().getLogin());

        @NonNull final UserLoginRequest userRequest = new UserLoginRequest("user", "user");
        @NonNull final UserLoginResponse userResponse = authEndpoint.login(userRequest);
        Assert.assertNotNull(userResponse.getToken());

        @NonNull final UserProfileRequest userProfileRequest = new UserProfileRequest(userResponse.getToken());
        @NonNull final UserProfileResponse userProfileResponse = authEndpoint.profile(userProfileRequest);
        Assert.assertNotNull(userProfileResponse.getUser());
        Assert.assertEquals("user", userProfileResponse.getUser().getLogin());
    }

    @AfterClass
    public static void tearDown() {
        try {
            @NonNull final UserRemoveRequest removeRequest = new UserRemoveRequest(adminToken);
            removeRequest.setLogin("testuser");
            userEndpoint.removeUser(removeRequest);
        } catch (Exception e) {
        }
    }

}