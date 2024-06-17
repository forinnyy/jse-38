package ru.forinnyy.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class UserUpdateProfileCommand extends AbstractUserCommand {

    @NotNull
    private static final String NAME = "update-user-profile";

    @NotNull
    private static final String DESCRIPTION = "Update profile of current user";

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
        System.out.println("[USER UPDATE PROFILE]");
        System.out.println("ENTER FIRST NAME:");
        @NotNull final String firstName = TerminalUtil.nextLine();
        System.out.println("ENTER LAST NAME:");
        final String lastName = TerminalUtil.nextLine();
        System.out.println("ENTER MIDDLE NAME:");
        final String middleName = TerminalUtil.nextLine();
        final String userId = getUserId();
        getUserService().updateUser(userId, firstName, lastName, middleName);
    }

    @NotNull
    @Override
    public Role[] getRoles() {
        return Role.values();
    }

}
