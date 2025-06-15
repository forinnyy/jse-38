package ru.forinnyy.tm.dto.request;

public final class UserLogoutRequest extends AbstractUserRequest {

    public UserLogoutRequest(String token) {
        super(token);
    }

}
