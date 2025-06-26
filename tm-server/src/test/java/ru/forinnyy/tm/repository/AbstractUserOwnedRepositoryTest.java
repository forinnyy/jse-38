package ru.forinnyy.tm.repository;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import ru.forinnyy.tm.model.AbstractUserOwnedModel;

import java.util.*;


public abstract class AbstractUserOwnedRepositoryTest<M extends AbstractUserOwnedModel> extends AbstractRepositoryTest<M> {

    protected AbstractUserOwnedRepository<M> getUserOwnedRepository() {
        return (AbstractUserOwnedRepository<M>) repository;
    }

    @Test
    public void testClearAndFindAllWithUserId() {
        M modelUserA = createModel();
        M modelUserB = createModel();
        M modelUserC = createModel();
        modelUserA.setUserId("1");
        modelUserB.setUserId("2");
        modelUserC.setUserId("2");
        getUserOwnedRepository().add("1", modelUserA);
        getUserOwnedRepository().add("2", modelUserB);
        getUserOwnedRepository().add("2", modelUserC);
        getUserOwnedRepository().clear("2");
        Assert.assertEquals(0, getUserOwnedRepository().findAll("2").size());
        Assert.assertThrows(NullPointerException.class, () -> getUserOwnedRepository().clear(null));
        Assert.assertThrows(NullPointerException.class, () -> getUserOwnedRepository().findAll((String) null));
    }

    @Test
    @SneakyThrows
    public void testExistsByIdAndFindOneBiyIdWithUserId() {
        final M model = createModel();
        model.setId("1");
        model.setUserId("1");
        getUserOwnedRepository().add("1", model);
        Assert.assertTrue(getUserOwnedRepository().existsById("1", "1"));
        Assert.assertFalse(getUserOwnedRepository().existsById("2", "2"));

        Assert.assertThrows(NullPointerException.class, () -> getUserOwnedRepository().existsById(null, "1"));
        Assert.assertThrows(NullPointerException.class, () -> getUserOwnedRepository().existsById("1", null));
        Assert.assertThrows(NullPointerException.class, () -> getUserOwnedRepository().findOneById(null, "1"));
        Assert.assertThrows(NullPointerException.class, () -> getUserOwnedRepository().findOneById("1", null));
    }

    @Test
    public void testRemoveAllWithUserId() {
        Map<String, M> models = new LinkedHashMap<>();
        getUserOwnedRepository().add("1", createModel());
        getUserOwnedRepository().add("2", createModel());
        getUserOwnedRepository().removeAll("1");
        Assert.assertEquals(Collections.emptyList(), getUserOwnedRepository().findAll("1"));
        Assert.assertThrows(NullPointerException.class, () -> getUserOwnedRepository().removeAll((String) null));
    }

    @Test
    @SneakyThrows
    public void testRemoveByIdWithUserId() {
        M model = createModel();
        getUserOwnedRepository().add("1", model);
        getUserOwnedRepository().removeById("1", model.getId());
        Assert.assertEquals(0, getUserOwnedRepository().getSize());
        Assert.assertThrows(NullPointerException.class, () -> getUserOwnedRepository().removeById("1", null));
        Assert.assertThrows(NullPointerException.class, () -> getUserOwnedRepository().removeById(null, "1"));
    }

}
