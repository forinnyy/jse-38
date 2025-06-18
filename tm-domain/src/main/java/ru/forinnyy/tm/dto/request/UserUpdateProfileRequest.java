package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class UserUpdateProfileRequest extends AbstractUserRequest {

    private String firstName;

    private String lastName;

    private String middleName;

    public UserUpdateProfileRequest(String token) {
        super(token);
    }

}
