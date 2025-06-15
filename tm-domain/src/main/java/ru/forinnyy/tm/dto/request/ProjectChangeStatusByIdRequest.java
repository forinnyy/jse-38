package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.enumerated.Status;

@Getter
@Setter
public final class ProjectChangeStatusByIdRequest extends AbstractUserRequest {

    private String id;

    private Status status;

    public ProjectChangeStatusByIdRequest(String token) {
        super(token);
    }

}
