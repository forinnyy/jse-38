package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class TaskUpdateByIndexRequest extends AbstractUserRequest {

    private Integer index;

    private String name;

    private String description;

    public TaskUpdateByIndexRequest(String token) {
        super(token);
    }

}
