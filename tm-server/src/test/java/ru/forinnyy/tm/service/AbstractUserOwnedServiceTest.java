package ru.forinnyy.tm.service;

import lombok.SneakyThrows;
import org.junit.Test;
import ru.forinnyy.tm.api.repository.IUserOwnedRepository;
import ru.forinnyy.tm.model.AbstractUserOwnedModel;

import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractUserOwnedServiceTest<M extends AbstractUserOwnedModel, R extends IUserOwnedRepository<M>>
        extends AbstractServiceTest<M, R> {

    protected AbstractUserOwnedService<M, R> getUserOwnedService() {
        return (AbstractUserOwnedService<M, R>) service;
    }

    @Test
    @SneakyThrows
    public void testAddWithUser() {
        M model = createModel();
        M result = getUserOwnedService().add(testUser.getId(), model);
        assertNotNull(result);
    }

    @Test
    @SneakyThrows
    public void testFindAllWithUser() {
        M model = createModel();
        getUserOwnedService().add(testUser.getId(), model);
        List<M> models = getUserOwnedService().findAll(testUser.getId());
        assertFalse(models.isEmpty());
    }

    @Test
    @SneakyThrows
    public void testRemoveByUser() {
        M model = createModel();
        getUserOwnedService().add(testUser.getId(), model);
        getUserOwnedService().remove(testUser.getId(), model);
        List<M> models = getUserOwnedService().findAll(testUser.getId());
        assertTrue(models.isEmpty());
    }

    @Test
    @SneakyThrows
    public void testFindById() {
        M model = createModel();
        M result = getUserOwnedService().add(testUser.getId(), model);
        M foundModel = getUserOwnedService().findOneById(testUser.getId(), result.getId());
        assertNotNull(foundModel);
        assertEquals(result.getId(), foundModel.getId());
    }

    @Test
    @SneakyThrows
    public void testRemoveOneByUser() {
        M model = createModel();
        getUserOwnedService().add(testUser.getId(), model);
        getUserOwnedService().removeOne(model);

        List<M> models = getUserOwnedService().findAll(testUser.getId());
        assertTrue(models.isEmpty());
    }

}
