package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class UserUnlockRequest extends AbstractUserRequest {

    private String login;

    public UserUnlockRequest(String token) {
        super(token);
    }

}
