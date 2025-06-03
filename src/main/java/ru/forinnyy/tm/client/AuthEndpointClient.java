package ru.forinnyy.tm.client;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.endpoint.IAuthEndpoint;
import ru.forinnyy.tm.dto.request.UserLoginRequest;
import ru.forinnyy.tm.dto.request.UserLogoutRequest;
import ru.forinnyy.tm.dto.request.UserProfileRequest;
import ru.forinnyy.tm.dto.response.UserLoginResponse;
import ru.forinnyy.tm.dto.response.UserLogoutResponse;
import ru.forinnyy.tm.dto.response.UserProfileResponse;

@NoArgsConstructor
public final class AuthEndpointClient extends AbstractEndpointClient implements IAuthEndpoint {

    public AuthEndpointClient(@NonNull AbstractEndpointClient client) {
        super(client);
    }

    @NonNull
    @Override
    @SneakyThrows
    public UserLoginResponse login(@NonNull final UserLoginRequest request) {
        return call(request, UserLoginResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public UserLogoutResponse logout(@NonNull final UserLogoutRequest request) {
        return call(request, UserLogoutResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public UserProfileResponse profile(@NonNull final UserProfileRequest request) {
        return call(request, UserProfileResponse.class);
    }

    @SneakyThrows
    public static void main(String[] args) {
        @NonNull final AuthEndpointClient authEndpointClient = new AuthEndpointClient();
        authEndpointClient.connect();
        System.out.println(authEndpointClient.profile(new UserProfileRequest()).getUser());

//        System.out.println(authEndpointClient.login(new UserLoginRequest("test2", "test2")).getSuccess());
//        System.out.println(authEndpointClient.profile(new UserProfileRequest()).getUser());

        System.out.println(authEndpointClient.login(new UserLoginRequest("test", "test")).getSuccess());
        System.out.println(authEndpointClient.profile(new UserProfileRequest()).getUser());
        System.out.println(authEndpointClient.logout(new UserLogoutRequest()));

        System.out.println(authEndpointClient.profile(new UserProfileRequest()).getUser());
        authEndpointClient.disconnect();
    }

}
