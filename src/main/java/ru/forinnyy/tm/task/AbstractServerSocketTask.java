package ru.forinnyy.tm.task;

import lombok.NonNull;
import ru.forinnyy.tm.component.Server;

import java.net.Socket;

public abstract class AbstractServerSocketTask extends AbstractServerTask {

    @NonNull
    protected final Socket socket;

    public AbstractServerSocketTask(
            @NonNull final Server server,
            @NonNull final Socket socket) {
        super(server);
        this.socket = socket;
    }

}
