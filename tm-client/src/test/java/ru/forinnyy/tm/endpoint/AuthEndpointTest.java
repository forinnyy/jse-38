package ru.forinnyy.tm.endpoint;

import lombok.NonNull;
import org.junit.*;
import org.junit.experimental.categories.Category;
import ru.forinnyy.tm.api.endpoint.IAuthEndpoint;
import ru.forinnyy.tm.api.endpoint.IUserEndpoint;
import ru.forinnyy.tm.api.service.IPropertyService;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.UserListProfilesResponse;
import ru.forinnyy.tm.dto.response.UserLoginResponse;
import ru.forinnyy.tm.dto.response.UserLogoutResponse;
import ru.forinnyy.tm.dto.response.UserProfileResponse;
import ru.forinnyy.tm.marker.SoapCategory;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.service.PropertyService;

import javax.xml.ws.soap.SOAPFaultException;
import java.util.List;

@Category(SoapCategory.class)
public final class AuthEndpointTest {

    @NonNull
    private static final IPropertyService propertyService = new PropertyService();

    @NonNull
    private static final IAuthEndpoint authEndpoint = IAuthEndpoint.newInstance(propertyService);

    @NonNull
    private static final IUserEndpoint userEndpoint = IUserEndpoint.newInstance(propertyService);

    private static String adminToken;

    private String testUserToken;

    private final String testLogin = "testuser";

    private final String testEmail = "test@email.com";

    private final String testPassword = "password";

    @BeforeClass
    public static void beforeClass() {
        @NonNull final UserLoginRequest adminRequest = new UserLoginRequest("admin", "admin");
        adminToken = authEndpoint.login(adminRequest).getToken();
    }

    @Before
    public void beforeMethod() {
        createTestUser(testLogin, testEmail, testPassword);
    }

    @After
    public void afterMethod() {
        @NonNull final UserListProfilesRequest profilesRequest = new UserListProfilesRequest(adminToken);
        @NonNull final UserListProfilesResponse profilesResponse = userEndpoint.listProfiles(profilesRequest);

        if (profilesResponse.getProfiles().contains(testLogin)) {
            @NonNull final UserRemoveRequest removeRequest = new UserRemoveRequest(adminToken);
            removeRequest.setLogin(testLogin);
            userEndpoint.removeUser(removeRequest);
        }
    }

    private void createTestUser(@NonNull final String login, @NonNull final String email, @NonNull final String password) {
        @NonNull final UserListProfilesRequest profilesRequest = new UserListProfilesRequest(adminToken);
        @NonNull final UserListProfilesResponse profilesResponse = userEndpoint.listProfiles(profilesRequest);
        @NonNull final List<String> profiles = profilesResponse.getProfiles();
        if (profiles.contains(login)) {
            @NonNull final UserRemoveRequest removeRequest = new UserRemoveRequest(adminToken);
            removeRequest.setLogin(login);
            userEndpoint.removeUser(removeRequest);
        }

        UserRegistryRequest registryRequest = new UserRegistryRequest();
        registryRequest.setLogin(login);
        registryRequest.setEmail(email);
        registryRequest.setPassword(password);
        userEndpoint.registryUser(registryRequest);

        @NonNull final UserLoginRequest loginRequest = new UserLoginRequest(testLogin, testPassword);
        testUserToken = authEndpoint.login(loginRequest).getToken();
    }

    @Test
    public void testProfile() {
        Assert.assertThrows(SOAPFaultException.class,
                () -> authEndpoint.profile(new UserProfileRequest(null)));

        Assert.assertThrows(SOAPFaultException.class,
                () -> authEndpoint.profile(new UserProfileRequest("invalid-token")));

        @NonNull final UserProfileRequest validRequest = new UserProfileRequest(testUserToken);
        @NonNull final UserProfileResponse response = authEndpoint.profile(validRequest);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getUser());

        @NonNull final User user = response.getUser();
        Assert.assertEquals(testLogin, user.getLogin());
        Assert.assertEquals(testEmail, user.getEmail());
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void testLogin() {
        @NonNull final UserLoginRequest validRequest = new UserLoginRequest(testLogin, testPassword);
        @NonNull final UserLoginResponse response = authEndpoint.login(validRequest);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getToken());
        Assert.assertFalse(response.getToken().isEmpty());

        Assert.assertThrows(SOAPFaultException.class,
                () -> authEndpoint.login(new UserLoginRequest(testLogin, "wrongpassword")));

        Assert.assertThrows(SOAPFaultException.class,
                () -> authEndpoint.login(new UserLoginRequest("nonexistent", testPassword)));

        Assert.assertThrows(SOAPFaultException.class,
                () -> authEndpoint.login(new UserLoginRequest(null, testPassword)));

        Assert.assertThrows(SOAPFaultException.class,
                () -> authEndpoint.login(new UserLoginRequest(testLogin, null)));
    }

    @Test
    public void testLogout() {
        Assert.assertThrows(SOAPFaultException.class,
                () -> authEndpoint.logout(new UserLogoutRequest(null)));

        Assert.assertThrows(SOAPFaultException.class,
                () -> authEndpoint.logout(new UserLogoutRequest("invalid-token")));

        @NonNull final UserLogoutRequest validRequest = new UserLogoutRequest(testUserToken);
        @NonNull final UserLogoutResponse response = authEndpoint.logout(validRequest);
        Assert.assertNotNull(response);

        Assert.assertThrows(SOAPFaultException.class,
                () -> authEndpoint.profile(new UserProfileRequest(testUserToken)));
    }

}