package ru.forinnyy.tm.client;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.endpoint.ISystemEndpoint;
import ru.forinnyy.tm.dto.request.ApplicationAboutRequest;
import ru.forinnyy.tm.dto.request.ApplicationVersionRequest;
import ru.forinnyy.tm.dto.response.ApplicationAboutResponse;
import ru.forinnyy.tm.dto.response.ApplicationVersionResponse;

public final class SystemEndpointClient extends AbstractEndpointClient implements ISystemEndpoint {

    @NonNull
    @Override
    @SneakyThrows
    public ApplicationAboutResponse getAbout(@NonNull ApplicationAboutRequest request) {
        return (ApplicationAboutResponse) call(request, ApplicationAboutResponse.class);
    }

    @NonNull
    @Override
    @SneakyThrows
    public ApplicationVersionResponse getVersion(@NonNull ApplicationVersionRequest request) {
        return (ApplicationVersionResponse) call(request, ApplicationVersionResponse.class);
    }

    @SneakyThrows
    public static void main(String[] args) {
        final SystemEndpointClient client = new SystemEndpointClient();
        client.connect();
        final ApplicationAboutResponse applicationAboutResponse = client.getAbout(new ApplicationAboutRequest());
        System.out.println(applicationAboutResponse.getEmail());
        System.out.println(applicationAboutResponse.getName());
        final ApplicationVersionResponse applicationVersionResponse = client.getVersion(new ApplicationVersionRequest());
        System.out.println(applicationVersionResponse.getVersion());
        client.disconnect();
    }

}
