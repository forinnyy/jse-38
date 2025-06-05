package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.model.Task;

@Getter
@Setter
@NoArgsConstructor
public final class TaskChangeStatusByIndexResponse extends AbstractTaskResponse {

    public TaskChangeStatusByIndexResponse(Task task) {
        super(task);
    }

}
