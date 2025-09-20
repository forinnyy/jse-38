package ru.forinnyy.tm.api.repository;

import lombok.SneakyThrows;
import ru.forinnyy.tm.model.Session;

public interface ISessionRepository extends IUserOwnedRepository<Session> {

    @SneakyThrows
    void initTable();

}
