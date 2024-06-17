package ru.forinnyy.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.User;

public final class UserViewProfileCommand extends AbstractUserCommand {

    @NotNull
    private static final String NAME = "view-user-profile";

    @NotNull
    private static final String DESCRIPTION = "View profile of current user";

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
    public void execute() throws AbstractFieldException, AbstractUserException {
        @NotNull final User user = getAuthService().getUser();
        System.out.println("[USER VIEW PROFILE]");
        System.out.println("ID: " + user.getId());
        System.out.println("LOGIN: " + user.getLogin());
        System.out.println("FIRST NAME: " + user.getFirstName());
        System.out.println("MIDDLE NAME: " + user.getMiddleName());
        System.out.println("LAST NAME: " + user.getLastName());
        System.out.println("EMAIL: " + user.getEmail());
        System.out.println("ROLE: " + user.getRole());
    }

    @Nullable
    @Override
    public Role[] getRoles() {
        return Role.values();
    }

}
