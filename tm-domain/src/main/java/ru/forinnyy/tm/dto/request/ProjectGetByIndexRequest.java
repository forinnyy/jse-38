package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class ProjectGetByIndexRequest extends AbstractUserRequest {

    private Integer index;

    public ProjectGetByIndexRequest(String token) {
        super(token);
    }

}
