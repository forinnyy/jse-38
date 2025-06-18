package ru.forinnyy.tm.dto.request;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class DataJsonLoadJaxBRequest extends AbstractUserRequest {

    public DataJsonLoadJaxBRequest(String token) {
        super(token);
    }

}
