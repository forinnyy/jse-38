package ru.forinnyy.tm.service;


import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.service.IConnectionService;
import ru.forinnyy.tm.api.service.IPropertyService;

import java.sql.Connection;
import java.sql.DriverManager;

public final class ConnectionService implements IConnectionService {

    @NonNull
    private final IPropertyService propertyService;

    public ConnectionService(@NonNull final IPropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @NonNull
    @Override
    @SneakyThrows
    public Connection getConnection() {
        @NonNull final String username = propertyService.getDBUser();
        @NonNull final String password = propertyService.getDBPassword();
        @NonNull final String url = propertyService.getDBUrl();
        @NonNull final Connection connection = DriverManager.getConnection(url, username, password);
        connection.setAutoCommit(false);
        return connection;
    }

}
