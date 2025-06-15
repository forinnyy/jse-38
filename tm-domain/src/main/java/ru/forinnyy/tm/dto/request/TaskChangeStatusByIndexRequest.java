package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;
import ru.forinnyy.tm.enumerated.Status;

@Getter
@Setter
public final class TaskChangeStatusByIndexRequest extends AbstractUserRequest {

    private Status status;

    private Integer index;

    public TaskChangeStatusByIndexRequest(String token) {
        super(token);
    }

}
