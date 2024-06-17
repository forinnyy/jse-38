package ru.forinnyy.tm.api.model;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.enumerated.Status;

public interface IHasStatus {

    @NotNull
    Status getStatus();

    void setStatus(@NotNull Status status);

}
