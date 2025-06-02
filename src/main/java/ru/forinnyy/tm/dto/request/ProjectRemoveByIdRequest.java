package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRemoveByIdRequest extends AbstractUserRequest {

    private String id;

}
