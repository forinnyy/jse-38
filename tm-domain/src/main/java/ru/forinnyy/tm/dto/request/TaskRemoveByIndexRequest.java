package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class TaskRemoveByIndexRequest extends AbstractUserRequest {

    private Integer index;

    public TaskRemoveByIndexRequest(String token) {
        super(token);
    }

}
