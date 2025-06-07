package ru.forinnyy.tm.task;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.service.IAuthService;
import ru.forinnyy.tm.api.service.IUserService;
import ru.forinnyy.tm.component.Server;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;
import ru.forinnyy.tm.exception.user.PermissionException;
import ru.forinnyy.tm.model.User;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public final class ServerRequestTask extends AbstractServerSocketTask {

    private AbstractRequest request;

    private AbstractResponse response;

    public ServerRequestTask(
            @NonNull final Server server,
            @NonNull final Socket socket
    ) {
        super(server, socket);
    }

    public ServerRequestTask(
            @NonNull final Server server,
            @NonNull final Socket socket,
            final String userId
    ) {
        super(server, socket, userId);
    }

    @Override
    @SneakyThrows
    public void run() {
        processInput();

        processUserId();
        processLogin();
        processProfile();
        processLogout();

        processOperation();
        processOutput();
    }

    @SneakyThrows
    private void processInput() {
        @NonNull final InputStream inputStream = socket.getInputStream();
        @NonNull final ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        @NonNull final Object object = objectInputStream.readObject();
        request = (AbstractRequest) object;
    }

    @SneakyThrows
    private void processOutput() {
        @NonNull final OutputStream outputStream = socket.getOutputStream();
        @NonNull final ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(response);
        server.submit(new ServerRequestTask(server, socket, userId));
    }

    private void processUserId() {
        if (!(request instanceof AbstractUserRequest)) return;
        @NonNull final AbstractUserRequest abstractUserRequest = (AbstractUserRequest) request;
        abstractUserRequest.setUserId(userId);
    }

    private void processLogin() {
        if (response != null) return;
        if (!(request instanceof UserLoginRequest)) return;
        try {
            @NonNull final UserLoginRequest userLoginRequest = (UserLoginRequest) request;
            final String login = userLoginRequest.getLogin();
            final String password = userLoginRequest.getPassword();
            @NonNull final IAuthService authService = server.getBootstrap().getAuthService();
            @NonNull final User user = authService.check(login, password);
            userId = user.getId();
            response = new UserLoginResponse();
        } catch (@NonNull final Exception e) {
            response = new UserLoginResponse(e);
        }
    }

    @SneakyThrows
    private void processProfile() {
        if (response != null) return;
        if (!(request instanceof UserProfileRequest)) return;
        if (userId == null) {
            response = new UserProfileResponse();
            return;
        }
        @NonNull final IUserService userService = server.getBootstrap().getUserService();
        final User user = userService.findOneById(userId);
        response = new UserProfileResponse(user);
    }

    private void processOperation() {
        if (response != null) return;
        try {
            final Object result = server.call(request);
            response = (AbstractResponse) result;
        } catch (Exception e) {
            response = new ApplicationErrorResponse(e);
        }
    }

    private void processLogout() {
        if (response != null) return;
        if (!(request instanceof UserLogoutRequest)) return;
        userId = null;
        response = new UserLogoutResponse();
    }

}
