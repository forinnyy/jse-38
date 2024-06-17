package ru.forinnyy.tm.command.system;

import org.jetbrains.annotations.NotNull;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;

import static ru.forinnyy.tm.util.NumberUtil.formatBytes;

public final class SystemInfoCommand extends AbstractSystemCommand {

    @NotNull
    private static final String DESCRIPTION = "Show system info.";

    @NotNull
    private static final String ARGUMENT = "-i";

    @NotNull
    private static final String NAME = "info";

    @NotNull
    @Override
    public String getArgument() {
        return ARGUMENT;
    }

    @NotNull
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @NotNull
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() throws AbstractEntityException, AbstractFieldException {
        System.out.println("[SYSTEM INFO]");
        final int processorCount = Runtime.getRuntime().availableProcessors();
        System.out.println("PROCESSORS: " + processorCount);

        final long maxMemory = Runtime.getRuntime().maxMemory();
        final long totalMemory = Runtime.getRuntime().totalMemory();
        final long freeMemory = Runtime.getRuntime().freeMemory();
        final long usedMemory = totalMemory - freeMemory;

        System.out.println("MAX MEMORY: " + formatBytes(maxMemory));
        System.out.println("TOTAL MEMORY: " + formatBytes(totalMemory));
        System.out.println("FREE MEMORY: " + formatBytes(freeMemory));
        System.out.println("USED MEMORY: " + formatBytes(usedMemory));
    }

}
