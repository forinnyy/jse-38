package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserListProfilesRequest extends AbstractUserRequest {

    public UserListProfilesRequest(String token) {
        super(token);
    }

}
