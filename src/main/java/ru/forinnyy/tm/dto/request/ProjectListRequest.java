package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.Setter;
import ru.forinnyy.tm.enumerated.Sort;

@Getter
@Setter
public class ProjectListRequest extends AbstractUserRequest {

    private Sort sort;

}
