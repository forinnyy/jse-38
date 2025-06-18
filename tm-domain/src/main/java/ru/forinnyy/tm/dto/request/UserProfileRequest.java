package ru.forinnyy.tm.dto.request;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class UserProfileRequest extends AbstractUserRequest {

    public UserProfileRequest(String token) {
        super(token);
    }

}
