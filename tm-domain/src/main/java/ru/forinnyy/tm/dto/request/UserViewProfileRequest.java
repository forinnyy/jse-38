package ru.forinnyy.tm.dto.request;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class UserViewProfileRequest extends AbstractUserRequest {

    public UserViewProfileRequest(String token) {
        super(token);
    }

}
