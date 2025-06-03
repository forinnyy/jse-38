package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateProfileRequest extends AbstractUserRequest {

    private String firstName;

    private String lastName;

    private String middleName;

}
