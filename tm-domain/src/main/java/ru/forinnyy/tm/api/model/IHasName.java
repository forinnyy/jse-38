package ru.forinnyy.tm.api.model;


import lombok.NonNull;

public interface IHasName {

    @NonNull
    String getName();

    void setName(@NonNull String name);

}
