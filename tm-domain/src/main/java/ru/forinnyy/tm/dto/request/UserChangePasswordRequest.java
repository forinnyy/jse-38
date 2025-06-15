package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class UserChangePasswordRequest extends AbstractUserRequest {

    private String password;

    public UserChangePasswordRequest(String token) {
        super(token);
    }

}
