package ru.forinnyy.tm.command;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import ru.forinnyy.tm.api.model.ICommand;
import ru.forinnyy.tm.api.service.IAuthService;
import ru.forinnyy.tm.api.service.IServiceLocator;
import ru.forinnyy.tm.exception.user.AbstractUserException;


@Setter
@Getter
public abstract class AbstractCommand implements ICommand {

    @NonNull
    protected IServiceLocator serviceLocator;

    @NonNull
    public IAuthService getAuthService() {
        return serviceLocator.getAuthService();
    }

    @NonNull
    public String getUserId() throws AbstractUserException {
        return getAuthService().getUserId();
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
