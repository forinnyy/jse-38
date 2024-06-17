package ru.forinnyy.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public class UserChangePasswordCommand extends AbstractUserCommand {

    private final String NAME = "change-user-password";

    private final String DESCRIPTION = "Change password of current user";

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
        System.out.println("[USER CHANGE PASSWORD]");
        System.out.println("ENTER NEW PASSWORD:");
        final String password = TerminalUtil.nextLine();
        final String userId = getUserId();
        getUserService().setPassword(userId, password);
    }

    @Override
    public Role[] getRoles() {
        return Role.values();
    }

}