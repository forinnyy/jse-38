package ru.forinnyy.tm.api.service;

import lombok.NonNull;
import ru.forinnyy.tm.api.component.ISaltProvider;

public interface IPropertyService extends ISaltProvider {

    @NonNull
    String getApplicationVersion();

    @NonNull
    String getAuthorEmail();

    @NonNull
    String getAuthorName();

}
