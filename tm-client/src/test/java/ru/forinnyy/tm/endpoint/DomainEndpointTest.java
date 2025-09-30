package ru.forinnyy.tm.endpoint;

import lombok.NonNull;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.forinnyy.tm.api.endpoint.IAuthEndpoint;
import ru.forinnyy.tm.api.endpoint.IDomainEndpoint;
import ru.forinnyy.tm.api.service.IPropertyService;
import ru.forinnyy.tm.dto.request.*;
import ru.forinnyy.tm.marker.SoapCategory;
import ru.forinnyy.tm.service.PropertyService;


@Category(SoapCategory.class)
public final class DomainEndpointTest {

    @NonNull
    private static final IPropertyService propertyService = new PropertyService();

    @NonNull
    private static final IAuthEndpoint authEndpoint = IAuthEndpoint.newInstance(propertyService);

    @NonNull
    private static final IDomainEndpoint domainEndpoint = IDomainEndpoint.newInstance(propertyService);

    private static String adminToken;

    @Before
    public void init() {
        @NonNull final UserLoginRequest loginRequest = new UserLoginRequest();
        loginRequest.setLogin("admin");
        loginRequest.setPassword("admin");
        adminToken = authEndpoint.login(loginRequest).getToken();
    }

    @Test
    public void backupSaveAndLoadData() {
        @NonNull final DataBackupSaveRequest saveRequest = new DataBackupSaveRequest(adminToken);
        Assert.assertNotNull(domainEndpoint.saveDataBackup(saveRequest));
        @NonNull final DataBackupLoadRequest loadRequest = new DataBackupLoadRequest(adminToken);
        Assert.assertNotNull(domainEndpoint.loadDataBackup(loadRequest));
    }

    @Test
    public void binarySaveAndLoadData() {
        @NonNull final DataBinarySaveRequest saveRequest = new DataBinarySaveRequest(adminToken);
        Assert.assertNotNull(domainEndpoint.saveDataBinary(saveRequest));
        @NonNull final DataBinaryLoadRequest loadRequest = new DataBinaryLoadRequest(adminToken);
        Assert.assertNotNull(domainEndpoint.loadDataBinary(loadRequest));
    }

    @Test
    public void base64SaveAndLoadData() {
        @NonNull final DataBase64SaveRequest saveRequest = new DataBase64SaveRequest(adminToken);
        Assert.assertNotNull(domainEndpoint.saveDataBase64(saveRequest));
        @NonNull final DataBase64LoadRequest loadRequest = new DataBase64LoadRequest(adminToken);
        Assert.assertNotNull(domainEndpoint.loadDataBase64(loadRequest));
    }

    @Test
    public void jsonFasterXmlSaveAndLoadData() {
        @NonNull final DataJsonSaveFasterXmlRequest saveRequest = new DataJsonSaveFasterXmlRequest(adminToken);
        Assert.assertNotNull(domainEndpoint.saveDataJsonFasterXml(saveRequest));
        @NonNull final DataJsonLoadFasterXmlRequest loadRequest = new DataJsonLoadFasterXmlRequest(adminToken);
        Assert.assertNotNull(domainEndpoint.loadDataJsonFasterXml(loadRequest));
    }

    @Test
    public void xmlFasterXmlSaveAndLoadData() {
        @NonNull final DataXmlSaveFasterXmlRequest saveRequest = new DataXmlSaveFasterXmlRequest(adminToken);
        Assert.assertNotNull(domainEndpoint.saveDataXmlFasterXml(saveRequest));
        @NonNull final DataXmlLoadFasterXmlRequest loadRequest = new DataXmlLoadFasterXmlRequest(adminToken);
        Assert.assertNotNull(domainEndpoint.loadDataXmlFasterXml(loadRequest));
    }

    @Test
    public void jsonJaxBSaveAndLoadData() {
        @NonNull final DataJsonSaveJaxBRequest saveRequest = new DataJsonSaveJaxBRequest(adminToken);
        Assert.assertNotNull(domainEndpoint.saveDataJsonJaxB(saveRequest));
        @NonNull final DataJsonLoadJaxBRequest loadRequest = new DataJsonLoadJaxBRequest(adminToken);
        Assert.assertNotNull(domainEndpoint.loadDataJsonJaxB(loadRequest));
    }

    @Test
    public void xmlJaxBSaveAndLoadData() {
        @NonNull final DataXmlSaveJaxBRequest saveRequest = new DataXmlSaveJaxBRequest(adminToken);
        Assert.assertNotNull(domainEndpoint.saveDataXmlJaxB(saveRequest));
        @NonNull final DataXmlLoadJaxBRequest loadRequest = new DataXmlLoadJaxBRequest(adminToken);
        Assert.assertNotNull(domainEndpoint.loadDataXmlJaxB(loadRequest));
    }

    @Test
    public void yamlFasterXmlSaveAndLoadData() {
        @NonNull final DataYamlSaveFasterXmlRequest saveRequest = new DataYamlSaveFasterXmlRequest(adminToken);
        Assert.assertNotNull(domainEndpoint.saveDataYamlFasterXml(saveRequest));
        @NonNull final DataYamlLoadFasterXmlRequest loadRequest = new DataYamlLoadFasterXmlRequest(adminToken);
        Assert.assertNotNull(domainEndpoint.loadDataYamlFasterXml(loadRequest));
    }

    @AfterClass
    public static void clearFiles() {
        @NonNull final DataFilesDeleteAfterTestsRequest request = new DataFilesDeleteAfterTestsRequest();
        domainEndpoint.deleteFilesAfterTests(request);
    }

}
