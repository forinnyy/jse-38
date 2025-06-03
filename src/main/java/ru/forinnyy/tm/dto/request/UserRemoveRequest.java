package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRemoveRequest extends AbstractUserRequest {

    private String login;

}
