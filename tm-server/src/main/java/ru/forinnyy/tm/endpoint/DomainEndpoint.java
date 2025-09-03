package ru.forinnyy.tm.endpoint;

import lombok.NonNull;
import ru.forinnyy.tm.api.endpoint.IDomainEndpoint;
import ru.forinnyy.tm.api.service.IServiceLocator;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;
import ru.forinnyy.tm.enumerated.Role;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "ru.forinnyy.tm.api.endpoint.IDomainEndpoint")
public final class DomainEndpoint extends AbstractEndpoint implements IDomainEndpoint {

    public DomainEndpoint(@NonNull final IServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @NonNull
    @Override
    @WebMethod
    public DataBackupLoadResponse loadDataBackup(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final DataBackupLoadRequest request
    ) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataBackup();
        return new DataBackupLoadResponse();
    }

    @NonNull
    @Override
    @WebMethod
    public DataBackupSaveResponse saveDataBackup(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final DataBackupSaveRequest request
    ) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataBackup();
        return new DataBackupSaveResponse();
    }

    @NonNull
    @Override
    @WebMethod
    public DataBase64LoadResponse loadDataBase64(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final DataBase64LoadRequest request) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataBase64();
        return new DataBase64LoadResponse();
    }

    @NonNull
    @Override
    @WebMethod
    public DataBase64SaveResponse saveDataBase64(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final DataBase64SaveRequest request
    ) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataBase64();
        return new DataBase64SaveResponse();
    }

    @NonNull
    @Override
    public DataBinaryLoadResponse loadDataBinary(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final DataBinaryLoadRequest request
    ) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataBinary();
        return new DataBinaryLoadResponse();
    }

    @NonNull
    @Override
    @WebMethod
    public DataBinarySaveResponse saveDataBinary(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final DataBinarySaveRequest request
    ) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataBinary();
        return new DataBinarySaveResponse();
    }

    @NonNull
    @Override
    @WebMethod
    public DataJsonLoadFasterXmlResponse loadDataJsonFasterXml(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final DataJsonLoadFasterXmlRequest request
    ) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataJsonFasterXml();
        return new DataJsonLoadFasterXmlResponse();
    }

    @NonNull
    @Override
    @WebMethod
    public DataJsonSaveFasterXmlResponse saveDataJsonFasterXml(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final DataJsonSaveFasterXmlRequest request
    ) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataJsonFasterXml();
        return new DataJsonSaveFasterXmlResponse();
    }

    @NonNull
    @Override
    @WebMethod
    public DataJsonLoadJaxBResponse loadDataJsonJaxB(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final DataJsonLoadJaxBRequest request
    ) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataJsonJaxB();
        return new DataJsonLoadJaxBResponse();
    }

    @NonNull
    @Override
    @WebMethod
    public DataJsonSaveJaxBResponse saveDataJsonJaxB(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final DataJsonSaveJaxBRequest request
    ) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataJsonJaxB();
        return new DataJsonSaveJaxBResponse();
    }

    @NonNull
    @Override
    @WebMethod
    public DataXmlLoadFasterXmlResponse loadDataXmlFasterXml(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final DataXmlLoadFasterXmlRequest request
    ) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataXmlFasterXml();
        return new DataXmlLoadFasterXmlResponse();
    }

    @NonNull
    @Override
    @WebMethod
    public DataXmlSaveFasterXmlResponse saveDataXmlFasterXml(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final DataXmlSaveFasterXmlRequest request
    ) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataXmlFasterXml();
        return new DataXmlSaveFasterXmlResponse();
    }

    @NonNull
    @Override
    @WebMethod
    public DataXmlLoadJaxBResponse loadDataXmlJaxB(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final DataXmlLoadJaxBRequest request
    ) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataXmlJaxB();
        return new DataXmlLoadJaxBResponse();
    }

    @NonNull
    @Override
    @WebMethod
    public DataXmlSaveJaxBResponse saveDataXmlJaxB(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final DataXmlSaveJaxBRequest request
    ) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataXmlJaxB();
        return new DataXmlSaveJaxBResponse();
    }

    @NonNull
    @Override
    @WebMethod
    public DataYamlLoadFasterXmlResponse loadDataYamlFasterXml(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final DataYamlLoadFasterXmlRequest request
    ) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().loadDataYamlFasterXml();
        return new DataYamlLoadFasterXmlResponse();
    }

    @NonNull
    @Override
    @WebMethod
    public DataYamlSaveFasterXmlResponse saveDataYamlFasterXml(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull final DataYamlSaveFasterXmlRequest request
    ) {
        check(request, Role.ADMIN);
        getServiceLocator().getDomainService().saveDataYamlFasterXml();
        return new DataYamlSaveFasterXmlResponse();
    }

    @NonNull
    @Override
    @WebMethod
    public DataFilesDeleteAfterTestsResponse deleteFilesAfterTests(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull DataFilesDeleteAfterTestsRequest request) {
        getServiceLocator().getDomainService().deleteFilesAfterTests();
        return new DataFilesDeleteAfterTestsResponse();
    }

}
