package ru.forinnyy.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class UserLoginCommand extends AbstractUserCommand {

    private final String NAME = "login";

    private final String DESCRIPTION = "Login user";

    @Override
    public @NotNull String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public @NotNull String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractEntityException, AbstractFieldException, AbstractUserException {
        System.out.println("[USER LOGIN]");
        System.out.println("ENTER LOGIN:");
        final String login = TerminalUtil.nextLine();
        System.out.println("ENTER PASSWORD:");
        final String password = TerminalUtil.nextLine();
        getAuthService().login(login, password);
    }

    @Override
    public Role[] getRoles() {
        return null;
    }

}
