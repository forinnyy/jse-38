package ru.forinnyy.tm.command.user;

import lombok.NonNull;
import ru.forinnyy.tm.api.endpoint.IAuthEndpointClient;
import ru.forinnyy.tm.api.endpoint.IUserEndpointClient;
import ru.forinnyy.tm.command.AbstractCommand;
import ru.forinnyy.tm.exception.entity.UserNotFoundException;
import ru.forinnyy.tm.model.User;

public abstract class AbstractUserCommand extends AbstractCommand {

    @NonNull
    public IUserEndpointClient getUserEndpointClient() {
        return serviceLocator.getUserEndpointClient();
    }

    @NonNull
    public IAuthEndpointClient getAuthEndpointClient() {
        return super.getAuthEndpointClient();
    }

    protected void showUser(final User user) throws UserNotFoundException {
        if (user == null) throw new UserNotFoundException();
        System.out.println("ID: " + user.getId());
        System.out.println("LOGIN: " + user.getLogin());
    }
    
    @Override
    public String getArgument() {
        return null;
    }

}
