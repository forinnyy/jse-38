package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.model.Task;

@Getter
@Setter
@NoArgsConstructor
public class TaskChangeStatusByIdResponse extends AbstractTaskResponse {

    public TaskChangeStatusByIdResponse(Task task) {
        super(task);
    }

}
