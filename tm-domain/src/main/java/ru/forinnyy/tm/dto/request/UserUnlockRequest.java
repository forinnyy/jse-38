package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class UserUnlockRequest extends AbstractUserRequest {

    private String login;

    public UserUnlockRequest(String token) {
        super(token);
    }

}
