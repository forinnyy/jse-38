package ru.forinnyy.tm.client;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.net.Socket;

@Getter
@Setter
public abstract class AbstractClient {

    private String host = "localhost";

    private Integer port = 6060;

    private Socket socket;

    public AbstractClient() {
    }

    public AbstractClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    protected Object call(final Object data) throws IOException, ClassNotFoundException {
        getObjectOutputStream().writeObject(data);
        return getObjectInputStream().readObject();
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

    public void connect() throws IOException {
        socket = new Socket(host, port);
    }

    public void disconnect() throws IOException {
        socket.close();
    }

}
