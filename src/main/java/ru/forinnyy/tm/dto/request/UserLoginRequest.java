package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginRequest extends AbstractRequest {

    private String login;

    private String password;

    public UserLoginRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

}
