package ru.forinnyy.tm.repository;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.forinnyy.tm.model.AbstractModel;

import java.util.*;


public abstract class AbstractRepositoryTest<M extends AbstractModel> extends AbstractTest {

    protected AbstractRepository<M> repository;

    protected abstract AbstractRepository<M> createRepository();
    protected abstract M createModel();

    @Before
    public void init() {
        repository = createRepository();
        repository.clear();
    }

    @Test
    public void testRemoveAll() {
        List<M> models = new ArrayList<>();
        models.add(createModel());
        models.add(createModel());
        repository.add(models);
        repository.removeAll(models);
        repository.removeAll(null);
        Assert.assertEquals(Collections.emptyList(), repository.findAll());
    }

    @Test
    public void testExistsById() {
        final M model = createModel();
        model.setId(UUID1);
        repository.add(model);
        Assert.assertTrue(repository.existsById(UUID1));
        Assert.assertFalse(repository.existsById(UUID2));
    }

    @Test
    public void testSet() {
        List<M> models = new ArrayList<>();
        models.add(createModel());
        models.add(createModel());
        repository.add(models);
        List<M> expectedModels = new ArrayList<>();
        expectedModels.add(createModel());
        expectedModels.add(createModel());
        repository.set(expectedModels);
        Assert.assertFalse(repository.findAll().containsAll(models));
        Assert.assertTrue(repository.findAll().containsAll(expectedModels));
        Assert.assertThrows(NullPointerException.class, () -> repository.set(null));
    }

    @Test
    public void testFindAllAndAddCollection() {
        List<M> models = new LinkedList<>();
        models.add(createModel());
        models.add(createModel());
        repository.add(models);
        Assert.assertEquals(repository.getSize(), repository.findAll().size());
        Assert.assertTrue(repository.findAll().containsAll(models));
        Assert.assertThrows(NullPointerException.class, () -> repository.add((List<M>) null));
    }

    @Test
    public void testAddAndFindOneById() {
        M expectedModel = createModel();
        repository.add(expectedModel);
        M model = repository.findOneById(expectedModel.getId());
        Assert.assertEquals(expectedModel, model);
        Assert.assertThrows(NullPointerException.class, () -> repository.add((M) null));
        Assert.assertThrows(NullPointerException.class, () -> repository.findOneById(null));
        Assert.assertNull(repository.findOneByIndex(null));
    }


    @Test
    @SneakyThrows
    public void testRemoveById() {
        M model = createModel();
        repository.add(model);
        repository.removeById(model.getId());
        Assert.assertEquals(0, repository.getSize());
        Assert.assertThrows(NullPointerException.class, () -> repository.remove(null));
        Assert.assertThrows(NullPointerException.class, () -> repository.removeById(null));
    }

    @Test
    @SneakyThrows
    public void testRemoveByIndex() {
        M model = createModel();
        repository.add(model);
        repository.removeByIndex(0);
        Assert.assertEquals(0, repository.getSize());
        Assert.assertThrows(NullPointerException.class, () -> repository.removeByIndex(null));
    }

}
