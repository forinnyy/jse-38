package ru.forinnyy.tm.service;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import ru.forinnyy.tm.api.service.ITokenService;

@Getter
@Setter
public final class TokenService implements ITokenService {

    @NonNull
    private String token;

}
