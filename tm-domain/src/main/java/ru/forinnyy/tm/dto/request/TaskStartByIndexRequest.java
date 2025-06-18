package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class TaskStartByIndexRequest extends  AbstractUserRequest {

    private Integer index;

    public TaskStartByIndexRequest(String token) {
        super(token);
    }

}
