package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class UserUpdateProfileRequest extends AbstractUserRequest {

    private String firstName;

    private String lastName;

    private String middleName;

}
