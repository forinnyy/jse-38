package ru.forinnyy.tm;

import lombok.NonNull;
import ru.forinnyy.tm.component.Bootstrap;
import ru.forinnyy.tm.exception.AbstractException;

import javax.naming.AuthenticationException;

public final class Application {

    public static void main(final String... args) throws AbstractException, AuthenticationException {
        @NonNull final Bootstrap bootstrap = new Bootstrap();
        bootstrap.run(args);
    }

}