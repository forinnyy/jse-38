package ru.forinnyy.tm.client;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.endpoint.ISystemEndpoint;
import ru.forinnyy.tm.dto.request.ServerAboutRequest;
import ru.forinnyy.tm.dto.request.ServerVersionRequest;
import ru.forinnyy.tm.dto.response.ServerAboutResponse;
import ru.forinnyy.tm.dto.response.ServerVersionResponse;

public final class SystemEndpointClient extends AbstractClient implements ISystemEndpoint {

    @NonNull
    @Override
    @SneakyThrows
    public ServerAboutResponse getAbout(@NonNull ServerAboutRequest request) {
        return (ServerAboutResponse) call(request);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ServerVersionResponse getVersion(@NonNull ServerVersionRequest request) {
        return (ServerVersionResponse) call(request);
    }

    @SneakyThrows
    public static void main(String[] args) {
        final SystemEndpointClient client = new SystemEndpointClient();
        client.connect();
        final ServerAboutResponse serverAboutResponse = client.getAbout(new ServerAboutRequest());
        System.out.println(serverAboutResponse.getEmail());
        System.out.println(serverAboutResponse.getName());
        final ServerVersionResponse serverVersionResponse = client.getVersion(new ServerVersionRequest());
        System.out.println(serverVersionResponse.getVersion());
        client.disconnect();
    }

}
