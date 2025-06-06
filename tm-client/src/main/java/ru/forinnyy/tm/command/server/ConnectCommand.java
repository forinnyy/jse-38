package ru.forinnyy.tm.command.server;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.endpoint.IEndpointClient;
import ru.forinnyy.tm.api.service.IServiceLocator;
import ru.forinnyy.tm.command.AbstractCommand;
import ru.forinnyy.tm.enumerated.Role;

import java.net.Socket;

public final class ConnectCommand extends AbstractCommand {

    @NonNull
    public static final String NAME = "connect";

    @Override
    @SneakyThrows
    public void execute() {
        try {
            @NonNull final IServiceLocator sl = getServiceLocator();
            @NonNull final IEndpointClient endpointClient = sl.getConnectionEndpointClient();
            final Socket socket = endpointClient.connect();
            sl.getAuthEndpointClient().setSocket(socket);
            sl.getSystemEndpointClient().setSocket(socket);
            sl.getDomainEndpointClient().setSocket(socket);
            sl.getProjectEndpointClient().setSocket(socket);
            sl.getTaskEndpointClient().setSocket(socket);
            sl.getUserEndpointClient().setSocket(socket);
        } catch (@NonNull final Exception e) {
            System.out.println("Error " + e.getMessage()); // TODO
        }
    }

    @Override
    public String getArgument() {
        return "";
    }

    @Override
    public @NonNull String getDescription() {
        return "";
    }

    @Override
    public @NonNull String getName() {
        return "";
    }

    @Override
    public Role[] getRoles() {
        return new Role[0];
    }

}
