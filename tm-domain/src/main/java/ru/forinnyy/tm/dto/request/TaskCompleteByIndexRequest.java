package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class TaskCompleteByIndexRequest extends AbstractUserRequest {

    private Integer index;

    public TaskCompleteByIndexRequest(String token) {
        super(token);
    }

}
