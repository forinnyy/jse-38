package ru.forinnyy.tm.client;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.endpoint.ISystemEndpointClient;
import ru.forinnyy.tm.dto.request.ApplicationAboutRequest;
import ru.forinnyy.tm.dto.request.ApplicationGitRequest;
import ru.forinnyy.tm.dto.request.ApplicationVersionRequest;
import ru.forinnyy.tm.dto.response.ApplicationAboutResponse;
import ru.forinnyy.tm.dto.response.ApplicationGitResponse;
import ru.forinnyy.tm.dto.response.ApplicationVersionResponse;

public final class SystemEndpointClient extends AbstractEndpointClient implements ISystemEndpointClient {

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

    @NonNull
    @Override
    @SneakyThrows
    public ApplicationGitResponse getGit(@NonNull ApplicationGitRequest request) {
        return (ApplicationGitResponse) call(request, ApplicationGitResponse.class);
    }

}
