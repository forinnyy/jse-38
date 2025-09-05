package ru.forinnyy.tm.endpoint;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.endpoint.IUserEndpoint;
import ru.forinnyy.tm.api.service.IServiceLocator;
import ru.forinnyy.tm.api.service.IUserService;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.model.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.forinnyy.tm.api.endpoint.IUserEndpoint")
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
    @WebMethod
    @SneakyThrows
    public UserLockResponse lockUser(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final UserLockRequest request
    ) {
        check(request, Role.ADMIN);
        final String login = request.getLogin();
        getUserService().lockUserByLogin(login);
        return new UserLockResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public UserUnlockResponse unlockUser(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final UserUnlockRequest request
    ) {
        check(request, Role.ADMIN);
        final String login = request.getLogin();
        getUserService().unlockUserByLogin(login);
        return new UserUnlockResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public UserRemoveResponse removeUser(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final UserRemoveRequest request
    ) {
        check(request, Role.ADMIN);
        final String login = request.getLogin();
        getUserService().removeByLogin(login);
        return new UserRemoveResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public UserRegistryResponse registryUser(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final UserRegistryRequest request
    ) {
        final String login = request.getLogin();
        final String email = request.getEmail();
        final String password = request.getPassword();
        getUserService().create(login, password, email);
        return new UserRegistryResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public UserChangePasswordResponse changeUserPassword(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final UserChangePasswordRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final String password = request.getPassword();
        getUserService().setPassword(userId, password);
        return new UserChangePasswordResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public UserUpdateProfileResponse updateUserProfile(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final UserUpdateProfileRequest request
    ) {
        @NonNull final Session session = check(request);
        final String userId = session.getUserId();
        final String firstName = request.getFirstName();
        final String lastName = request.getLastName();
        final String middleName = request.getMiddleName();
        getUserService().updateUser(userId, firstName, lastName, middleName);
        return new UserUpdateProfileResponse();
    }

    @NonNull
    @Override
    @WebMethod
    @SneakyThrows
    public UserListProfilesResponse listProfiles(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final UserListProfilesRequest request
    ) {
        check(request, Role.ADMIN);
        @NonNull final List<String> profiles = getUserService().listProfiles();
        return new UserListProfilesResponse(profiles);
    }

}
