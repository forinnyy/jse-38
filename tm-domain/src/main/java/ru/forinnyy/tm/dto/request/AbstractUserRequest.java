package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractUserRequest extends AbstractRequest {

    private String token;

    public AbstractUserRequest(String token) {
        this.token = token;
    }

}
