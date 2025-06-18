package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class TaskCreateRequest extends AbstractUserRequest {

    private String name;

    private String description;

    public TaskCreateRequest(String token) {
        super(token);
    }

}
