package ru.forinnyy.tm.dto.request;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class DataXmlLoadJaxBRequest extends AbstractUserRequest {

    public DataXmlLoadJaxBRequest(String token) {
        super(token);
    }

}
