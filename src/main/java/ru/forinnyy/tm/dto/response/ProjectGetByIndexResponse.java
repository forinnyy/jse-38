package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.model.Project;

@Getter
@Setter
@NoArgsConstructor
public class ProjectGetByIndexResponse extends AbstractProjectResponse {

    public ProjectGetByIndexResponse(Project project) {
        super(project);
    }

}
