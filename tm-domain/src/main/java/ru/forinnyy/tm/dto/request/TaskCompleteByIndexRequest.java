package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class TaskCompleteByIndexRequest extends AbstractUserRequest {

    private Integer index;

    public TaskCompleteByIndexRequest(String token) {
        super(token);
    }

}
