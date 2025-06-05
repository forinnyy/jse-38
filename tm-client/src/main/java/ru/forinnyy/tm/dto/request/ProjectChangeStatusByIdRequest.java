package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.enumerated.Status;

@Getter
@Setter
@NoArgsConstructor
public final class ProjectChangeStatusByIdRequest extends AbstractUserRequest {

    private String id;

    private Status status;

}
