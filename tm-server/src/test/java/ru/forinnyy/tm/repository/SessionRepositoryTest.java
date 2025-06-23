package ru.forinnyy.tm.repository;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.junit.Before;
import ru.forinnyy.tm.api.repository.ISessionRepository;
import ru.forinnyy.tm.model.Session;

import java.util.ArrayList;
import java.util.List;

public final class SessionRepositoryTest {

    private final static int NUMBER_OF_ENTRIES = 10;

    @NonNull
    private List<Session> sessionList;

    @NonNull
    private ISessionRepository sessionRepository;

    @Before
    @SneakyThrows
    public void initRepository() {
        sessionList = new ArrayList<>();
        sessionRepository = new SessionRepository();
        for (int i = 1; i <= NUMBER_OF_ENTRIES; i++) {
            @NonNull final Session session = new Session();
            sessionRepository.add(session);
            sessionList.add(session);
        }
    }



}
