package ru.forinnyy.tm.endpoint;

import lombok.NonNull;
import ru.forinnyy.tm.api.endpoint.IDomainEndpoint;
import ru.forinnyy.tm.api.service.IServiceLocator;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;
import ru.forinnyy.tm.enumerated.Role;

public final class DomainEndpoint extends AbstractEndpoint implements IDomainEndpoint {

    public DomainEndpoint(@NonNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @NonNull
    @Override
    public DataBackupLoadResponse loadDataBackup(@NonNull final DataBackupLoadRequest request) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataBackup();
        return new DataBackupLoadResponse();
    }

    @NonNull
    @Override
    public DataBackupSaveResponse saveDataBackup(@NonNull final DataBackupSaveRequest request) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataBackup();
        return new DataBackupSaveResponse();
    }

    @NonNull
    @Override
    public DataBase64LoadResponse loadDataBase64(@NonNull final DataBase64LoadRequest request) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataBase64();
        return new DataBase64LoadResponse();
    }

    @NonNull
    @Override
    public DataBase64SaveResponse saveDataBase64(@NonNull final DataBase64SaveRequest request) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataBase64();
        return new DataBase64SaveResponse();
    }

    @NonNull
    @Override
    public DataBinaryLoadResponse loadDataBinary(@NonNull final DataBinaryLoadRequest request) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataBinary();
        return new DataBinaryLoadResponse();
    }

    @NonNull
    @Override
    public DataBinarySaveResponse saveDataBinary(@NonNull final DataBinarySaveRequest request) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataBinary();
        return new DataBinarySaveResponse();
    }

    @NonNull
    @Override
    public DataJsonLoadFasterXmlResponse loadDataJsonFasterXml(@NonNull final DataJsonLoadFasterXmlRequest request) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataJsonFasterXml();
        return new DataJsonLoadFasterXmlResponse();
    }

    @NonNull
    @Override
    public DataJsonSaveFasterXmlResponse saveDataJsonFasterXml(@NonNull final DataJsonSaveFasterXmlRequest request) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataJsonFasterXml();
        return new DataJsonSaveFasterXmlResponse();
    }

    @NonNull
    @Override
    public DataJsonLoadJaxBResponse loadDataJsonJaxB(@NonNull final DataJsonLoadJaxBRequest request) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataJsonJaxB();
        return new DataJsonLoadJaxBResponse();
    }

    @NonNull
    @Override
    public DataJsonSaveJaxBResponse saveDataJsonJaxB(@NonNull final DataJsonSaveJaxBRequest request) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataJsonJaxB();
        return new DataJsonSaveJaxBResponse();
    }

    @NonNull
    @Override
    public DataXmlLoadFasterXmlResponse loadDataXmlFasterXml(@NonNull final DataXmlLoadFasterXmlRequest request) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataXmlFasterXml();
        return new DataXmlLoadFasterXmlResponse();
    }

    @NonNull
    @Override
    public DataXmlSaveFasterXmlResponse saveDataXmlFasterXml(@NonNull final DataXmlSaveFasterXmlRequest request) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataXmlFasterXml();
        return new DataXmlSaveFasterXmlResponse();
    }

    @NonNull
    @Override
    public DataXmlLoadJaxBResponse loadDataXmlJaxB(@NonNull final DataXmlLoadJaxBRequest request) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataXmlJaxB();
        return new DataXmlLoadJaxBResponse();
    }

    @NonNull
    @Override
    public DataXmlSaveJaxBResponse saveDataXmlJaxB(@NonNull final DataXmlSaveJaxBRequest request) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataXmlJaxB();
        return new DataXmlSaveJaxBResponse();
    }

    @NonNull
    @Override
    public DataYamlLoadFasterXmlResponse loadDataYamlFasterXml(@NonNull final DataYamlLoadFasterXmlRequest request) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataYamlFasterXml();
        return new DataYamlLoadFasterXmlResponse();
    }

    @NonNull
    @Override
    public DataYamlSaveFasterXmlResponse saveDataYamlFasterXml(@NonNull final DataYamlSaveFasterXmlRequest request) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataYamlFasterXml();
        return new DataYamlSaveFasterXmlResponse();
    }

}
