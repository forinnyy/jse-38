package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.model.Task;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractTaskResponse extends AbstractResponse {

    private Task task;

    public AbstractTaskResponse(Task task) {
        this.task = task;
    }

}
