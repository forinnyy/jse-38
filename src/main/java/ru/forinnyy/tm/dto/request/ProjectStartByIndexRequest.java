package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectStartByIndexRequest extends AbstractUserRequest {

    private Integer index;

}
