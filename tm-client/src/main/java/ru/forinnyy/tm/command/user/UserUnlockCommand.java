package ru.forinnyy.tm.command.user;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.UserUnlockRequest;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class UserUnlockCommand extends AbstractUserCommand {

    @NonNull
    private static final String NAME = "user-unlock";

    @NonNull
    private static final String DESCRIPTION = "Unlock user.";

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

    @NonNull
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
        @NonNull final String login = TerminalUtil.nextLine();

        @NonNull final UserUnlockRequest request = new UserUnlockRequest(getToken());
        request.setLogin(login);
        getUserEndpoint().unlockUser(request);
    }

}
