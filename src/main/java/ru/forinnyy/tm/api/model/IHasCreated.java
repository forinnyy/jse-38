package ru.forinnyy.tm.api.model;

import org.jetbrains.annotations.Nullable;

import java.util.Date;

public interface IHasCreated {

    Date getCreated();

    void setCreated(Date created);

}
