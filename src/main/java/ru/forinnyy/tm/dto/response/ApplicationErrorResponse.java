package ru.forinnyy.tm.dto.response;

import lombok.NonNull;

public class ApplicationErrorResponse extends AbstractResultResponse {

    public ApplicationErrorResponse() {
        setSuccess(false);
    }

    public ApplicationErrorResponse(@NonNull Throwable throwable) {
        super(throwable);
    }

}
