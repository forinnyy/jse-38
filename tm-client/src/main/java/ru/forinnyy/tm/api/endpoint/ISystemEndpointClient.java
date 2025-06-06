package ru.forinnyy.tm.api.endpoint;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.ApplicationAboutRequest;
import ru.forinnyy.tm.dto.request.ApplicationVersionRequest;
import ru.forinnyy.tm.dto.response.ApplicationAboutResponse;
import ru.forinnyy.tm.dto.response.ApplicationVersionResponse;

public interface ISystemEndpointClient extends IEndpointClient {

    @NonNull
    ApplicationAboutResponse getAbout(@NonNull ApplicationAboutRequest request);

    @NonNull
    ApplicationVersionResponse getVersion(@NonNull ApplicationVersionRequest request);

}
