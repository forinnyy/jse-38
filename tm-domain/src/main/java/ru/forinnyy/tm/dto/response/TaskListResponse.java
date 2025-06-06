package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.model.Task;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public final class TaskListResponse extends AbstractResponse {

    private List<Task> tasks;

    public TaskListResponse(List<Task> tasks) {
        this.tasks = tasks;
    }

}
