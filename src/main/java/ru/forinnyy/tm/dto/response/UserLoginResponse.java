package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class UserLoginResponse extends AbstractResultResponse {

    public UserLoginResponse(@NonNull Throwable throwable) {
        super(throwable);
    }

}
