package ru.forinnyy.tm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.enumerated.Role;


@Getter
@Setter
@NoArgsConstructor
public final class User extends AbstractModel {

    @Nullable
    private String login;

    @Nullable
    private String passwordHash;

    @Nullable
    private String email;

    @Nullable
    private String firstName;

    @Nullable
    private String lastName;

    @Nullable
    private String middleName;

    @NotNull
    private Role role = Role.USUAL;

    @NotNull
    private Boolean locked = false;

}
