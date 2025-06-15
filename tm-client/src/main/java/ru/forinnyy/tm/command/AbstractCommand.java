package ru.forinnyy.tm.command;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import ru.forinnyy.tm.api.endpoint.IAuthEndpoint;
import ru.forinnyy.tm.api.endpoint.IProjectEndpoint;
import ru.forinnyy.tm.api.endpoint.ITaskEndpoint;
import ru.forinnyy.tm.api.model.ICommand;
import ru.forinnyy.tm.api.service.IServiceLocator;


@Setter
@Getter
public abstract class AbstractCommand implements ICommand {

    protected String getToken() {
        return getServiceLocator().getTokenService().getToken();
    }

    protected void setToken(final String token) {
        getServiceLocator().getTokenService().setToken(token);
    }

    @NonNull
    protected IServiceLocator serviceLocator;

    protected IAuthEndpoint getAuthEndpoint() {
        return serviceLocator.getAuthEndpoint();
    }

    @NonNull
    protected ITaskEndpoint getTaskEndpointClient() {
        return getServiceLocator().getTaskEndpoint();
    }

    @NonNull
    protected IProjectEndpoint getProjectEndpointClient() {
        return getServiceLocator().getProjectEndpoint();
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
