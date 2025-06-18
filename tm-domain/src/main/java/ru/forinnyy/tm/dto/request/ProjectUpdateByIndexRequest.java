package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class ProjectUpdateByIndexRequest extends AbstractUserRequest {

    private Integer index;

    private String name;

    private String description;

    public ProjectUpdateByIndexRequest(String token) {
        super(token);
    }

}
