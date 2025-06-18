package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class ProjectUpdateByIdRequest extends AbstractUserRequest {

    private String id;

    private String name;

    private String description;

    public ProjectUpdateByIdRequest(String token) {
        super(token);
    }

}
