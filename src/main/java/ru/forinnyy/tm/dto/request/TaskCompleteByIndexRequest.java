package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskCompleteByIndexRequest extends AbstractUserRequest {

    private Integer index;

}
