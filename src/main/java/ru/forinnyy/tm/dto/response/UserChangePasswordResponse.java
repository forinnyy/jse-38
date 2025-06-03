package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.model.User;

@Getter
@Setter
@NoArgsConstructor
public class UserChangePasswordResponse extends AbstractUserResponse {

    public UserChangePasswordResponse(User user) {
        super(user);
    }

}
