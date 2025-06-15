package ru.forinnyy.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.api.repository.ISessionRepository;
import ru.forinnyy.tm.api.service.ISessionService;
import ru.forinnyy.tm.model.Session;

public final class SessionService extends AbstractUserOwnedService<Session, ISessionRepository> implements ISessionService {

    public SessionService(@NotNull ISessionRepository repository) {
        super(repository);
    }

}
