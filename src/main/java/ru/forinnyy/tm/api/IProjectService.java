package ru.forinnyy.tm.api;

import ru.forinnyy.tm.model.Project;

import java.util.List;

public interface IProjectService extends  IProjectRepository {

    Project create(String name, String description);

}
