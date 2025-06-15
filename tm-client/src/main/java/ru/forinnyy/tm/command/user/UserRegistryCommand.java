package ru.forinnyy.tm.command.user;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.UserRegistryRequest;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.util.TerminalUtil;


public final class UserRegistryCommand extends AbstractUserCommand {

    private static final String NAME = "user-registry";

    private static final String DESCRIPTION = "User registration";

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
        System.out.println("[USER REGISTRY]");
        System.out.println("ENTER LOGIN:");
        @NonNull final String login = TerminalUtil.nextLine();
        System.out.println("ENTER PASSWORD:");
        @NonNull final String password = TerminalUtil.nextLine();
        System.out.println("ENTER EMAIL:");
        @NonNull final String email = TerminalUtil.nextLine();

        @NonNull final UserRegistryRequest request = new UserRegistryRequest();
        request.setLogin(login);
        request.setEmail(email);
        request.setPassword(password);
        getUserEndpoint().registryUser(request);
    }

    @Override
    public Role[] getRoles() {
        return null;
    }
    
}
