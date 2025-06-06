package ru.forinnyy.tm.api.endpoint;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.UserLoginRequest;
import ru.forinnyy.tm.dto.request.UserLogoutRequest;
import ru.forinnyy.tm.dto.request.UserProfileRequest;
import ru.forinnyy.tm.dto.response.UserLoginResponse;
import ru.forinnyy.tm.dto.response.UserLogoutResponse;
import ru.forinnyy.tm.dto.response.UserProfileResponse;

public interface IAuthEndpointClient extends IEndpointClient {

    @NonNull
    UserLoginResponse login(@NonNull UserLoginRequest request);

    @NonNull
    UserLogoutResponse logout(@NonNull UserLogoutRequest request);

    @NonNull
    UserProfileResponse profile(@NonNull UserProfileRequest request);

}
