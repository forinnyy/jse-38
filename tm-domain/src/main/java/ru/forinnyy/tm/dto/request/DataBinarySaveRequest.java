package ru.forinnyy.tm.dto.request;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class DataBinarySaveRequest extends AbstractUserRequest {

    public DataBinarySaveRequest(String token) {
        super(token);
    }

}
