package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.Setter;
import ru.forinnyy.tm.model.Task;

import java.util.List;

@Getter
@Setter
public final class TaskListResponse extends AbstractResponse {

    private final List<Task> tasks;

    public TaskListResponse(List<Task> tasks) {
        this.tasks = tasks;
    }

}
