package ru.forinnyy.tm.dto.request;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class DataBase64SaveRequest extends AbstractUserRequest {

    public DataBase64SaveRequest(String token) {
        super(token);
    }

}
