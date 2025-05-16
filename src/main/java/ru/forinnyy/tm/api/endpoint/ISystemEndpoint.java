package ru.forinnyy.tm.api.endpoint;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.ServerAboutRequest;
import ru.forinnyy.tm.dto.request.ServerVersionRequest;
import ru.forinnyy.tm.dto.response.ServerAboutResponse;
import ru.forinnyy.tm.dto.response.ServerVersionResponse;

public interface ISystemEndpoint {

    @NonNull
    ServerAboutResponse getAbout(@NonNull ServerAboutRequest request);

    @NonNull
    ServerVersionResponse getVersion(@NonNull ServerVersionRequest request);

}
