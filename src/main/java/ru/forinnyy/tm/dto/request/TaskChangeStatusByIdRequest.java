package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;
import ru.forinnyy.tm.enumerated.Status;

@Getter
@Setter
public class TaskChangeStatusByIdRequest extends AbstractUserRequest {

    private String id;

    private Status status;

}
