package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.model.Project;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractProjectResponse extends AbstractResponse {

    private Project project;

    public AbstractProjectResponse(Project project) {
        this.project = project;
    }

}
