package ru.forinnyy.tm.dto.request;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class DataXmlSaveFasterXmlRequest extends AbstractUserRequest {

    public DataXmlSaveFasterXmlRequest(String token) {
        super(token);
    }

}
