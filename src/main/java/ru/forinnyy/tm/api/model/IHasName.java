package ru.forinnyy.tm.api.model;

import org.jetbrains.annotations.NotNull;

public interface IHasName {

    @NotNull
    String getName();

    void setName(@NotNull String name);

}
