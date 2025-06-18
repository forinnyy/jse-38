package ru.forinnyy.tm.dto.request;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class DataJsonSaveFasterXmlRequest extends AbstractUserRequest {

    public DataJsonSaveFasterXmlRequest(String token) {
        super(token);
    }

}
