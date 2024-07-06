package ru.forinnyy.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.api.component.ISaltProvider;

public interface IPropertyService extends ISaltProvider {

    @NotNull
    String getApplicationVersion();

    @NotNull
    String getAuthorEmail();

    @NotNull
    String getAuthorName();

}
