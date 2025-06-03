package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskGetByIdRequest extends AbstractUserRequest {

    private String id;

}
