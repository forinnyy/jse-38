package ru.forinnyy.tm.endpoint;

import ru.forinnyy.tm.api.service.IServiceLocator;

public class AbstractEndpoint {

    private final IServiceLocator serviceLocator;

    public AbstractEndpoint(IServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    protected IServiceLocator getServiceLocator() {
        return serviceLocator;
    }

}
