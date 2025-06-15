package ru.forinnyy.tm.endpoint;

import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.service.IServiceLocator;
import ru.forinnyy.tm.dto.request.AbstractUserRequest;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.user.AccessDeniedException;
import ru.forinnyy.tm.exception.user.PermissionException;
import ru.forinnyy.tm.model.Session;


@Getter
public abstract class AbstractEndpoint {

    @NonNull
    @SneakyThrows
    protected Session check(final AbstractUserRequest request, final Role role) {
        if (request == null) throw new AccessDeniedException();
        if (role == null) throw new PermissionException();
        final String token = request.getToken();
        @NonNull final Session session = serviceLocator.getAuthService().validateToken(token);
        if (session.getRole() == null) throw new AccessDeniedException();
        if (!session.getRole().equals(role)) throw new AccessDeniedException();
        return session;
    }

    @NonNull
    @SneakyThrows
    protected Session check(final AbstractUserRequest request) {
        if (request == null) throw new AccessDeniedException();
        final String token = request.getToken();
        if (token == null || token.isEmpty()) throw new AccessDeniedException();
        return serviceLocator.getAuthService().validateToken(token);
    }

    @NonNull
    private final IServiceLocator serviceLocator;

    public AbstractEndpoint(@NonNull final IServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

}
