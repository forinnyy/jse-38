package ru.forinnyy.tm.command.user;

import lombok.NonNull;
import ru.forinnyy.tm.dto.request.UserProfileRequest;
import ru.forinnyy.tm.dto.response.UserProfileResponse;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;
import ru.forinnyy.tm.model.User;

public final class UserViewProfileCommand extends AbstractUserCommand {

    @NonNull
    private static final String NAME = "view-user-profile";

    @NonNull
    private static final String DESCRIPTION = "View profile of current user";

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
    public void execute() throws AbstractFieldException, AbstractUserException {
        @NonNull final UserProfileRequest request = new UserProfileRequest();
        @NonNull final UserProfileResponse response = getAuthEndpointClient().profile(request);

        @NonNull final User user = response.getUser();
        System.out.println("[USER VIEW PROFILE]");
        System.out.println("ID: " + user.getId());
        System.out.println("LOGIN: " + user.getLogin());
        System.out.println("FIRST NAME: " + user.getFirstName());
        System.out.println("MIDDLE NAME: " + user.getMiddleName());
        System.out.println("LAST NAME: " + user.getLastName());
        System.out.println("EMAIL: " + user.getEmail());
        System.out.println("ROLE: " + user.getRole());
    }

    @Override
    public Role[] getRoles() {
        return Role.values();
    }

}
