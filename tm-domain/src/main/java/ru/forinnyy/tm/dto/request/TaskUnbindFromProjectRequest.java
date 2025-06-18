package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class TaskUnbindFromProjectRequest extends AbstractUserRequest {

    private String taskId;

    private String projectId;

    public TaskUnbindFromProjectRequest(String token) {
        super(token);
    }

}
