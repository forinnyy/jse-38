package ru.forinnyy.tm.api.model;

import ru.forinnyy.tm.enumerated.Status;

public interface IHasStatus {

    Status getStatus();

    void setStatus(Status status);

}
