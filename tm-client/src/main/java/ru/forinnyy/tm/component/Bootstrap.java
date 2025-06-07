package ru.forinnyy.tm.component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.forinnyy.tm.api.endpoint.*;
import ru.forinnyy.tm.api.repository.ICommandRepository;
import ru.forinnyy.tm.api.service.ICommandService;
import ru.forinnyy.tm.api.service.IPropertyService;
import ru.forinnyy.tm.api.service.IServiceLocator;
import ru.forinnyy.tm.client.*;
import ru.forinnyy.tm.command.AbstractCommand;
import ru.forinnyy.tm.exception.system.ArgumentNotSupportedException;
import ru.forinnyy.tm.exception.system.CommandNotSupportedException;
import ru.forinnyy.tm.repository.CommandRepository;
import ru.forinnyy.tm.service.CommandService;
import ru.forinnyy.tm.service.PropertyService;
import ru.forinnyy.tm.util.SystemUtil;
import ru.forinnyy.tm.util.TerminalUtil;

import java.io.File;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;


@NoArgsConstructor
public final class Bootstrap implements IServiceLocator {

    @NonNull
    private static final String PACKAGE_COMMANDS = "ru.forinnyy.tm.command";

    @NonNull
    private static final Logger LOGGER_LIFECYCLE = LoggerFactory.getLogger("LIFECYCLE");

    @NonNull
    private static final Logger LOGGER_COMMANDS = LoggerFactory.getLogger("COMMANDS");

    @NonNull
    private final ICommandRepository commandRepository = new CommandRepository();

    @Getter
    @NonNull
    private final ICommandService commandService = new CommandService(commandRepository);

    @Getter
    @NonNull
    private final IPropertyService propertyService = new PropertyService();

    @NonNull
    private final FileScanner fileScanner = new FileScanner(this);

    @Getter
    @NonNull
    private final IEndpointClient connectionEndpointClient = new ConnectionEndpointClient();

    @Getter
    @NonNull
    private final ISystemEndpointClient systemEndpointClient = new SystemEndpointClient();

    @Getter
    @NonNull
    private final IDomainEndpointClient domainEndpointClient = new DomainEndpointClient();

    @Getter
    @NonNull
    private final IProjectEndpointClient projectEndpointClient = new ProjectEndpointClient();

    @Getter
    @NonNull
    private final ITaskEndpointClient taskEndpointClient = new TaskEndpointClient();

    @Getter
    @NonNull
    private final IAuthEndpointClient authEndpointClient = new AuthEndpointClient();

    @Getter
    @NonNull
    private final IUserEndpointClient userEndpointClient = new UserEndpointClient();

    {
        @NonNull final Reflections reflections = new Reflections(PACKAGE_COMMANDS);
        @NonNull final Set<Class<? extends AbstractCommand>> classes =
                reflections.getSubTypesOf(AbstractCommand.class);
        for (@NonNull final Class<? extends AbstractCommand> clazz : classes) registry(clazz);
    }

    private void initFileScanner() {
        fileScanner.start();
    }

    @SneakyThrows
    private void registry(@NonNull final Class<? extends AbstractCommand> clazz) {
        if (Modifier.isAbstract(clazz.getModifiers())) return;
        if (!AbstractCommand.class.isAssignableFrom(clazz)) return;
        final AbstractCommand command = clazz.newInstance();
        registry(command);
    }

    private void registry(@NonNull final AbstractCommand command) {
        command.setServiceLocator(this);
        commandService.add(command);
    }

    @SneakyThrows
    private void processArgument(final String arg) {
        final AbstractCommand abstractCommand = commandService.getCommandByArgument(arg);
        if (abstractCommand == null) throw new ArgumentNotSupportedException(arg);
        abstractCommand.execute();
    }

    @SneakyThrows
    private boolean processArguments(final String[] args) {
        if (args == null || args.length < 1) return false;
        processArgument(args[0]);
        return true;
    }

    public void processCommand(final String command) {
        processCommand(command, true);
    }

    @SneakyThrows
    public void processCommand(final String command, boolean checkRoles) {
        final AbstractCommand abstractCommand = commandService.getCommandByName(command);
        if (abstractCommand == null) throw new CommandNotSupportedException(command);
        abstractCommand.execute();
    }

    @SneakyThrows
    private void initPID() {
        @NonNull final String filename = "task-manager.pid";
        @NonNull final String pid = Long.toString(SystemUtil.getPID());
        Files.write(Paths.get(filename), pid.getBytes());
        @NonNull final File file = new File(filename);
        file.deleteOnExit();
    }

    private void initCommands() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("ENTER COMMAND:");
                @NonNull final String command = TerminalUtil.nextLine();
                LOGGER_COMMANDS.info(command);
                processCommand(command);
                System.out.println("[OK]");
            } catch (@NonNull final Exception e) {
                LOGGER_LIFECYCLE.error(e.getMessage());
                System.err.println("[FAIL]");
            }
        }
    }

    @SneakyThrows
    private void prepareStartup() {
        initPID();
        LOGGER_LIFECYCLE.info("** WELCOME TO TASK-MANAGER **");
        Runtime.getRuntime().addShutdownHook(new Thread(this::prepareShutdown));
        initFileScanner();
    }

    @SneakyThrows
    private void prepareShutdown() {
        LOGGER_LIFECYCLE.info("** TASK-MANAGER IS SHUTTING DOWN **");
        fileScanner.stop();
    }

    public void run(final String[] args) {
        if (processArguments(args)) System.exit(0);
        prepareStartup();
        initCommands();
    }

}
