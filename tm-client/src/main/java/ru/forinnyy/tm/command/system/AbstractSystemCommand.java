package ru.forinnyy.tm.command.system;

import lombok.NonNull;
import ru.forinnyy.tm.api.service.ICommandService;
import ru.forinnyy.tm.api.service.IPropertyService;
import ru.forinnyy.tm.command.AbstractCommand;
import ru.forinnyy.tm.enumerated.Role;

public abstract class AbstractSystemCommand extends AbstractCommand {

    @NonNull
    protected ICommandService getCommandService() {
        return serviceLocator.getCommandService();
    }

    @NonNull
    protected IPropertyService getPropertyService() {
        return serviceLocator.getPropertyService();
    }

    @Override
    public Role[] getRoles() {
        return null;
    }

}
