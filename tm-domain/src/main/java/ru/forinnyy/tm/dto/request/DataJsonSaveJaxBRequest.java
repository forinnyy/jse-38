package ru.forinnyy.tm.dto.request;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class DataJsonSaveJaxBRequest extends AbstractUserRequest {

    public DataJsonSaveJaxBRequest(String token) {
        super(token);
    }

}
