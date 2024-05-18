package ru.forinnyy.tm.model;

import ru.forinnyy.tm.api.model.IWBS;
import ru.forinnyy.tm.enumerated.Status;

import java.util.Date;
import java.util.UUID;

public final class Task implements IWBS {

    private String id = UUID.randomUUID().toString();

    private String name = "";

    private String description = "";

    private Status status = Status.NOT_STARTED;

    private String projectId;

    private Date created = new Date();

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Task() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
