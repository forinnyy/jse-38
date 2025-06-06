package ru.forinnyy.tm.command.server;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.command.AbstractCommand;
import ru.forinnyy.tm.enumerated.Role;

public class DisconnectCommand extends AbstractCommand {

    @NonNull
    public static final String NAME = "disconnect";

    @Override
    @SneakyThrows
    public void execute() {
        try {
            getServiceLocator().getConnectionEndpointClient().disconnect();
        } catch (@NonNull final Exception e) {
            System.out.println("ERROR " + e.getMessage()); // TODO
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
        return NAME;
    }

    @Override
    public Role[] getRoles() {
        return new Role[0];
    }

}
