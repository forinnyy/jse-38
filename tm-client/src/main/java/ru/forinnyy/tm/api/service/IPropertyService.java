package ru.forinnyy.tm.api.service;

import lombok.NonNull;
import ru.forinnyy.tm.api.endpoint.IConnectionProvider;

public interface IPropertyService extends IConnectionProvider {

    @NonNull
    String getApplicationVersion();


}
