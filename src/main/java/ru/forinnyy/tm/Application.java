package ru.forinnyy.tm;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.component.Bootstrap;
import ru.forinnyy.tm.exception.AbstractException;

import javax.naming.AuthenticationException;

public final class Application {

    public static void main(@Nullable final String... args) throws AbstractException, AuthenticationException {
        @NotNull final Bootstrap bootstrap = new Bootstrap();
        bootstrap.run(args);
    }

}