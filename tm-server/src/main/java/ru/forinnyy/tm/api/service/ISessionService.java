package ru.forinnyy.tm.api.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.model.Session;

import java.util.Date;

public interface ISessionService extends IUserOwnedService<Session> {
    @SneakyThrows
    void removeExpiredSessions(@NonNull Date currentDate);

    @SneakyThrows
    void removeAllByUserId(@NonNull String userId);
}
