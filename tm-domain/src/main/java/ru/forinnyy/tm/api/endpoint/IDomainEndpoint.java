package ru.forinnyy.tm.api.endpoint;

import lombok.NonNull;
import lombok.SneakyThrows;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.response.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IDomainEndpoint extends IEndpoint {

    @NonNull
    String NAME = "DomainEndpoint";

    @NonNull
    String PART = NAME + "Service";

    @SneakyThrows
    @WebMethod(exclude = true)
    static IDomainEndpoint newInstance() {
        return newInstance(HOST, PORT);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static IDomainEndpoint newInstance(@NonNull final IConnectionProvider connectionProvider) {
        return IEndpoint.newInstance(connectionProvider, NAME, SPACE, PART, IDomainEndpoint.class);
    }

    @SneakyThrows
    @WebMethod(exclude = true)
    static IDomainEndpoint newInstance(@NonNull final String host, @NonNull final String port) {
        return IEndpoint.newInstanse(host, port, NAME, SPACE, PART, IDomainEndpoint.class);
    }

    @NonNull
    @WebMethod
    DataBackupLoadResponse loadDataBackup(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull DataBackupLoadRequest request
    );

    @NonNull
    @WebMethod
    DataBackupSaveResponse saveDataBackup(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull DataBackupSaveRequest request
    );

    @NonNull
    @WebMethod
    DataBase64LoadResponse loadDataBase64(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull DataBase64LoadRequest request
    );

    @NonNull
    @WebMethod
    DataBase64SaveResponse saveDataBase64(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull DataBase64SaveRequest request
    );

    @NonNull
    @WebMethod
    DataBinaryLoadResponse loadDataBinary(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull DataBinaryLoadRequest request
    );

    @NonNull
    @WebMethod
    DataBinarySaveResponse saveDataBinary(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull DataBinarySaveRequest request)
            ;

    @NonNull
    @WebMethod
    DataJsonLoadFasterXmlResponse loadDataJsonFasterXml(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull DataJsonLoadFasterXmlRequest request
    );

    @NonNull
    @WebMethod
    DataJsonSaveFasterXmlResponse saveDataJsonFasterXml(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull DataJsonSaveFasterXmlRequest request
    );

    @NonNull
    @WebMethod
    DataJsonLoadJaxBResponse loadDataJsonJaxB(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull DataJsonLoadJaxBRequest request
    );

    @NonNull
    @WebMethod
    DataJsonSaveJaxBResponse saveDataJsonJaxB(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull DataJsonSaveJaxBRequest request
    );

    @NonNull
    @WebMethod
    DataXmlLoadFasterXmlResponse loadDataXmlFasterXml(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull DataXmlLoadFasterXmlRequest request
    );

    @NonNull
    @WebMethod
    DataXmlSaveFasterXmlResponse saveDataXmlFasterXml(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull DataXmlSaveFasterXmlRequest request
    );

    @NonNull
    @WebMethod
    DataXmlLoadJaxBResponse loadDataXmlJaxB(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull DataXmlLoadJaxBRequest request
    );

    @NonNull
    @WebMethod
    DataXmlSaveJaxBResponse saveDataXmlJaxB(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull DataXmlSaveJaxBRequest request
    );

    @NonNull
    @WebMethod
    DataYamlLoadFasterXmlResponse loadDataYamlFasterXml(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull DataYamlLoadFasterXmlRequest request
    );

    @NonNull
    @WebMethod
    DataYamlSaveFasterXmlResponse saveDataYamlFasterXml(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull DataYamlSaveFasterXmlRequest request
    );

    @NonNull
    @WebMethod
    DataFilesDeleteAfterTestsResponse deleteFilesAfterTests(
            @WebParam(name = REQUEST, partName = REQUEST)
            @NonNull DataFilesDeleteAfterTestsRequest request
    );

}
