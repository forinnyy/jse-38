package ru.forinnyy.tm.command.data;

import lombok.NonNull;
import ru.forinnyy.tm.command.AbstractCommand;
import ru.forinnyy.tm.dto.Domain;

public abstract class AbstractDataCommand extends AbstractCommand {

    @NonNull
    public static final String FILE_BASE64 = "./data.base64";

    @NonNull
    public static final String FILE_BINARY = "./data.bin";

    @NonNull
    public static final String FILE_XML = "./data.xml";

    @NonNull
    public static final String FILE_JSON = "./data.json";

    @NonNull
    public static final String FILE_YAML = "./data.yaml";

    @NonNull
    public final String CONTEXT_FACTORY = "javax.xml.bind.context.factory";

    @NonNull
    public final String CONTEXT_FACTORY_JAXB = "org.eclipse.persistence.jaxb.JAXBContextFactory";

    @NonNull
    public final String MEDIA_TYPE = "eclipselink.media-type";

    @NonNull
    public final String APPLICATION_TYPE_JSON = "application/json";

    public AbstractDataCommand() {
    }

    @NonNull
    public Domain getDomain() {
        @NonNull final Domain domain = new Domain();
        domain.setProjects(serviceLocator.getProjectService().findAll());
        domain.setTasks(serviceLocator.getTaskService().findAll());
        domain.setUsers(serviceLocator.getUserService().findAll());
        return domain;
    }

    @NonNull
    public void setDomain(final Domain domain) {
        if (domain == null) return;
        serviceLocator.getProjectService().set(domain.getProjects());
        serviceLocator.getTaskService().set(domain.getTasks());
        serviceLocator.getUserService().set(domain.getUsers());
        serviceLocator.getAuthService().logout();
    }

}
