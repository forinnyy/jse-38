package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ProjectCreateRequest extends AbstractUserRequest {

    private String name;

    private String description;

    public ProjectCreateRequest(String token) {
        super(token);
    }

}
