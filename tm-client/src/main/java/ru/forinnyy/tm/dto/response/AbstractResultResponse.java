package ru.forinnyy.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractResultResponse extends AbstractResponse {

    @NonNull
    private Boolean success = true;

    private String message = "";

    public AbstractResultResponse(@NonNull Throwable throwable) {
        setSuccess(false);
        setMessage(throwable.getMessage());
    }

}
