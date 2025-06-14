package ru.forinnyy.tm.api.endpoint;

import lombok.NonNull;

public interface IConnectionProvider {

    @NonNull
    String getHost();

    @NonNull
    String getPort();

}
