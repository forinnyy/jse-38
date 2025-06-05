package ru.forinnyy.tm.command.user;

import lombok.NonNull;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class UserUpdateProfileCommand extends AbstractUserCommand {

    @NonNull
    private static final String NAME = "update-user-profile";

    @NonNull
    private static final String DESCRIPTION = "Update profile of current user";

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
        System.out.println("[USER UPDATE PROFILE]");
        System.out.println("ENTER FIRST NAME:");
        @NonNull final String firstName = TerminalUtil.nextLine();
        System.out.println("ENTER LAST NAME:");
        @NonNull final String lastName = TerminalUtil.nextLine();
        System.out.println("ENTER MIDDLE NAME:");
        @NonNull final String middleName = TerminalUtil.nextLine();
        @NonNull final String userId = getUserId();
        getUserService().updateUser(userId, firstName, lastName, middleName);
    }

    @NonNull
    @Override
    public Role[] getRoles() {
        return Role.values();
    }

}
