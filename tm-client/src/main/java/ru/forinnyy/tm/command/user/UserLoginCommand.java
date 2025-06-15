package ru.forinnyy.tm.command.user;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.UserLoginRequest;
import ru.forinnyy.tm.dto.response.UserLoginResponse;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.util.TerminalUtil;

public final class UserLoginCommand extends AbstractUserCommand {

    @NonNull
    private static final String NAME = "login";

    @NonNull
    private static final String DESCRIPTION = "Login user";

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
    public void execute() {
        System.out.println("[USER LOGIN]");
        System.out.println("ENTER LOGIN:");
        @NonNull final String login = TerminalUtil.nextLine();
        System.out.println("ENTER PASSWORD:");
        @NonNull final String password = TerminalUtil.nextLine();
        @NonNull final UserLoginRequest request = new UserLoginRequest();
        request.setLogin(login);
        request.setPassword(password);

        @NonNull final UserLoginResponse response = getAuthEndpoint().login(request);
        @NonNull final String token = response.getToken();
        setToken(token);
        System.out.println(token);
        if (!response.getSuccess()) {
            throw new RuntimeException(response.getMessage());
        }
    }

    @Override
    public Role[] getRoles() {
        return null;
    }

}
