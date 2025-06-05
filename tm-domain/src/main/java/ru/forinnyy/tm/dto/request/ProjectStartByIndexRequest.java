package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ProjectStartByIndexRequest extends AbstractUserRequest {

    private Integer index;

}
