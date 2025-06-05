package ru.forinnyy.tm.api.component;

import lombok.NonNull;

public interface ISaltProvider {

    @NonNull
    Integer getPasswordIteration();

    @NonNull
    String getPasswordSecret();

}
