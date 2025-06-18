package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class UserRemoveRequest extends AbstractUserRequest {

    private String login;

    public UserRemoveRequest(String token) {
        super(token);
    }

}
