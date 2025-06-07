package ru.forinnyy.tm.api.endpoint;


import java.net.Socket;

public interface IEndpointClient {

    Socket connect();

    void disconnect();

    void setSocket(Socket socket);

    void setPort(Integer port);

}
