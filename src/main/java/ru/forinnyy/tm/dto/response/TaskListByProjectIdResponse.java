package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.Setter;
import ru.forinnyy.tm.model.Task;

import java.util.List;

@Getter
@Setter
public class TaskListByProjectIdResponse {

    private final List<Task> tasks;

    public TaskListByProjectIdResponse(List<Task> tasks) {
        this.tasks = tasks;
    }

}
