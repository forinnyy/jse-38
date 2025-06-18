package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.enumerated.Status;

@Getter
@Setter
@NoArgsConstructor
public final class TaskChangeStatusByIndexRequest extends AbstractUserRequest {

    private Status status;

    private Integer index;

    public TaskChangeStatusByIndexRequest(String token) {
        super(token);
    }

}
