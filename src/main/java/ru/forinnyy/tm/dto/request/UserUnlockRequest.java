package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUnlockRequest extends AbstractUserRequest {

    private String login;
}
