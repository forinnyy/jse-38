package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.Setter;
import ru.forinnyy.tm.model.Project;

@Getter
@Setter
public class ProjectChangeStatusByIdResponse extends AbstractProjectResponse {

    public ProjectChangeStatusByIdResponse(Project project) {
        super(project);
    }

}
