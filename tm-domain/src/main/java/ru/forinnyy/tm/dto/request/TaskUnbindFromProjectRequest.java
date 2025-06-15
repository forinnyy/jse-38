package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class TaskUnbindFromProjectRequest extends AbstractUserRequest {

    private String taskId;

    private String projectId;

    public TaskUnbindFromProjectRequest(String token) {
        super(token);
    }

}
