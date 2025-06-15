package ru.forinnyy.tm.api.endpoint;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.request.UserLoginRequest;
import ru.forinnyy.tm.dto.request.UserLogoutRequest;
import ru.forinnyy.tm.dto.request.UserProfileRequest;
import ru.forinnyy.tm.dto.response.UserLoginResponse;
import ru.forinnyy.tm.dto.response.UserLogoutResponse;
import ru.forinnyy.tm.dto.response.UserProfileResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IAuthEndpoint extends IEndpoint {

    @NonNull
    String NAME = "AuthEndpoint";

    @NonNull
    String PART = NAME  + "Service";

    @SneakyThrows
    @WebMethod(exclude = true)
    static IAuthEndpoint newInstance() {
        return newInstance(HOST, PORT);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static IAuthEndpoint newInstance(@NonNull final IConnectionProvider connectionProvider) {
        return IEndpoint.newInstance(connectionProvider, NAME, SPACE, PART, IAuthEndpoint.class);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static IAuthEndpoint newInstance(@NonNull final String host, @NonNull final String port) {
        return IEndpoint.newInstanse(host, port, NAME, SPACE, PART, IAuthEndpoint.class);
    }

    @NonNull
    @WebMethod
    UserLoginResponse login(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull UserLoginRequest request
    );

    @NonNull
    @WebMethod
    UserLogoutResponse logout(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull UserLogoutRequest request
    );

    @NonNull
    @WebMethod
    UserProfileResponse profile(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull UserProfileRequest request
    );

}
