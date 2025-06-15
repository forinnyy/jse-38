package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class UserRemoveRequest extends AbstractUserRequest {

    private String login;

    public UserRemoveRequest(String token) {
        super(token);
    }

}
