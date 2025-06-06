package ru.forinnyy.tm.client;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.endpoint.IAuthEndpointClient;
import ru.forinnyy.tm.dto.request.UserLoginRequest;
import ru.forinnyy.tm.dto.request.UserLogoutRequest;
import ru.forinnyy.tm.dto.request.UserProfileRequest;
import ru.forinnyy.tm.dto.response.UserLoginResponse;
import ru.forinnyy.tm.dto.response.UserLogoutResponse;
import ru.forinnyy.tm.dto.response.UserProfileResponse;

@NoArgsConstructor
public final class AuthEndpointClient extends AbstractEndpointClient implements IAuthEndpointClient {

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

}
