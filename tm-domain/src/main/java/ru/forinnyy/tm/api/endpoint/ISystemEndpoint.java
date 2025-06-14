package ru.forinnyy.tm.api.endpoint;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.request.ApplicationAboutRequest;
import ru.forinnyy.tm.dto.request.ApplicationVersionRequest;
import ru.forinnyy.tm.dto.response.ApplicationAboutResponse;
import ru.forinnyy.tm.dto.response.ApplicationVersionResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ISystemEndpoint extends IEndpoint {

    @NonNull
    String NAME = "SystemEndpoint";

    @NonNull
    String PART = NAME + "Service";

    @SneakyThrows
    @WebMethod(exclude = true)
    static ISystemEndpoint newInstance() {
        return newInstance(HOST, PORT);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static ISystemEndpoint newInstance(@NonNull final IConnectionProvider connectionProvider) {
        return IEndpoint.newInstance(connectionProvider, NAME, SPACE, PART, ISystemEndpoint.class);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static ISystemEndpoint newInstance(@NonNull final String host, @NonNull final String port) {
        return IEndpoint.newInstanse(host, port, NAME, SPACE, PART, ISystemEndpoint.class);
    }

    @NonNull
    @WebMethod
    ApplicationAboutResponse getAbout(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull ApplicationAboutRequest request
    );

    @NonNull
    @WebMethod
    ApplicationVersionResponse getVersion(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull ApplicationVersionRequest request
    );

    @WebMethod(exclude = true)
    public static void main(String[] args) {
        ISystemEndpoint.newInstance().getAbout(new ApplicationAboutRequest());
    }

}
