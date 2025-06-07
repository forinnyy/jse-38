package ru.forinnyy.tm.api.service;

import lombok.NonNull;

public interface IPropertyService {

    @NonNull
    String getApplicationConfig();


    @NonNull
    Integer getServerPort();

}
