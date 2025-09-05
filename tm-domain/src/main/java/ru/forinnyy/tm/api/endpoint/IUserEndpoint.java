package ru.forinnyy.tm.api.endpoint;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IUserEndpoint extends IEndpoint {

    @NonNull
    String NAME = "UserEndpoint";

    @NonNull
    String PART = NAME + "Service";

    @SneakyThrows
    @WebMethod(exclude = true)
    static IUserEndpoint newInstance() {
        return newInstance(HOST, PORT);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static IUserEndpoint newInstance(@NonNull final IConnectionProvider connectionProvider) {
        return IEndpoint.newInstance(connectionProvider, NAME, SPACE, PART, IUserEndpoint.class);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static IUserEndpoint newInstance(@NonNull final String host, @NonNull final String port) {
        return IEndpoint.newInstanse(host, port, NAME, SPACE, PART, IUserEndpoint.class);
    }

    @NonNull
    @WebMethod
    UserLockResponse lockUser(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull UserLockRequest request
    );

    @NonNull
    @WebMethod
    UserUnlockResponse unlockUser(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull UserUnlockRequest request
    );

    @NonNull
    @WebMethod
    UserRemoveResponse removeUser(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull UserRemoveRequest request
    );

    @NonNull
    @WebMethod
    UserRegistryResponse registryUser(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull UserRegistryRequest request
    );

    @NonNull
    @WebMethod
    UserChangePasswordResponse changeUserPassword(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull UserChangePasswordRequest request
    );

    @NonNull
    @WebMethod
    UserUpdateProfileResponse updateUserProfile(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull UserUpdateProfileRequest request
    );

    @NonNull
    @WebMethod
    UserListProfilesResponse listProfiles(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull UserListProfilesRequest request
    );

}
