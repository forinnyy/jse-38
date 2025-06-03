package ru.forinnyy.tm.dto.response;

import ru.forinnyy.tm.model.Task;

public class TaskStartByIdResponse extends AbstractTaskResponse {

    public TaskStartByIdResponse(Task task) {
        super(task);
    }

}
