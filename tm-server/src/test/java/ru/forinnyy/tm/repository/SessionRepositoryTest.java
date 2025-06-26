package ru.forinnyy.tm.repository;

import ru.forinnyy.tm.model.Session;

public final class SessionRepositoryTest extends AbstractUserOwnedRepositoryTest<Session> {

    @Override
    protected AbstractUserOwnedRepository<Session> createRepository() {
        return new SessionRepository();
    }

    @Override
    protected Session createModel() {
        return new Session();
    }

}
