package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class ProjectStartByIndexRequest extends AbstractUserRequest {

    private Integer index;

    public ProjectStartByIndexRequest(String token) {
        super(token);
    }

}
