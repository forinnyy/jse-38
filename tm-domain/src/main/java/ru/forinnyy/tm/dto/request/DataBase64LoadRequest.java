package ru.forinnyy.tm.dto.request;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class DataBase64LoadRequest extends AbstractUserRequest {

    public DataBase64LoadRequest(String token) {
        super(token);
    }

}
