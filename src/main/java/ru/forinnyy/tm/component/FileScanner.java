package ru.forinnyy.tm.component;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.command.AbstractCommand;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class FileScanner extends Thread {

    private Bootstrap bootstrap;

    @NonNull
    private final List<String> commands = new ArrayList<>();

    @NonNull
    private final File folder = new File("./");

    public FileScanner(@NonNull final Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
        this.setDaemon(true);
    }

    public void init() {
        @NonNull final Iterable<AbstractCommand> commands = bootstrap.getCommandService().getTerminalCommands();
        commands.forEach(e -> this.commands.add(e.getName()));
        start();
    }

    @Override
    @SneakyThrows
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Thread.sleep(1000);
            for (@NonNull final File file : folder.listFiles()) {
                if (file.isDirectory()) continue;
                @NonNull final String fileName = file.getName();
                final boolean check = commands.contains(fileName);
                if (check) {
                    try {
                        file.delete();
                        bootstrap.processCommand(fileName);
                    } catch (@NonNull final Exception e) {
                        System.out.println("ERROR :" + e); // TODO исправить на логгер
                    }
                }
            }
        }
    }

}
