package ru.forinnyy.tm.command.user;

import ru.forinnyy.tm.api.service.IAuthService;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.util.TerminalUtil;


public final class UserRegistryCommand extends AbstractUserCommand {

    private final String NAME = "user-registry";

    private final String DESCRIPTION = "User registration";

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
        System.out.println("[USER REGISTRY]");
        System.out.println("ENTER LOGIN:");
        final String login = TerminalUtil.nextLine();
        System.out.println("ENTER PASSWORD:");
        final String password = TerminalUtil.nextLine();
        System.out.println("ENTER EMAIL:");
        final String email = TerminalUtil.nextLine();
        final IAuthService authService = getAuthService();
        final User user = authService.registry(login, password, email);
        showUser(user);
    }
    
}
