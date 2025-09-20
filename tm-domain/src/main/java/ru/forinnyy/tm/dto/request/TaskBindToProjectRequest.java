package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class TaskBindToProjectRequest extends AbstractUserRequest {

    private String taskId;

    private String projectId;

    public TaskBindToProjectRequest(String token) {
        super(token);
    }

}
