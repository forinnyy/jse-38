package ru.forinnyy.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.enumerated.Sort;

@Getter
@Setter
@NoArgsConstructor
public final class TaskListRequest extends AbstractUserRequest {

    private Sort sort;

}
