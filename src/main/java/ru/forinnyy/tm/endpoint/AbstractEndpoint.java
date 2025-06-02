package ru.forinnyy.tm.endpoint;

import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.service.IServiceLocator;
import ru.forinnyy.tm.api.service.IUserService;
import ru.forinnyy.tm.dto.request.AbstractUserRequest;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.user.AccessDeniedException;
import ru.forinnyy.tm.model.User;


@Getter
public abstract class AbstractEndpoint {

    @SneakyThrows
    protected void check(final AbstractUserRequest request, final Role role) {
        if (request == null) throw new AccessDeniedException();
        if (role == null) throw new AccessDeniedException();
        final String userId = request.getUserId();
        if (userId == null || userId.isEmpty()) throw new AccessDeniedException();
        @NonNull final IServiceLocator serviceLocator = getServiceLocator();
        @NonNull final IUserService userService = serviceLocator.getUserService();
        final User user = userService.findOneById(userId);
        if (user == null) throw new AccessDeniedException();
        final Role roleUser = user.getRole();
        final boolean check = roleUser == role;
        if (!check) throw new AccessDeniedException();
    }

    @SneakyThrows
    protected void check(final AbstractUserRequest request) {
        if (request == null) throw new AccessDeniedException();
        final String userId = request.getUserId();
        if (userId == null || userId.isEmpty()) throw new AccessDeniedException();
    }

    @NonNull
    private final IServiceLocator serviceLocator;

    public AbstractEndpoint(@NonNull final IServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

}
