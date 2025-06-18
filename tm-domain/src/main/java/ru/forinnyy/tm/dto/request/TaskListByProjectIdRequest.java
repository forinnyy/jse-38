package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class TaskListByProjectIdRequest extends AbstractUserRequest {

    private String projectId;

    public TaskListByProjectIdRequest(String token) {
        super(token);
    }

}
