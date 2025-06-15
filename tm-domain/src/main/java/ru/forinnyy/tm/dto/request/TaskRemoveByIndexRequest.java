package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class TaskRemoveByIndexRequest extends AbstractUserRequest {

    private Integer index;

    public TaskRemoveByIndexRequest(String token) {
        super(token);
    }

}
