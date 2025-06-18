package ru.forinnyy.tm.dto.request;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class DataXmlSaveJaxBRequest extends AbstractUserRequest {

    public DataXmlSaveJaxBRequest(String token) {
        super(token);
    }

}
