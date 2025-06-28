package ru.forinnyy.tm.service;

import lombok.NonNull;
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

    protected abstract R createRepository();
    protected abstract AbstractService<M, R> createService();
    protected abstract M createModel();

    @Before
    public void init() {
        repository = createRepository();
        service = createService();
    }

    @Test
    @SneakyThrows
    public void testAdd() {
        @NonNull final M model = createModel();
        service.add(Arrays.asList(model));
        List<M> allModels = service.findAll();
        assertTrue(allModels.contains(model));
    }

    @Test
    @SneakyThrows
    public void testSet() {
        @NonNull final M model = createModel();
        service.set(Arrays.asList(model));
        @NonNull final List<M> allModels = service.findAll();
        assertTrue(allModels.contains(model));
    }

    @Test
    @SneakyThrows
    public void testFindAll() {
        @NonNull final M model = createModel();
        service.add(Arrays.asList(model));
        @NonNull final List<M> allModels = service.findAll();
        assertFalse(allModels.isEmpty());
    }

    @Test
    @SneakyThrows
    public void testClear() {
        @NonNull final M model = createModel();
        service.add(Arrays.asList(model));
        service.clear();
        @NonNull final List<M> allModels = service.findAll();
        assertTrue(allModels.isEmpty());
    }

    @Test
    @SneakyThrows
    public void testRemove() {
        @NonNull final M model = createModel();
        service.add(Arrays.asList(model));
        service.remove(model);
        @NonNull final List<M> allModels = service.findAll();
        assertFalse(allModels.contains(model));
    }
}
