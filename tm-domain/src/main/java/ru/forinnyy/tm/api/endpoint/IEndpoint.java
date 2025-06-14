package ru.forinnyy.tm.api.endpoint;

import lombok.NonNull;
import lombok.SneakyThrows;

import javax.jws.WebMethod;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public interface IEndpoint {

    @NonNull
    String HOST = "localhost";

    @NonNull
    String PORT = "8080";

    @NonNull
    String REQUEST = "request";

    @NonNull
    String SPACE = "http://endpoint.tm.forinnyy.ru/";

    @SneakyThrows
    @WebMethod(exclude = true)
    static <T> T newInstance(
            @NonNull final String name, @NonNull final String space,
            @NonNull final String part, @NonNull final Class<T> clazz
    ) {
        @NonNull final String host = HOST;
        @NonNull final String port = PORT;
        return newInstanse(host, port, name, space, part, clazz);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static <T> T newInstance(
            @NonNull final IConnectionProvider connectionProvider,
            @NonNull final String name, @NonNull final String space,
            @NonNull final String part, @NonNull final Class<T> clazz
    ) {
        @NonNull final String host = connectionProvider.getHost();
        @NonNull final String port = connectionProvider.getPort();
        return newInstanse(host, port, name, space, part, clazz);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static <T> T newInstanse(
            @NonNull final String host, @NonNull final String port,
            @NonNull final String name, @NonNull final String space,
            @NonNull final String part, @NonNull final Class<T> clazz
    ) {
        @NonNull final String wsdl = "http://" + host + ":" + port + "/" + name + "?wsdl";
        @NonNull final URL url = new URL(wsdl);
        @NonNull final QName qName = new QName(space, part);
        return Service.create(url, qName).getPort(clazz);
    }

}
