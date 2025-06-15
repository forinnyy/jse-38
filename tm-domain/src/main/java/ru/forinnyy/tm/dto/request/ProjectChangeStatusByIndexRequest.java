package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;
import ru.forinnyy.tm.enumerated.Status;

@Getter
@Setter
public final class ProjectChangeStatusByIndexRequest extends AbstractUserRequest {

    private Integer index;

    private Status status;

    public ProjectChangeStatusByIndexRequest(String token) {
        super(token);
    }

}
