package ru.forinnyy.tm.api.repository;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.model.Session;

public interface ISessionRepository extends IUserOwnedRepository<Session> {
    @NonNull
    @SneakyThrows
    void initTable();

}
