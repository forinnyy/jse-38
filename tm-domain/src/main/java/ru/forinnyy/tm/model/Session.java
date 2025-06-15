package ru.forinnyy.tm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import ru.forinnyy.tm.enumerated.Role;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public final class Session extends AbstractUserOwnedModel {

    @NonNull
    private Date date = new Date();

    private Role role = null;

}
