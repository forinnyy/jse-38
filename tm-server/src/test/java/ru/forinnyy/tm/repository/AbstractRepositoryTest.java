package ru.forinnyy.tm.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.forinnyy.tm.model.AbstractModel;


public abstract class AbstractRepositoryTest<M extends AbstractModel> extends Data {

    protected AbstractRepository<M> repository;

    protected abstract AbstractRepository<M> createRepository();
    protected abstract M createModel();

    @Before
    public void init() {
        repository = createRepository();
        repository.clear();
    }

    @Test
    public void testAddAndFindOneById() {
        M expectedModel = createModel();
        repository.add(expectedModel);
        M model = repository.findOneById(expectedModel.getId());
        Assert.assertEquals(expectedModel, model);
        Assert.assertThrows(NullPointerException.class, () -> repository.add((M) null));
        Assert.assertThrows(NullPointerException.class, () -> repository.findOneById(null));
    }

    @Test
    public void testRemoveById() throws Exception {
        M model = createModel();
        repository.add(model);
        repository.removeById(model.getId());
        Assert.assertNull(repository.findOneById(model.getId()));
    }

    @Test
    public void testClear() {
        repository.add(createModel());
        repository.clear();
        Assert.assertEquals(0, repository.getSize());
    }

    @Test
    public void testGetSize() {
        repository.add(createModel());
        repository.add(createModel());
        Assert.assertEquals(2, repository.getSize());
    }
}
