package ru.forinnyy.tm.service;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.forinnyy.tm.AbstractTest;
import ru.forinnyy.tm.api.repository.IRepository;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.IdEmptyException;
import ru.forinnyy.tm.exception.field.IndexIncorrectException;
import ru.forinnyy.tm.model.AbstractModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractServiceTest<M extends AbstractModel, R extends IRepository<M>>
        extends AbstractTest {

    protected R repository;
    protected AbstractService<M, R> service;
    protected abstract R createRepository();
    protected abstract AbstractService<M, R> createService();
    protected abstract M createModel();

    @Before
    public void init() {
        repository = createRepository();
        service = createService();
        service.clear();
    }

    @Test
    public void testAddAndFindAll() throws AbstractEntityException {
        final M model = createModel();
        service.add(model);
        Assert.assertEquals(1, service.getSize());
        Assert.assertTrue(service.findAll().contains(model));
    }

    @Test
    public void testAddCollection() {
        final List<M> models = Arrays.asList(createModel(), createModel());
        service.add(models);
        Assert.assertEquals(2, service.getSize());
        Assert.assertTrue(service.findAll().containsAll(models));
    }

    @Test
    public void testSetCollection() {
        final List<M> oldModels = Arrays.asList(createModel(), createModel());
        service.add(oldModels);
        final List<M> newModels = Arrays.asList(createModel(), createModel());
        service.set(newModels);
        Assert.assertEquals(2, service.getSize());
        Assert.assertFalse(service.findAll().containsAll(oldModels));
        Assert.assertTrue(service.findAll().containsAll(newModels));
    }

    @Test
    public void testClear() {
        service.add(Arrays.asList(createModel(), createModel()));
        service.clear();
        Assert.assertEquals(0, service.getSize());
    }

    @Test
    public void testExistsById() throws AbstractEntityException {
        final M model = createModel();
        model.setId(UUID1);
        service.add(model);
        Assert.assertTrue(service.existsById(UUID1));
        Assert.assertFalse(service.existsById(UUID2));
        Assert.assertFalse(service.existsById(null));
        Assert.assertFalse(service.existsById(""));
    }

    @Test
    @SneakyThrows
    public void testFindOneById() {
        final M model = createModel();
        model.setId(UUID1);
        service.add(model);
        final M result = service.findOneById(UUID1);
        Assert.assertEquals(model, result);
        Assert.assertThrows(IdEmptyException.class, () -> service.findOneById(null));
        Assert.assertThrows(IdEmptyException.class, () -> service.findOneById(""));
    }

    @Test
    @SneakyThrows
    public void testFindOneByIndex() {
        final M model = createModel();
        service.add(model);
        final M result = service.findOneByIndex(0);
        Assert.assertEquals(model, result);
        Assert.assertThrows(IndexIncorrectException.class, () -> service.findOneByIndex(null));
    }

    @Test
    @SneakyThrows
    public void testRemoveById() {
        final M model = createModel();
        service.add(model);
        service.removeById(model.getId());
        Assert.assertEquals(0, service.getSize());
        Assert.assertThrows(IdEmptyException.class, () -> service.removeById(null));
        Assert.assertThrows(IdEmptyException.class, () -> service.removeById(""));
    }

    @Test
    @SneakyThrows
    public void testRemoveByIndex() {
        final M model = createModel();
        service.add(model);
        service.removeByIndex(0);
        Assert.assertEquals(0, service.getSize());
        Assert.assertThrows(IndexIncorrectException.class, () -> service.removeByIndex(null));
    }

    @Test
    @SneakyThrows
    public void testRemoveOne() {
        final M model = createModel();
        service.add(model);
        service.removeOne(model);
        Assert.assertEquals(0, service.getSize());
        Assert.assertThrows(AbstractEntityException.class, () -> service.remove(null));
    }

    @Test
    public void testRemoveAll() {
        final List<M> models = Arrays.asList(createModel(), createModel());
        service.add(models);
        service.removeAll(models);
        Assert.assertEquals(0, service.getSize());
        service.removeAll(null);
        service.removeAll(Collections.emptyList());
        Assert.assertEquals(0, service.getSize());
    }

}