package ru.forinnyy.tm.endpoint;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.api.endpoint.ISystemEndpoint;
import ru.forinnyy.tm.api.service.IPropertyService;
import ru.forinnyy.tm.api.service.IServiceLocator;
import ru.forinnyy.tm.dto.request.ServerAboutRequest;
import ru.forinnyy.tm.dto.request.ServerVersionRequest;
import ru.forinnyy.tm.dto.response.ServerAboutResponse;
import ru.forinnyy.tm.dto.response.ServerVersionResponse;

public final class SystemEndpoint implements ISystemEndpoint {

    @NonNull
    private final IServiceLocator serviceLocator;

    public SystemEndpoint(@NonNull final IServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @NotNull
    @Override
    public ServerAboutResponse getAbout(@NotNull final ServerAboutRequest request) {
        @NonNull final IPropertyService propertyService = serviceLocator.getPropertyService();
        @NonNull final ServerAboutResponse response = new ServerAboutResponse();
        response.setEmail(propertyService.getAuthorEmail());
        response.setName(propertyService.getAuthorName());
        return response;
    }

    @NotNull
    @Override
    public ServerVersionResponse getVersion(@NotNull final ServerVersionRequest request) {
        @NonNull final IPropertyService propertyService = serviceLocator.getPropertyService();
        @NonNull final ServerVersionResponse response = new ServerVersionResponse();
        response.setVersion(propertyService.getApplicationVersion());
        return response;
    }

}
