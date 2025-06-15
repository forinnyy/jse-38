package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ProjectRemoveByIdRequest extends AbstractUserRequest {

    private String id;

    public ProjectRemoveByIdRequest(String token) {
        super(token);
    }

}
