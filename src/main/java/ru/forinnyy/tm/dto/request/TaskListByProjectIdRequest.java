package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskListByProjectIdRequest extends AbstractUserRequest {

    private String projectId;

}
