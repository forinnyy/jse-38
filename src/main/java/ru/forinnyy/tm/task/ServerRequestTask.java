package ru.forinnyy.tm.task;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.component.Server;
import ru.forinnyy.tm.dto.request.AbstractRequest;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public final class ServerRequestTask extends AbstractServerSocketTask {

    public ServerRequestTask(
            @NonNull final Server server,
            @NonNull final Socket socket
    ) {
        super(server, socket);
    }

    @Override
    @SneakyThrows
    public void run() {
        @NonNull final InputStream inputStream = socket.getInputStream();
        @NonNull final ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        @NonNull final Object object = objectInputStream.readObject();
        @NonNull final AbstractRequest request = (AbstractRequest) object;
        Object response = server.call(request);
        @NonNull final OutputStream outputStream = socket.getOutputStream();
        @NonNull final ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(response);
        server.submit(new ServerRequestTask(server, socket));
    }

}
