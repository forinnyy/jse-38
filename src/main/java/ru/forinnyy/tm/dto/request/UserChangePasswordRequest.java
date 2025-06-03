package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChangePasswordRequest extends AbstractUserRequest {

    private String password;

}
