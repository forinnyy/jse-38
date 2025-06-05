package ru.forinnyy.tm.task;

import lombok.NonNull;
import ru.forinnyy.tm.component.Server;

public abstract class AbstractServerTask implements Runnable {

    @NonNull
    protected Server server;

    public AbstractServerTask(@NonNull final Server server) {
        this.server = server;
    }

}
