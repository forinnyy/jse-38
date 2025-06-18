package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class ProjectCreateRequest extends AbstractUserRequest {

    private String name;

    private String description;

    public ProjectCreateRequest(String token) {
        super(token);
    }

}
