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
        M result = getUserOwnedService().add(UUID1, model);
        assertNotNull(result);
    }

    @Test
    @SneakyThrows
    public void testFindAllWithUser() {
        M model = createModel();
        getUserOwnedService().add(UUID1, model);
        List<M> models = getUserOwnedService().findAll(UUID1);
        assertFalse(models.isEmpty());
    }

    @Test
    @SneakyThrows
    public void testRemoveByUser() {
        M model = createModel();
        getUserOwnedService().add(UUID1, model);
        getUserOwnedService().remove(UUID1, model);
        List<M> models = getUserOwnedService().findAll(UUID1);
        assertTrue(models.isEmpty());
    }

    @Test
    @SneakyThrows
    public void testFindById() {
        M model = createModel();
        M result = getUserOwnedService().add(UUID1, model);
        M foundModel = getUserOwnedService().findOneById(UUID1, result.getId());
        assertNotNull(foundModel);
        assertEquals(result.getId(), foundModel.getId());
    }

    @Test
    @SneakyThrows
    public void testRemoveOneByUser() {
        M model = createModel();
        getUserOwnedService().add(UUID1, model);
        getUserOwnedService().removeOne(model);

        List<M> models = getUserOwnedService().findAll(UUID1);
        assertTrue(models.isEmpty());
    }

}
