package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;
import ru.forinnyy.tm.enumerated.Status;

@Getter
@Setter
public class ProjectCompleteByIdRequest extends AbstractUserRequest {

    private String id;

}
