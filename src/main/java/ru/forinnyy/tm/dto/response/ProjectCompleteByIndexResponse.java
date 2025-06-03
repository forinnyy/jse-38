package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.model.Project;

@Getter
@Setter
@NoArgsConstructor
public class ProjectCompleteByIndexResponse extends AbstractProjectResponse {

    public ProjectCompleteByIndexResponse(Project project) {
        super(project);
    }

}
