package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;
import ru.forinnyy.tm.enumerated.Sort;

@Getter
@Setter
public final class ProjectListRequest extends AbstractUserRequest {

    private Sort sort;

}
