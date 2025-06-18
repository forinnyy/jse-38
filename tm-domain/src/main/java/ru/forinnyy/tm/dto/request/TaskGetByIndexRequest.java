package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class TaskGetByIndexRequest extends AbstractUserRequest {

    private Integer index;

    public TaskGetByIndexRequest(String token) {
        super(token);
    }

}
