package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.model.Project;

@Getter
@Setter
@NoArgsConstructor
public class ProjectRemoveByIdResponse extends AbstractProjectResponse {

    public ProjectRemoveByIdResponse(Project project) {
        super(project);
    }

}
