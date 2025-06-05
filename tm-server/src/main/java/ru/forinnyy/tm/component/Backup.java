package ru.forinnyy.tm.component;

import lombok.NonNull;
import ru.forinnyy.tm.dto.Domain;
import ru.forinnyy.tm.service.DomainService;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class Backup {

    @NonNull
    private final ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();

    @NonNull
    private final Bootstrap bootstrap;

    public Backup(@NonNull final Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public void start() {
        load();
        es.scheduleWithFixedDelay(this::save, 0, 3, TimeUnit.SECONDS);
    }

    public void stop() {
        es.shutdown();
    }

    public void save() {
        bootstrap.getDomainService().saveDataBackup();
    }

    public void load() {
        if (!Files.exists(Paths.get(DomainService.FILE_BACKUP))) return;
        bootstrap.getDomainService().loadDataBackup();
    }

}
