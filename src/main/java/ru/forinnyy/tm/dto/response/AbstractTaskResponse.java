package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.model.Task;

@Getter
@Setter
@NoArgsConstructor
public class AbstractTaskResponse {

    private Task task;

    public AbstractTaskResponse(Task task) {
        this.task = task;
    }

}
