package ru.forinnyy.tm.command.system;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.api.service.ICommandService;
import ru.forinnyy.tm.command.AbstractCommand;
import ru.forinnyy.tm.enumerated.Role;

public abstract class AbstractSystemCommand extends AbstractCommand {

    @NotNull
    protected ICommandService getCommandService() {
        return serviceLocator.getCommandService();
    }

    @Nullable
    @Override
    public Role[] getRoles() {
        return null;
    }

}
