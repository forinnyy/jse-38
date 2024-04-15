package ru.forinnyy.tm.api;

import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.model.Project;

public interface IProjectService extends  IProjectRepository {

    Project updateById(String id, String name, String description);

    Project updateByIndex(Integer index, String name, String description);

    Project changeProjectStatusById(String id, Status status);

    Project changeProjectStatusByIndex(Integer index, Status status);

}
