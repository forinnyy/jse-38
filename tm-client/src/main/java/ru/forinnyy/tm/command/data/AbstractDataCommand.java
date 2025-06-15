package ru.forinnyy.tm.command.data;

import ru.forinnyy.tm.api.endpoint.IDomainEndpoint;
import ru.forinnyy.tm.command.AbstractCommand;

public abstract class AbstractDataCommand extends AbstractCommand {

    public AbstractDataCommand() {
    }

    protected IDomainEndpoint getDomainEndpoint() {
        return getServiceLocator().getDomainEndpoint();
    }

}
