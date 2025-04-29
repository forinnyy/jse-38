package ru.forinnyy.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.Domain;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.user.AbstractUserException;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class DataXmlLoadFasterXmlCommand extends AbstractDataCommand {

    @Override
    public String getArgument() {
        return null;
    }

    @Override
    public @NonNull String getDescription() {
        return "Load data from xml file.";
    }

    @Override
    public @NonNull String getName() {
        return "data-load-xml";
    }

    @Override
    public Role[] getRoles() {
        return new Role[]{Role.ADMIN};
    }

    @SneakyThrows
    @Override
    public void execute() {
        System.out.println("[DATA LOAD XML]");
        @NonNull final byte[] bytes = Files.readAllBytes(Paths.get(FILE_XML));
        @NonNull final String xml = new String(bytes);
        @NonNull final ObjectMapper objectMapper = new XmlMapper();
        @NonNull final Domain domain = objectMapper.readValue(xml, Domain.class);
        setDomain(domain);
    }

}
