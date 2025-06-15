package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class TaskUpdateByIdRequest extends AbstractUserRequest {

    private String id;

    private String name;

    private String description;

    public TaskUpdateByIdRequest(String token) {
        super(token);
    }

}
