package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectCreateRequest extends AbstractUserRequest {

    private String name;

    private String description;

}
