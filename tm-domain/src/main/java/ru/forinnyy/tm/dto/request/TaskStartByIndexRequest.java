package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class TaskStartByIndexRequest extends  AbstractUserRequest {

    private Integer index;

    public TaskStartByIndexRequest(String token) {
        super(token);
    }

}
