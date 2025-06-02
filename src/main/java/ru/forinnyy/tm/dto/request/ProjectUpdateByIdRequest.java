package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectUpdateByIdRequest extends AbstractUserRequest {

    private String id;

    private String name;

    private String description;

}
