package ru.forinnyy.tm.task;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.component.Server;

import java.net.ServerSocket;
import java.net.Socket;

public final class ServerAcceptTask extends AbstractServerTask {

    public ServerAcceptTask(@NonNull final Server server) {
        super(server);
    }

    @Override
    @SneakyThrows
    public void run() {
        final ServerSocket serverSocket = server.getSocketServer();
        if (serverSocket == null) return;
        @NonNull final Socket socket = serverSocket.accept();
        server.submit(new ServerRequestTask(server, socket));
        server.submit(new ServerAcceptTask(server));
    }

}
