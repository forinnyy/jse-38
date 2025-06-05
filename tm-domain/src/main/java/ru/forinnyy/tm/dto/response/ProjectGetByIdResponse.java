package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.model.Project;

@Getter
@Setter
@NoArgsConstructor
public final class ProjectGetByIdResponse extends AbstractProjectResponse {

    public ProjectGetByIdResponse(Project project) {
        super(project);
    }

}
