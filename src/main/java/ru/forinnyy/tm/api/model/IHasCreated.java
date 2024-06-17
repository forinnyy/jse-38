package ru.forinnyy.tm.api.model;

import org.jetbrains.annotations.Nullable;

import java.util.Date;

public interface IHasCreated {

    @Nullable
    Date getCreated();

    void setCreated(@Nullable Date created);

}
