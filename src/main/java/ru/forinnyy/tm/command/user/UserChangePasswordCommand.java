package ru.forinnyy.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public class UserChangePasswordCommand extends AbstractUserCommand {

    @NotNull
    private static final String NAME = "change-user-password";

    @NotNull
    private static final String DESCRIPTION = "Change password of current user";

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

    @Override
    public void execute() throws AbstractEntityException, AbstractFieldException, AbstractUserException {
        System.out.println("[USER CHANGE PASSWORD]");
        System.out.println("ENTER NEW PASSWORD:");
        @NotNull final String password = TerminalUtil.nextLine();
        @NotNull final String userId = getUserId();
        getUserService().setPassword(userId, password);
    }

    @Override
    public Role[] getRoles() {
        return Role.values();
    }

}