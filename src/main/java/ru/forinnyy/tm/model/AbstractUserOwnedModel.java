package ru.forinnyy.tm.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractUserOwnedModel extends AbstractModel {

    @Nullable
    private String userId;

}
