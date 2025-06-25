package ru.forinnyy.tm.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.forinnyy.tm.model.AbstractModel;


public abstract class AbstractRepositoryTest<M extends AbstractModel> {

    protected AbstractRepository<M> repository;

    protected abstract AbstractRepository<M> getRepository();
    protected abstract M createModel();

    @Before
    public void init() {
        repository = getRepository();
        repository.clear();
    }

    @Test
    public void testAddAndFindOneById() {
        M model = createModel();
        repository.add(model);
        M found = repository.findOneById(model.getId());
        Assert.assertEquals(model, found);
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
