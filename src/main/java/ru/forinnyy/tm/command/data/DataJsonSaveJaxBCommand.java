package ru.forinnyy.tm.command.data;

import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.Domain;
import ru.forinnyy.tm.enumerated.Role;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

public final class DataJsonSaveJaxBCommand extends AbstractDataCommand {

    @Override
    public String getArgument() {
        return null;
    }

    @NonNull
    @Override
    public String getDescription() {
        return "Save data in json file.";
    }

    @NonNull
    @Override
    public String getName() {
        return "data-save-json-jaxb";
    }

    @NonNull
    @Override
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

    @Override
    @SneakyThrows
    public void execute() {
        System.out.println("[DATA SAVE JSON]");
        System.setProperty(CONTEXT_FACTORY, CONTEXT_FACTORY_JAXB);
        @NonNull final Domain domain = getDomain();
        @NonNull final File file = new File(FILE_JSON);
        Files.deleteIfExists(file.toPath());
        Files.createFile(file.toPath());
        @NonNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NonNull final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.setProperty(MEDIA_TYPE, APPLICATION_TYPE_JSON);
        @Cleanup @NonNull final FileOutputStream fileOutputStream = new FileOutputStream(file);
        jaxbMarshaller.marshal(domain, fileOutputStream);
        fileOutputStream.flush();
    }

}
