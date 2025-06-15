package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;
import ru.forinnyy.tm.enumerated.Sort;

@Getter
@Setter
public final class TaskListRequest extends AbstractUserRequest {

    private Sort sort;

    public TaskListRequest(String token) {
        super(token);
    }

}
