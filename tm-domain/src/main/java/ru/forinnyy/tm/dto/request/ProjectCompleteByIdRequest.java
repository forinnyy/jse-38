package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ProjectCompleteByIdRequest extends AbstractUserRequest {

    private String id;

    public ProjectCompleteByIdRequest(String token) {
        super(token);
    }

}
