package ru.forinnyy.tm.client;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.api.endpoint.IUserEndpointClient;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;

public final class UserEndpointClient extends AbstractEndpointClient implements IUserEndpointClient {

    @NotNull
    @Override
    @SneakyThrows
    public UserLockResponse lockUser(@NotNull UserLockRequest request) {
        return call(request, UserLockResponse.class);
    }

    @NotNull
    @Override
    @SneakyThrows
    public UserUnlockResponse unlockUser(@NotNull UserUnlockRequest request) {
        return call(request, UserUnlockResponse.class);
    }

    @NotNull
    @Override
    @SneakyThrows
    public UserRemoveResponse removeUser(@NotNull UserRemoveRequest request) {
        return call(request, UserRemoveResponse.class);
    }

    @NotNull
    @Override
    @SneakyThrows
    public UserRegistryResponse registryUser(@NotNull UserRegistryRequest request) {
        return call(request, UserRegistryResponse.class);
    }

    @NotNull
    @Override
    @SneakyThrows
    public UserChangePasswordResponse changeUserPassword(@NotNull UserChangePasswordRequest request) {
        return call(request, UserChangePasswordResponse.class);
    }

    @NotNull
    @Override
    @SneakyThrows
    public UserUpdateProfileResponse updateUserProfile(@NotNull UserUpdateProfileRequest request) {
        return call(request, UserUpdateProfileResponse.class);
    }

}
