package ru.forinnyy.tm.command.user;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class UserLockCommand extends AbstractUserCommand {

    @NotNull
    private static final String NAME = "user-lock";

    @NotNull
    private static final String DESCRIPTION = "Lock user.";

    @NotNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @NotNull
    @Override
    public String getName() {
        return NAME;
    }

    @NotNull
    @Override
    public Role[] getRoles() {
        return  new Role[] {
                Role.ADMIN
        };
    }

    @Override
    public void execute() throws AbstractFieldException, AbstractUserException, AbstractEntityException {
        System.out.println("[USER LOCK]");
        System.out.println("ENTER LOGIN");
        @NotNull final String login = TerminalUtil.nextLine();
        getUserService().lockUserByLogin(login);
    }

}
