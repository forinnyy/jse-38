package ru.forinnyy.tm.dto.request;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class DataBackupSaveRequest extends AbstractUserRequest {

    public DataBackupSaveRequest(String token) {
        super(token);
    }

}
