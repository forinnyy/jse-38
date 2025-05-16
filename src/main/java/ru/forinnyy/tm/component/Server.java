package ru.forinnyy.tm.component;

import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.api.endpoint.Operation;
import ru.forinnyy.tm.api.service.IPropertyService;
import ru.forinnyy.tm.dto.request.AbstractRequest;
import ru.forinnyy.tm.dto.response.AbstractResponse;
import ru.forinnyy.tm.task.AbstractServerTask;
import ru.forinnyy.tm.task.ServerAcceptTask;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Server {

    @NonNull
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @NonNull
    private final Dispatcher dispatcher = new Dispatcher();

    @Getter
    private ServerSocket socketServer;

    @NonNull
    private final Bootstrap bootstrap;

    public Server(@NonNull Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public void submit(@NonNull final AbstractServerTask task) {
        executorService.submit(task);
    }

    @SneakyThrows
    public void start() {
        @NonNull final IPropertyService propertyService = bootstrap.getPropertyService();
        @NonNull final Integer port = propertyService.getServerPort();
        socketServer = new ServerSocket(port);
        submit(new ServerAcceptTask(this));
    }

    public <RQ extends AbstractRequest, RS extends AbstractResponse> void registry(
            @NonNull final Class<RQ> reqClass, @NonNull final Operation<RQ, RS> operation
    ) {
        dispatcher.registry(reqClass, operation);
    }

    @NonNull
    public Object call(@NonNull final AbstractRequest request) {
        return dispatcher.call(request);
    }

    @SneakyThrows
    public void stop() {
        if (socketServer == null) return;
        executorService.shutdown();
        socketServer.close();
    }

}
