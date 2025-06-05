package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public final class TaskStartByIdRequest extends AbstractUserRequest {

    private String id;

}
