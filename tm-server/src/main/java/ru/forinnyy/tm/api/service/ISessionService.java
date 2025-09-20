package ru.forinnyy.tm.api.service;

import lombok.SneakyThrows;
import ru.forinnyy.tm.model.Session;

public interface ISessionService extends IUserOwnedService<Session> {

    @SneakyThrows
    void initTable();

}
