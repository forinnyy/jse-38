package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.model.Project;

@Getter
@Setter
@NoArgsConstructor
public final class ProjectStartByIndexResponse extends AbstractProjectResponse {

    public ProjectStartByIndexResponse(Project project) {
        super(project);
    }

}
