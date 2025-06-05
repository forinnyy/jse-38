package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.model.User;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractUserResponse extends AbstractResponse {

    private User user;

    public AbstractUserResponse(User user) {
        this.user = user;
    }

}
