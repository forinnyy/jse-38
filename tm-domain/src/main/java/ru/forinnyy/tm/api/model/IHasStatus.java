package ru.forinnyy.tm.api.model;

import lombok.NonNull;
import ru.forinnyy.tm.enumerated.Status;

public interface IHasStatus {

    @NonNull
    Status getStatus();

    void setStatus(@NonNull Status status);

}
