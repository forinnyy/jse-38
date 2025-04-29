package ru.forinnyy.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.Domain;
import ru.forinnyy.tm.enumerated.Role;

import java.nio.file.Files;
import java.nio.file.Paths;

public final class DataXmlLoadFasterXmlCommand extends AbstractDataCommand {

    @Override
    public String getArgument() {
        return null;
    }

    @NonNull
    @Override
    public String getDescription() {
        return "Load data from xml file.";
    }

    @NonNull
    @Override
    public String getName() {
        return "data-load-xml";
    }

    @NonNull
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
