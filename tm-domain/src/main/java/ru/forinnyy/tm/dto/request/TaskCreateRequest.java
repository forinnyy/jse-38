package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class TaskCreateRequest extends AbstractUserRequest {

    private String name;

    private String description;

    public TaskCreateRequest(String token) {
        super(token);
    }

}
