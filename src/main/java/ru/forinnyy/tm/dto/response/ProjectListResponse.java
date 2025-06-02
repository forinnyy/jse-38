package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.forinnyy.tm.model.Project;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProjectListResponse extends AbstractResponse {

    private List<Project> projects;

    public ProjectListResponse(List<Project> projects) {
        this.projects = projects;
    }

}
