//package ru.forinnyy.tm.endpoint;
//
//import lombok.NonNull;
//import org.junit.*;
//import org.junit.experimental.categories.Category;
//import ru.forinnyy.tm.api.endpoint.IAuthEndpoint;
//import ru.forinnyy.tm.api.endpoint.IUserEndpoint;
//import ru.forinnyy.tm.api.service.IPropertyService;
//import ru.forinnyy.tm.dto.request.*;
//import ru.forinnyy.tm.dto.response.*;
//import ru.forinnyy.tm.marker.SoapCategory;
//import ru.forinnyy.tm.service.PropertyService;
//
//import javax.xml.ws.soap.SOAPFaultException;
//
//@Category(SoapCategory.class)
//public final class UserEndpointTest {
//
//    @NonNull
//    private static final IPropertyService propertyService = new PropertyService();
//
//    @NonNull
//    private static final IAuthEndpoint authEndpoint = IAuthEndpoint.newInstance(propertyService);
//
//    @NonNull
//    private static final IUserEndpoint userEndpoint = IUserEndpoint.newInstance(propertyService);
//
//    private static String adminToken;
//    private String testUserToken;
//
//    private final String testUserLogin = "testuser";
//
//    private final String testUserEmail = "testuser@example.com";
//
//    private final String testUserPassword = "password";
//
//    @BeforeClass
//    public static void beforeClass() {
//        @NonNull final UserLoginRequest adminRequest = new UserLoginRequest("admin", "admin");
//        adminToken = authEndpoint.login(adminRequest).getToken();
//    }
//
//    @Before
//    public void beforeMethod() {
//        createTestUser(testUserLogin, testUserEmail, testUserPassword);
//    }
//
//    @After
//    public void afterMethod() {
//        @NonNull final UserListProfilesRequest profilesRequest = new UserListProfilesRequest(adminToken);
//        @NonNull final UserListProfilesResponse profilesResponse = userEndpoint.listProfiles(profilesRequest);
//
//        if (profilesResponse.getProfiles().contains(testUserLogin)) {
//            @NonNull final UserRemoveRequest removeRequest = new UserRemoveRequest(adminToken);
//            removeRequest.setLogin(testUserLogin);
//            userEndpoint.removeUser(removeRequest);
//        }
//    }
//
//    private void createTestUser(@NonNull final String login, @NonNull final String email, @NonNull final String password) {
//        @NonNull final UserListProfilesRequest profilesRequest = new UserListProfilesRequest(adminToken);
//        @NonNull final UserListProfilesResponse profilesResponse = userEndpoint.listProfiles(profilesRequest);
//
//        if (profilesResponse.getProfiles().contains(login)) {
//            @NonNull final UserRemoveRequest removeRequest = new UserRemoveRequest(adminToken);
//            removeRequest.setLogin(login);
//            userEndpoint.removeUser(removeRequest);
//        }
//
//        @NonNull final UserRegistryRequest registryRequest = new UserRegistryRequest();
//        registryRequest.setLogin(login);
//        registryRequest.setEmail(email);
//        registryRequest.setPassword(password);
//        userEndpoint.registryUser(registryRequest);
//
//        @NonNull final UserLoginRequest loginRequest = new UserLoginRequest(login, password);
//        testUserToken = authEndpoint.login(loginRequest).getToken();
//    }
//
//    @Test
//    public void testRemoveUser() {
//        @NonNull final UserRemoveRequest request = new UserRemoveRequest(adminToken);
//        request.setLogin(testUserLogin);
//        @NonNull final UserRemoveResponse response = userEndpoint.removeUser(request);
//        Assert.assertNotNull(response);
//    }
//
//    @Test
//    public void testLockAndUnlockUser() {
//        @NonNull final UserLockRequest lockRequest = new UserLockRequest(adminToken);
//        lockRequest.setLogin(testUserLogin);
//        userEndpoint.lockUser(lockRequest);
//
//        Assert.assertThrows(SOAPFaultException.class,
//                () -> authEndpoint.login(new UserLoginRequest(testUserLogin, testUserPassword)));
//
//        @NonNull final UserUnlockRequest unlockRequest = new UserUnlockRequest(adminToken);
//        unlockRequest.setLogin(testUserLogin);
//        userEndpoint.unlockUser(unlockRequest);
//
//        @NonNull final String token = authEndpoint.login(new UserLoginRequest(testUserLogin, testUserPassword)).getToken();
//        Assert.assertNotNull(token);
//    }
//
//    @Test
//    public void testChangeUserPassword() {
//        @NonNull final String newPass = "newpassword";
//        @NonNull final UserChangePasswordRequest request = new UserChangePasswordRequest(testUserToken);
//        request.setPassword(newPass);
//        userEndpoint.changeUserPassword(request);
//
//        @NonNull final String token = authEndpoint.login(new UserLoginRequest(testUserLogin, newPass)).getToken();
//        Assert.assertNotNull(token);
//
//        @NonNull final UserChangePasswordRequest revert = new UserChangePasswordRequest(testUserToken);
//        revert.setPassword(testUserPassword);
//        userEndpoint.changeUserPassword(revert);
//    }
//
//    @Test
//    public void testUpdateUserProfile() {
//        @NonNull final UserUpdateProfileRequest request = new UserUpdateProfileRequest(testUserToken);
//        request.setFirstName("John");
//        request.setLastName("Doe");
//        request.setMiddleName("Middle");
//        @NonNull final UserUpdateProfileResponse response = userEndpoint.updateUserProfile(request);
//        Assert.assertNotNull(response);
//    }
//
//}
