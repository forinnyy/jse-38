package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class TaskGetByIndexRequest extends AbstractUserRequest {

    private Integer index;

    public TaskGetByIndexRequest(String token) {
        super(token);
    }

}
