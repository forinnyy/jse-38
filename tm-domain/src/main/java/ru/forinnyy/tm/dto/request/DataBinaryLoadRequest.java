package ru.forinnyy.tm.dto.request;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class DataBinaryLoadRequest extends AbstractUserRequest {

    public DataBinaryLoadRequest(String token) {
        super(token);
    }

}
