package ru.forinnyy.tm.endpoint;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.endpoint.IUserEndpoint;
import ru.forinnyy.tm.api.service.IServiceLocator;
import ru.forinnyy.tm.api.service.IUserService;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.model.User;

public final class UserEndpoint extends AbstractEndpoint implements IUserEndpoint {

    public UserEndpoint(IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @NonNull
    private IUserService getUserService() {
        return getServiceLocator().getUserService();
    }

    @NonNull
    @Override
    @SneakyThrows
    public UserLockResponse lockUser(@NonNull final UserLockRequest request) {
        check(request, Role.ADMIN);
        final String login = request.getLogin();
        getUserService().lockUserByLogin(login);
        return new UserLockResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public UserUnlockResponse unlockUser(@NonNull final UserUnlockRequest request) {
        check(request, Role.ADMIN);
        final String login = request.getLogin();
        getUserService().unlockUserByLogin(login);
        return new UserUnlockResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public UserRemoveResponse removeUser(@NonNull final UserRemoveRequest request) {
        check(request, Role.ADMIN);
        final String login = request.getLogin();
        final User user = getUserService().removeByLogin(login);
        return new UserRemoveResponse(user);
    }

    @NonNull
    @Override
    @SneakyThrows
    public UserRegistryResponse registryUser(@NonNull final UserRegistryRequest request) {
        final String login = request.getLogin();
        final String email = request.getEmail();
        final String password = request.getPassword();
        final User user = getUserService().create(login, password, email);
        return new UserRegistryResponse(user);
    }

    @NonNull
    @Override
    @SneakyThrows
    public UserChangePasswordResponse changeUserPassword(@NonNull final UserChangePasswordRequest request) {
        check(request);
        final String userId = request.getUserId();
        final String password = request.getPassword();
        final User user = getUserService().setPassword(userId, password);
        return new UserChangePasswordResponse(user);
    }

    @NonNull
    @Override
    @SneakyThrows
    public UserUpdateProfileResponse updateUserProfile(@NonNull final UserUpdateProfileRequest request) {
        check(request);
        final String userId = request.getUserId();
        final String firstName = request.getFirstName();
        final String lastName = request.getLastName();
        final String middleName = request.getMiddleName();
        final User user = getUserService().updateUser(userId, firstName, lastName, middleName);
        return new UserUpdateProfileResponse(user);
    }

    @NonNull
    @Override
    @SneakyThrows
    public UserLoginResponse loginUser(@NonNull final UserLoginRequest request) {
        final String login = request.getLogin();
        final String password = request.getPassword();
        getServiceLocator().getAuthService().login(login, password);
        return new UserLoginResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public UserLogoutResponse logoutUser(@NonNull final UserLogoutRequest request) {
        check(request);
        getServiceLocator().getAuthService().logout();
        return new UserLogoutResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public UserViewProfileResponse viewProfileUser(@NonNull final UserViewProfileRequest request) {
        check(request);
        final String userId = request.getUserId();
        final User user = getUserService().findOneById(userId);
        System.out.println("[USER VIEW PROFILE]");
        System.out.println("ID: " + user.getId());
        System.out.println("LOGIN: " + user.getLogin());
        System.out.println("FIRST NAME: " + user.getFirstName());
        System.out.println("MIDDLE NAME: " + user.getMiddleName());
        System.out.println("LAST NAME: " + user.getLastName());
        System.out.println("EMAIL: " + user.getEmail());
        System.out.println("ROLE: " + user.getRole());
        return new UserViewProfileResponse();
    }

}
