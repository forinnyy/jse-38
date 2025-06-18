package ru.forinnyy.tm.dto.request;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class TaskClearRequest extends AbstractUserRequest {

    public TaskClearRequest(String token) {
        super(token);
    }

}
