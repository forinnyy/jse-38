package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class ProjectRemoveByIndexRequest extends AbstractUserRequest {

    private Integer index;

    public ProjectRemoveByIndexRequest(String token) {
        super(token);
    }

}
