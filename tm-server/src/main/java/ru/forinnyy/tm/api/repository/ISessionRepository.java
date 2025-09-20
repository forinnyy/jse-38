package ru.forinnyy.tm.api.repository;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.model.Session;

import java.util.Date;

public interface ISessionRepository extends IUserOwnedRepository<Session> {
    @NonNull
    @SneakyThrows
    void initTable();

    @SneakyThrows
    void removeExpiredSessions(@NonNull Date currentDate);

    @SneakyThrows
    void removeAllByUserId(@NonNull String userId);
}
