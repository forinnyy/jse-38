package ru.forinnyy.tm.command.user;

import ru.forinnyy.tm.api.service.IAuthService;
import ru.forinnyy.tm.api.service.IUserService;
import ru.forinnyy.tm.command.AbstractCommand;
import ru.forinnyy.tm.exception.entity.UserNotFoundException;
import ru.forinnyy.tm.model.User;

public abstract class AbstractUserCommand extends AbstractCommand {

    public IUserService getUserService() {
        return serviceLocator.getUserService();
    }

    public IAuthService getAuthService() {
        return serviceLocator.getAuthService();
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
