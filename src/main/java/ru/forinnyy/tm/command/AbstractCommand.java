package ru.forinnyy.tm.command;


import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.api.model.ICommand;
import ru.forinnyy.tm.api.service.IAuthService;
import ru.forinnyy.tm.api.service.IServiceLocator;
import ru.forinnyy.tm.exception.user.AbstractUserException;


@Setter
@Getter
public abstract class AbstractCommand implements ICommand {

    @NotNull
    protected IServiceLocator serviceLocator;

    @NotNull
    public IAuthService getAuthService() {
        return serviceLocator.getAuthService();
    }

    @NotNull
    public String getUserId() throws AbstractUserException {
        return getAuthService().getUserId();
    }

    @NotNull
    @Override
    public String toString() {
        final String name = getName();
        final String argument = getArgument();
        final String description = getDescription();
        @NotNull String result = "";
        if (name != null && !name.isEmpty()) result += name + " : ";
        if (argument != null && !argument.isEmpty()) result += argument + " : ";
        if (description != null && !description.isEmpty()) result += description;
        return result;
    }

}
