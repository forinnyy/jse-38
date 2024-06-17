package ru.forinnyy.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;

public final class UserRemoveCommand extends AbstractUserCommand {

    @NotNull
    private static final String NAME = "user-remove";

    @NotNull
    private static final String DESCRIPTION = "Remove user.";

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

    @NotNull
    @Override
    public Role[] getRoles() {
        return  new Role[] {
                Role.ADMIN
        };
    }

    @Override
    public void execute() throws AbstractFieldException, AbstractUserException, AbstractEntityException {
        System.out.println("[USER REMOVE]");
        System.out.println("ENTER LOGIN");
        @NotNull final String login = TerminalUtil.nextLine();
        getUserService().removeByLogin(login);
    }

}
