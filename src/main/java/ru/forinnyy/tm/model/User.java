package ru.forinnyy.tm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.NonNull;
import ru.forinnyy.tm.enumerated.Role;


@Getter
@Setter
@NoArgsConstructor
public final class User extends AbstractModel {

    private String login;

    private String passwordHash;

    private String email;

    private String firstName;

    private String lastName;

    private String middleName;

    @NonNull
    private Role role = Role.USUAL;

    @NonNull
    private Boolean locked = false;

    public Boolean isLocked() {
        return locked;
    }

}
