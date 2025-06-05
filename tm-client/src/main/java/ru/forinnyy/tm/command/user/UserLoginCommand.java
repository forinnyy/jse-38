package ru.forinnyy.tm.command.user;

import lombok.NonNull;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

import javax.naming.AuthenticationException;

public final class UserLoginCommand extends AbstractUserCommand {

    @NonNull
    private static final String NAME = "login";

    @NonNull
    private static final String DESCRIPTION = "Login user";

    @NonNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @NonNull
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractEntityException, AbstractFieldException, AbstractUserException, AuthenticationException {
        System.out.println("[USER LOGIN]");
        System.out.println("ENTER LOGIN:");
        @NonNull final String login = TerminalUtil.nextLine();
        System.out.println("ENTER PASSWORD:");
        @NonNull final String password = TerminalUtil.nextLine();
        getAuthService().login(login, password);
    }
    
    @Override
    public Role[] getRoles() {
        return null;
    }

}
