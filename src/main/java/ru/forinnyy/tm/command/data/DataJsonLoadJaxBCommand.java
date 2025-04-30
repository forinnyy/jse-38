package ru.forinnyy.tm.command.data;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.Domain;
import ru.forinnyy.tm.enumerated.Role;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public final class DataJsonLoadJaxBCommand extends AbstractDataCommand {

    @Override
    public String getArgument() {
        return null;
    }

    @NonNull
    @Override
    public String getDescription() {
        return "Load data from json file.";
    }

    @NonNull
    @Override
    public String getName() {
        return "data-load-json-jaxb";
    }

    @NonNull
    @Override
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

    @Override
    @SneakyThrows
    public void execute() {
        System.out.println("[DATA LOAD JSON]");
        System.setProperty(CONTEXT_FACTORY, CONTEXT_FACTORY_JAXB);
        @NonNull JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NonNull final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setProperty(MEDIA_TYPE, APPLICATION_TYPE_JSON);
        @NonNull final File file = new File(FILE_JSON);
        @NonNull final Domain domain = (Domain) unmarshaller.unmarshal(file);
        setDomain(domain);
    }

}
