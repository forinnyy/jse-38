package ru.forinnyy.tm.model;

import ru.forinnyy.tm.api.model.IWBS;
import ru.forinnyy.tm.enumerated.Status;

import java.util.Date;

public final class Project extends AbstractUserOwnedModel implements IWBS {

    private String name = "";

    private String description = "";

    private Status status = Status.NOT_STARTED;

    private Date created = new Date();

    public Project() {
    }

    public Project(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
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
