package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;
import ru.forinnyy.tm.enumerated.Status;

@Getter
@Setter
public class ProjectChangeStatusByIndexRequest extends AbstractUserRequest {

    private String index;

    private Status status;

}
