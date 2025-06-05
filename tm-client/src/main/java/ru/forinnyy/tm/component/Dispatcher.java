package ru.forinnyy.tm.component;

import lombok.NonNull;
import ru.forinnyy.tm.api.endpoint.Operation;
import ru.forinnyy.tm.dto.request.AbstractRequest;
import ru.forinnyy.tm.dto.response.AbstractResponse;

import java.util.LinkedHashMap;
import java.util.Map;

public class Dispatcher {

    @NonNull
    private final Map<Class<? extends AbstractRequest>, Operation<?, ?>> map = new LinkedHashMap<>();

    public <RQ extends AbstractRequest, RS extends AbstractResponse> void registry(
            @NonNull final Class<RQ> reqClass, @NonNull final Operation<RQ, RS> operation
    ) {
        map.put(reqClass, operation);
    }

    @NonNull
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Object call(@NonNull final AbstractRequest request) {
        final Operation operation = map.get(request.getClass());
        if (operation == null) throw new RuntimeException(request.getClass().getName());
        return operation.execute(request);
    }

}
