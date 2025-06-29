package ru.forinnyy.tm.service;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.forinnyy.tm.AbstractTest;
import ru.forinnyy.tm.api.repository.IRepository;
import ru.forinnyy.tm.exception.entity.EntityNotFoundException;
import ru.forinnyy.tm.exception.field.IdEmptyException;
import ru.forinnyy.tm.exception.field.IndexIncorrectException;
import ru.forinnyy.tm.model.AbstractModel;

import java.util.*;


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
        @NonNull final M model_one = createModel();
        service.add(model_one);
        Assert.assertTrue(service.findAll().contains(model_one));
        Assert.assertThrows(NullPointerException.class, () -> service.add((M) null));
        @NonNull final M model_two = createModel();
        @NonNull final M model_three = createModel();
        @NonNull final Collection<M> collection = Arrays.asList(model_two, model_three);
        service.add(collection);
        Assert.assertTrue(service.findAll().containsAll(Arrays.asList(model_one, model_two, model_three)));
        Assert.assertThrows(NullPointerException.class, () -> service.add((Collection<M>) null));
    }

    @Test
    @SneakyThrows
    public void testSet() {
        @NonNull final M model = createModel();
        service.set(Arrays.asList(model));
        Assert.assertTrue(service.findAll().contains(model));
        Assert.assertThrows(NullPointerException.class, () -> service.set(null));
    }

    @Test
    @SneakyThrows
    public void testFindAll() {
        service.add(Arrays.asList(createModel()));
        @NonNull final List<M> models = service.findAll();
        Assert.assertTrue(models.containsAll(service.findAll()));
    }

    @Test
    @SneakyThrows
    public void testClear() {
        service.add(createModel());
        service.clear();
        Assert.assertTrue(service.findAll().isEmpty());
    }

    @Test
    @SneakyThrows
    public void testRemove() {
        @NonNull final M model = createModel();
        service.add(Arrays.asList(model));
        service.remove(model);
        @NonNull final List<M> models = service.findAll();
        Assert.assertFalse(models.contains(model));

        Assert.assertThrows(EntityNotFoundException.class,
                () -> service.remove(null));
    }

    @Test
    public void testRemoveAll() {
        @NonNull final List<M> models = Arrays.asList(createModel(), createModel());
        service.set(models);
        Assert.assertEquals(2, service.getSize());
        service.removeAll(models);
        service.removeAll(null);
        @NonNull final List<M> emptyList = new ArrayList<>();
        service.removeAll(emptyList);
        Assert.assertEquals(0, service.getSize());
    }

    @Test
    @SneakyThrows
    public void testFindOneById() {
        @NonNull final M model = createModel();
        service.add(model);
        Assert.assertEquals(model, service.findOneById(model.getId()));

        Assert.assertThrows(IdEmptyException.class, () -> service.findOneById(null));
        Assert.assertThrows(IdEmptyException.class, () -> service.findOneById(""));
    }

    @Test
    @SneakyThrows
    public void testFindByIndex() {
        @NonNull final M model = createModel();
        service.add(model);
        Assert.assertEquals(model, service.findOneByIndex(0));

        Assert.assertThrows(IndexIncorrectException.class, () -> service.findOneByIndex(null));
    }

    @Test
    @SneakyThrows
    public void testRemoveById() {
        @NonNull final M model = createModel();
        service.add(model);
        Assert.assertTrue(service.findAll().contains(model));
        service.removeById(model.getId());
        Assert.assertFalse(service.findAll().contains(model));

        Assert.assertThrows(IdEmptyException.class, () -> service.removeById(null));
        Assert.assertThrows(IdEmptyException.class, () -> service.removeById(""));
    }

    @Test
    @SneakyThrows
    public void testRemoveByIndex() {
        @NonNull final M model = createModel();
        service.add(model);
        Assert.assertTrue(service.findAll().contains(model));
        service.removeByIndex(0);
        Assert.assertFalse(service.findAll().contains(model));

        Assert.assertThrows(IndexIncorrectException.class, () -> service.removeByIndex(null));
    }

    @Test
    @SneakyThrows
    public void testExistsById() {
        @NonNull final M model = createModel();
        service.add(model);
        Assert.assertTrue(service.existsById(model.getId()));
        Assert.assertFalse(service.existsById(null));
        Assert.assertFalse(service.existsById(EMPTY_STRING));
    }

}
