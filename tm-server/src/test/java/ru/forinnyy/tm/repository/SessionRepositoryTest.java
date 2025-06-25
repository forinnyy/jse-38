package ru.forinnyy.tm.repository;

import ru.forinnyy.tm.model.Session;

public final class SessionRepositoryTest extends AbstractRepositoryTest<Session> {

    @Override
    protected AbstractRepository<Session> createRepository() {
        return new SessionRepository();
    }

    @Override
    protected Session createModel() {
        return new Session();
    }

}
