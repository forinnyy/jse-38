package ru.forinnyy.tm.service;


import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.dbcp2.BasicDataSource;
import ru.forinnyy.tm.api.service.IConnectionService;
import ru.forinnyy.tm.api.service.IPropertyService;

import java.sql.Connection;

public final class ConnectionService implements IConnectionService {

    @NonNull
    private final IPropertyService propertyService;

    private final BasicDataSource dataSource;

    public ConnectionService(@NonNull final IPropertyService propertyService) {
        this.propertyService = propertyService;
        this.dataSource = createDataSource();
    }

    private BasicDataSource createDataSource() {
        final BasicDataSource result = new BasicDataSource();
        result.setUrl(propertyService.getDBUrl());
        result.setUsername(propertyService.getDBUser());
        result.setPassword(propertyService.getDBPassword());
        result.setMinIdle(5);
        result.setMaxIdle(10);
        result.setAutoCommitOnReturn(false);
        result.setMaxOpenPreparedStatements(100);
        return result;
    }

    @NonNull
    @Override
    @SneakyThrows
    public Connection getConnection() {
        @NonNull final Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }
}

