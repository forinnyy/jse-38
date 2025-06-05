package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class TaskRemoveByIdRequest extends AbstractUserRequest {

    private String id;

}
