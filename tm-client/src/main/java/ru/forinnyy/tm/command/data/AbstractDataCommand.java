package ru.forinnyy.tm.command.data;

import ru.forinnyy.tm.api.endpoint.IDomainEndpointClient;
import ru.forinnyy.tm.command.AbstractCommand;

public abstract class AbstractDataCommand extends AbstractCommand {

    public AbstractDataCommand() {
    }

    protected IDomainEndpointClient getDomainEndpoint() {
        return getServiceLocator().getDomainEndpointClient();
    }

}
