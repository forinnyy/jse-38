package ru.forinnyy.tm.command.user;

import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class UserUpdateProfileCommand extends AbstractUserCommand {

    private final String NAME = "update-user-profile";

    private final String DESCRIPTION = "Update profile of current user";

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
        System.out.println("[USER UPDATE PROFILE]");
        System.out.println("ENTER FIRST NAME:");
        final String firstName = TerminalUtil.nextLine();
        System.out.println("ENTER LAST NAME:");
        final String lastName = TerminalUtil.nextLine();
        System.out.println("ENTER MIDDLE NAME:");
        final String middleName = TerminalUtil.nextLine();
        final String userId = getUserId();
        getUserService().updateUser(userId, firstName, lastName, middleName);
    }

    @Override
    public Role[] getRoles() {
        return Role.values();
    }

}
