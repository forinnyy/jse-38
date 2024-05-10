package ru.forinnyy.tm.command.system;

import ru.forinnyy.tm.api.service.ICommandService;
import ru.forinnyy.tm.command.AbstractCommand;

public abstract class AbstractSystemCommand extends AbstractCommand {

    protected ICommandService getCommandService() {
        return serviceLocator.getCommandService();
    }

}
