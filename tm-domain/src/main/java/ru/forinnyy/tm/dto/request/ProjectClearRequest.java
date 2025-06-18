package ru.forinnyy.tm.dto.request;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class ProjectClearRequest extends AbstractUserRequest {

    public ProjectClearRequest(String token) {
        super(token);
    }

}
