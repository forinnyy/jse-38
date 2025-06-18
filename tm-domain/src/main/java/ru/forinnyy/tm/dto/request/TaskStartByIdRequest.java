package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public final class TaskStartByIdRequest extends AbstractUserRequest {

    private String id;

    public TaskStartByIdRequest(String token) {
        super(token);
    }

}
