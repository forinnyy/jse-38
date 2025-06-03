package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.model.Task;

@Getter
@Setter
@NoArgsConstructor
public class TaskGetByIdResponse extends AbstractTaskResponse {

    public TaskGetByIdResponse(Task task) {
        super(task);
    }

}
