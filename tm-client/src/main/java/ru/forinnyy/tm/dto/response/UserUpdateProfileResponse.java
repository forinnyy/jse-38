package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.model.User;

@Getter
@Setter
@NoArgsConstructor
public final class UserUpdateProfileResponse extends AbstractUserResponse {

    public UserUpdateProfileResponse(User user) {
        super(user);
    }

}
