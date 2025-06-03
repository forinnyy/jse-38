package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class UserRegistryRequest extends AbstractRequest {

    private String login;

    private String password;

    private String email;

}
