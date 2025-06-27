package ru.forinnyy.tm.service;

import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import ru.forinnyy.tm.AbstractTest;
import ru.forinnyy.tm.api.repository.IRepository;
import ru.forinnyy.tm.model.AbstractModel;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class AbstractServiceTest<M extends AbstractModel, R extends IRepository<M>> extends AbstractTest {

    protected AbstractService<M, R> service;
    protected R repository;

    @Before
    public void setUp() {
        super.initData();
        repository = createRepository();
        service = createService();
    }

    protected abstract R createRepository();
    protected abstract AbstractService<M, R> createService();
    protected abstract M createModel();

    @Test
    @SneakyThrows
    public void testAdd() {
        M model = createModel();
        service.add(Arrays.asList(model));
        List<M> allModels = service.findAll();
        assertTrue(allModels.contains(model));
    }

    @Test
    @SneakyThrows
    public void testSet() {
        M model = createModel();
        service.set(Arrays.asList(model));
        List<M> allModels = service.findAll();
        assertTrue(allModels.contains(model));
    }

    @Test
    @SneakyThrows
    public void testFindAll() {
        M model = createModel();
        service.add(Arrays.asList(model));
        List<M> allModels = service.findAll();
        assertFalse(allModels.isEmpty());
    }

    @Test
    @SneakyThrows
    public void testClear() {
        M model = createModel();
        service.add(Arrays.asList(model));
        service.clear();
        List<M> allModels = service.findAll();
        assertTrue(allModels.isEmpty());
    }

    @Test
    @SneakyThrows
    public void testRemove() {
        M model = createModel();
        service.add(Arrays.asList(model));
        service.remove(model);
        List<M> allModels = service.findAll();
        assertFalse(allModels.contains(model));
    }
}
