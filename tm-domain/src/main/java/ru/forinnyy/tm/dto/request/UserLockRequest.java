package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class UserLockRequest extends AbstractUserRequest {

    private String login;

    public UserLockRequest(String token) {
        super(token);
    }

}
