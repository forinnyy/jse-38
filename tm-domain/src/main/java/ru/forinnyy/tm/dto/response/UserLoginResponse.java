package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class UserLoginResponse extends AbstractResultResponse {

    @NonNull
    private String token;

    public UserLoginResponse(@NonNull String token) {
        this.token = token;
    }

    public UserLoginResponse(@NonNull Throwable throwable) {
        super(throwable);
    }

}
