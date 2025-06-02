package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.model.Project;

@Getter
@Setter
@NoArgsConstructor
public class ProjectUpdateByIndexResponse extends AbstractProjectResponse {

    public ProjectUpdateByIndexResponse(Project project) {
        super(project);
    }

}
