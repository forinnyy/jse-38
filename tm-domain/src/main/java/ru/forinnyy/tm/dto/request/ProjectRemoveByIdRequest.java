package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class ProjectRemoveByIdRequest extends AbstractUserRequest {

    private String id;

    public ProjectRemoveByIdRequest(String token) {
        super(token);
    }

}
