package ru.forinnyy.tm.command.user;

import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public class UserChangePasswordCommand extends AbstractUserCommand {

    private final String NAME = "change-user-password";

    private final String DESCRIPTION = "Change password of current user";

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
        final String userId = getAuthService().getUserId();
        System.out.println("[USER CHANGE PASSWORD]");
        System.out.println("ENTER NEW PASSWORD:");
        final String password = TerminalUtil.nextLine();
        getUserService().setPassword(userId, password);
    }

}
