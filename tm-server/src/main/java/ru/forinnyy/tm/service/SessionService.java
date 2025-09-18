package ru.forinnyy.tm.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.repository.ISessionRepository;
import ru.forinnyy.tm.api.service.IConnectionService;
import ru.forinnyy.tm.api.service.ISessionService;
import ru.forinnyy.tm.exception.field.UserIdEmptyException;
import ru.forinnyy.tm.model.Session;

import java.sql.Connection;
import java.util.Date;

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
    public void removeExpiredSessions(@NonNull final Date currentDate) {
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final ISessionRepository repository = getRepository(connection);
            repository.removeExpiredSessions(currentDate);
            connection.commit();
        }
    }

    @Override
    @SneakyThrows
    public void removeAllByUserId(@NonNull final String userId) {
        if (userId == null || userId.isEmpty()) throw new UserIdEmptyException();
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final ISessionRepository repository = getRepository(connection);
            repository.removeAllByUserId(userId);
            connection.commit();
        }
    }

    @NonNull
    @Override
    @SneakyThrows
    public Session findOneById(@NonNull final String id) {
        try (@NonNull final Connection connection = getConnection()) {
            @NonNull final ISessionRepository repository = getRepository(connection);
            return repository.findOneById(id);
        }
    }

}