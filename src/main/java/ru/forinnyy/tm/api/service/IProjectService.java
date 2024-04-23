package ru.forinnyy.tm.api.service;

import ru.forinnyy.tm.api.repository.IProjectRepository;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.model.Project;

import java.util.List;

public interface IProjectService extends IProjectRepository {

    Project updateById(String id, String name, String description);

    Project updateByIndex(Integer index, String name, String description);

    Project changeProjectStatusById(String id, Status status);

    Project changeProjectStatusByIndex(Integer index, Status status);

    List<Project> findAll(Sort sort);

}
