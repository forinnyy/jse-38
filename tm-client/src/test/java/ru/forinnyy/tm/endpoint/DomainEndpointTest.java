package ru.forinnyy.tm.endpoint;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.junit.*;
import org.junit.experimental.categories.Category;
import ru.forinnyy.tm.api.endpoint.IAuthEndpoint;
import ru.forinnyy.tm.api.endpoint.IDomainEndpoint;
import ru.forinnyy.tm.api.service.IPropertyService;
import ru.forinnyy.tm.dto.request.DataBackupLoadRequest;
import ru.forinnyy.tm.dto.request.DataBinaryLoadRequest;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.dto.request.UserLoginRequest;
import ru.forinnyy.tm.marker.SoapCategory;
import ru.forinnyy.tm.service.PropertyService;

import java.io.File;

@Category(SoapCategory.class)
public final class DomainEndpointTest {

    @NonNull
    private static final IPropertyService propertyService = new PropertyService();

    @NonNull
    private static final IAuthEndpoint authEndpoint = IAuthEndpoint.newInstance();

    @NonNull
    private static final IDomainEndpoint domainEndpoint = IDomainEndpoint.newInstance();

    private static String token;

    @BeforeClass
    public static void init() {
        @NonNull final UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setLogin("admin");
        loginRequest.setPassword("admin");
        token = authEndpoint.login(loginRequest).getToken();
        System.out.println(token);
    }

    @Test
    public void backupSaveAndLoadData() {
        @NonNull final DataBackupSaveRequest saveRequest = new DataBackupSaveRequest(token);
        Assert.assertNotNull(domainEndpoint.saveDataBackup(saveRequest));
        @NonNull final DataBackupLoadRequest loadRequest = new DataBackupLoadRequest(token);
        Assert.assertNotNull(domainEndpoint.loadDataBackup(loadRequest));
    }

    @Test
    public void binarySaveAndLoadData() {
        @NonNull final DataBinarySaveRequest saveRequest = new DataBinarySaveRequest(token);
        Assert.assertNotNull(domainEndpoint.saveDataBinary(saveRequest));
        @NonNull final DataBinaryLoadRequest loadRequest = new DataBinaryLoadRequest(token);
        Assert.assertNotNull(domainEndpoint.loadDataBinary(loadRequest));
    }

    @Test
    public void base64SaveAndLoadData() {
        @NonNull final DataBase64SaveRequest saveRequest = new DataBase64SaveRequest(token);
        Assert.assertNotNull(domainEndpoint.saveDataBase64(saveRequest));
        @NonNull final DataBase64LoadRequest loadRequest = new DataBase64LoadRequest(token);
        Assert.assertNotNull(domainEndpoint.loadDataBase64(loadRequest));
    }

    @Test
    public void jsonFasterXmlSaveAndLoadData() {
        @NonNull final DataJsonSaveFasterXmlRequest saveRequest = new DataJsonSaveFasterXmlRequest(token);
        Assert.assertNotNull(domainEndpoint.saveDataJsonFasterXml(saveRequest));
        @NonNull final DataJsonLoadFasterXmlRequest loadRequest = new DataJsonLoadFasterXmlRequest(token);
        Assert.assertNotNull(domainEndpoint.loadDataJsonFasterXml(loadRequest));
    }

    @Test
    public void xmlFasterXmlSaveAndLoadData() {
        @NonNull final DataXmlSaveFasterXmlRequest saveRequest = new DataXmlSaveFasterXmlRequest(token);
        Assert.assertNotNull(domainEndpoint.saveDataXmlFasterXml(saveRequest));
        @NonNull final DataXmlLoadFasterXmlRequest loadRequest = new DataXmlLoadFasterXmlRequest(token);
        Assert.assertNotNull(domainEndpoint.loadDataXmlFasterXml(loadRequest));
    }

    @Test
    public void jsonJaxBSaveAndLoadData() {
        @NonNull final DataJsonSaveJaxBRequest saveRequest = new DataJsonSaveJaxBRequest(token);
        Assert.assertNotNull(domainEndpoint.saveDataJsonJaxB(saveRequest));
        @NonNull final DataJsonLoadJaxBRequest loadRequest = new DataJsonLoadJaxBRequest(token);
        Assert.assertNotNull(domainEndpoint.loadDataJsonJaxB(loadRequest));
    }

    @Test
    public void xmlJaxBSaveAndLoadData() {
        @NonNull final DataXmlSaveJaxBRequest saveRequest = new DataXmlSaveJaxBRequest(token);
        Assert.assertNotNull(domainEndpoint.saveDataXmlJaxB(saveRequest));
        @NonNull final DataXmlLoadJaxBRequest loadRequest = new DataXmlLoadJaxBRequest(token);
        Assert.assertNotNull(domainEndpoint.loadDataXmlJaxB(loadRequest));
    }

    @Test
    public void yamlFasterXmlSaveAndLoadData() {
        @NonNull final DataYamlSaveFasterXmlRequest saveRequest = new DataYamlSaveFasterXmlRequest(token);
        Assert.assertNotNull(domainEndpoint.saveDataYamlFasterXml(saveRequest));
        @NonNull final DataYamlLoadFasterXmlRequest loadRequest = new DataYamlLoadFasterXmlRequest(token);
        Assert.assertNotNull(domainEndpoint.loadDataYamlFasterXml(loadRequest));
    }

}
