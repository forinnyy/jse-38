package ru.forinnyy.tm;

import ru.forinnyy.tm.component.Bootstrap;
import ru.forinnyy.tm.exception.AbstractException;

public final class Application {

    public static void main(final String... args) throws AbstractException {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.run(args);
    }

}