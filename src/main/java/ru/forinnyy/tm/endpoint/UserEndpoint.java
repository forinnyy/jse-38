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

public class UserEndpoint extends AbstractEndpoint implements IUserEndpoint {

    public UserEndpoint(IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    private IUserService getUserService() {
        return getServiceLocator().getUserService();
    }

    @NonNull
    @Override
    @SneakyThrows
    public UserLockResponse lockUser(@NonNull UserLockRequest request) {
        check(request, Role.ADMIN);
        @NonNull final String login = request.getLogin();
        getUserService().lockUserByLogin(login);
        return new UserLockResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public UserUnlockResponse unlockUser(@NonNull UserUnlockRequest request) {
        check(request, Role.ADMIN);
        @NonNull final String login = request.getLogin();
        getUserService().unlockUserByLogin(login);
        return new UserUnlockResponse();
    }

    @NonNull
    @Override
    @SneakyThrows
    public UserRemoveResponse removeUser(@NonNull UserRemoveRequest request) {
        check(request, Role.ADMIN);
        @NonNull final String login = request.getLogin();
        @NonNull final User user = getUserService().removeByLogin(login);
        return new UserRemoveResponse(user);
    }

    @NonNull
    @Override
    @SneakyThrows
    public UserRegistryResponse registryUser(@NonNull UserRegistryRequest request) {
        @NonNull final String login = request.getLogin();
        @NonNull final String email = request.getEmail();
        @NonNull final String password = request.getPassword();
        @NonNull final User user = getUserService().create(login, password, email);
        return new UserRegistryResponse(user);
    }

    @NonNull
    @Override
    @SneakyThrows
    public UserChangePasswordResponse changeUserPassword(@NonNull UserChangePasswordRequest request) {
        check(request);
        @NonNull final String userId = request.getUserId();
        @NonNull final String password = request.getPassword();
        @NonNull final User user = getUserService().setPassword(userId, password);
        return new UserChangePasswordResponse(user);
    }

    @NonNull
    @Override
    @SneakyThrows
    public UserUpdateProfileResponse updateUserProfile(@NonNull UserUpdateProfileRequest request) {
        check(request);
        @NonNull final String userId = request.getUserId();
        @NonNull final String firstName = request.getFirstName();
        @NonNull final String lastName = request.getLastName();
        @NonNull final String middleName = request.getMiddleName();
        @NonNull final User user = getUserService().updateUser(userId, firstName, lastName, middleName);
        return new UserUpdateProfileResponse(user);
    }

}
