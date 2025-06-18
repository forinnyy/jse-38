package ru.forinnyy.tm.dto.request;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class DataBackupLoadRequest extends AbstractUserRequest {

    public DataBackupLoadRequest(String token) {
        super(token);
    }

}
