package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectGetByIdRequest extends AbstractUserRequest {

    private String id;

}
