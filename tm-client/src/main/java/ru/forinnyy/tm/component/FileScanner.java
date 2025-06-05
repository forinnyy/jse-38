package ru.forinnyy.tm.component;

import lombok.NonNull;
import ru.forinnyy.tm.command.AbstractCommand;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class FileScanner {

    @NonNull
    private final ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();

    @NonNull
    private final Bootstrap bootstrap;

    @NonNull
    private final List<String> commands = new ArrayList<>();

    @NonNull
    private final File folder = new File("./");

    public FileScanner(@NonNull final Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public void start() {
        @NonNull final Iterable<AbstractCommand> commands = bootstrap.getCommandService().getTerminalCommands();
        commands.forEach(e -> this.commands.add(e.getName()));
        es.scheduleWithFixedDelay(this::process, 0, 3, TimeUnit.SECONDS);
    }

    public void stop() {
        es.shutdown();
    }

    public void process() {
        for (@NonNull final File file: folder.listFiles()) {
            if (file.isDirectory()) continue;
            @NonNull final String fileName = file.getName();
            final boolean check = commands.contains(fileName);
            if (check) {
                try {
                    file.delete();
                    bootstrap.processCommand(fileName);
                } catch (@NonNull final Exception e) {
                    System.out.println("[ERROR : ]" + e); // TODO logger
                }
            }
        }
    }

}
