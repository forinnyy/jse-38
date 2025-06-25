package ru.forinnyy.tm.repository;

import lombok.SneakyThrows;
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
