package ru.forinnyy.tm.command.user;

import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;

public final class UserLogoutCommand extends AbstractUserCommand {

    private final String NAME = "logout";

    private final String DESCRIPTION = "Logout current user";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractEntityException, AbstractFieldException, AbstractUserException {
        System.out.println("[USER LOGOUT]");
        getAuthService().logout();
    }

    @Override
    public Role[] getRoles() {
        return Role.values();
    }

}
