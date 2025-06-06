package ru.forinnyy.tm.client;

import lombok.*;
import ru.forinnyy.tm.api.endpoint.IEndpointClient;
import ru.forinnyy.tm.dto.response.ApplicationErrorResponse;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractEndpointClient implements IEndpointClient {

    protected String host = "localhost";

    protected Integer port = 6060;

    protected Socket socket;

    public AbstractEndpointClient(@NonNull final AbstractEndpointClient client) {
        this.host = client.host;
        this.port = client.port;
        this.socket = client.socket;
    }

    @NonNull
    @SneakyThrows
    @SuppressWarnings("unchecked")
    protected <T> T call(final Object data, @NonNull final Class<T> clazz) throws IOException, ClassNotFoundException {
        getObjectOutputStream().writeObject(data);
        @NonNull final Object result = getObjectInputStream().readObject();
        if (result instanceof ApplicationErrorResponse) {
            @NonNull final ApplicationErrorResponse response = (ApplicationErrorResponse) result;
            throw new RemoteException(response.getMessage());
        }
        return (T) result;
    }

    private ObjectOutputStream getObjectOutputStream() throws IOException {
        return new ObjectOutputStream(getOutputStream());
    }

    private ObjectInputStream getObjectInputStream() throws IOException {
        return new ObjectInputStream(getInputStream());
    }

    private OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    private InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    @SneakyThrows
    public Socket connect() {
        this.socket = new Socket(host, port);
        return socket;
    }

    @SneakyThrows
    public void disconnect() {
        socket.close();
    }

}
