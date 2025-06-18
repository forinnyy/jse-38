package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class TaskRemoveByIdRequest extends AbstractUserRequest {

    private String id;

    public TaskRemoveByIdRequest(String token) {
        super(token);
    }

}
