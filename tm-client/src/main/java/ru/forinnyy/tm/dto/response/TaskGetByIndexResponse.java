package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.model.Task;

@Getter
@Setter
@NoArgsConstructor
public final class TaskGetByIndexResponse extends AbstractTaskResponse {

    public TaskGetByIndexResponse(Task task) {
        super(task);
    }

}
