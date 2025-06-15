package ru.forinnyy.tm.command.user;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.UserChangePasswordRequest;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public class UserChangePasswordCommand extends AbstractUserCommand {

    @NonNull
    private static final String NAME = "change-user-password";

    @NonNull
    private static final String DESCRIPTION = "Change password of current user";

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
        System.out.println("[USER CHANGE PASSWORD]");
        System.out.println("ENTER NEW PASSWORD:");
        @NonNull final String password = TerminalUtil.nextLine();

        @NonNull final UserChangePasswordRequest request = new UserChangePasswordRequest(getToken());
        request.setPassword(password);
        getUserEndpoint().changeUserPassword(request);
    }

    @Override
    public Role[] getRoles() {
        return Role.values();
    }

}