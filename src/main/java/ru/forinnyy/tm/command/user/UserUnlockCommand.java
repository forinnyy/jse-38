package ru.forinnyy.tm.command.user;

import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class UserUnlockCommand extends AbstractUserCommand {

    private final String NAME = "user-unlock";

    private final String DESCRIPTION = "Unlock user.";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Role[] getRoles() {
        return  new Role[] {
                Role.ADMIN
        };
    }

    @Override
    public void execute() throws AbstractFieldException, AbstractUserException, AbstractEntityException {
        System.out.println("[USER UNLOCK]");
        System.out.println("ENTER LOGIN");
        final String login = TerminalUtil.nextLine();
        getUserService().unlockUserByLogin(login);
    }

}
