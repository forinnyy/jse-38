package ru.forinnyy.tm.api.endpoint;

import ru.forinnyy.tm.dto.request.AbstractRequest;
import ru.forinnyy.tm.dto.response.AbstractResponse;

@FunctionalInterface
public interface Operation<RQ extends AbstractRequest, RS extends AbstractResponse> {

    RS execute(RQ request);

}
