package ru.forinnyy.tm.dto.request;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class DataJsonLoadFasterXmlRequest extends AbstractUserRequest {

    public DataJsonLoadFasterXmlRequest(String token) {
        super(token);
    }

}
