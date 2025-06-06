package ru.forinnyy.tm.command;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import ru.forinnyy.tm.api.endpoint.IAuthEndpointClient;
import ru.forinnyy.tm.api.endpoint.IProjectEndpointClient;
import ru.forinnyy.tm.api.endpoint.ITaskEndpointClient;
import ru.forinnyy.tm.api.model.ICommand;
import ru.forinnyy.tm.api.service.IServiceLocator;


@Setter
@Getter
public abstract class AbstractCommand implements ICommand {

    @NonNull
    protected IServiceLocator serviceLocator;

    protected IAuthEndpointClient getAuthEndpointClient() {
        return serviceLocator.getAuthEndpointClient();
    }

    @NonNull
    protected ITaskEndpointClient getTaskEndpointClient() {
        return getServiceLocator().getTaskEndpointClient();
    }

    @NonNull
    protected IProjectEndpointClient getProjectEndpointClient() {
        return getServiceLocator().getProjectEndpointClient();
    }

    @NonNull
    @Override
    public String toString() {
        final String name = getName();
        final String argument = getArgument();
        final String description = getDescription();
        @NonNull String result = "";
        if (name != null && !name.isEmpty()) result += name + " : ";
        if (argument != null && !argument.isEmpty()) result += argument + " : ";
        if (description != null && !description.isEmpty()) result += description;
        return result;
    }

}
