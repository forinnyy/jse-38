package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractUserRequest extends AbstractRequest {

    private String token;

}
