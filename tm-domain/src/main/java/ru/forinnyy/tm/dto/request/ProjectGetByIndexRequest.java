package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ProjectGetByIndexRequest extends AbstractUserRequest {

    private Integer index;

}
