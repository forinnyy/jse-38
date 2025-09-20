package ru.forinnyy.tm.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.repository.ISessionRepository;
import ru.forinnyy.tm.api.service.IConnectionService;
import ru.forinnyy.tm.api.service.ISessionService;
import ru.forinnyy.tm.model.Session;

import java.sql.Connection;


public final class SessionService extends AbstractUserOwnedService<Session, ISessionRepository> implements ISessionService {

    public SessionService(@NonNull final IConnectionService connectionService) {
        super(connectionService);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ISessionRepository getRepository(@NonNull final Connection connection) {
        return new ru.forinnyy.tm.repository.SessionRepository(connection);
    }

    @Override
    @SneakyThrows
    public void initTable() {
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final ISessionRepository repository = getRepository(connection);
            repository.initTable();
            connection.commit();
        }
    }

}