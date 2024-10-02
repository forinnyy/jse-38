package ru.forinnyy.tm.command.user;

import lombok.NonNull;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;

public final class UserLogoutCommand extends AbstractUserCommand {

    @NonNull
    private static final String NAME = "logout";

    @NonNull
    private static final String DESCRIPTION = "Logout current user";

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
    public void execute() throws AbstractEntityException, AbstractFieldException, AbstractUserException {
        System.out.println("[USER LOGOUT]");
        getAuthService().logout();
    }

    @Override
    public Role[] getRoles() {
        return Role.values();
    }

}
