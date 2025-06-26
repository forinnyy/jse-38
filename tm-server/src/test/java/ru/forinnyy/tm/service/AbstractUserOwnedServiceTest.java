package ru.forinnyy.tm.service;

import org.junit.Assert;
import org.junit.Test;
import ru.forinnyy.tm.api.repository.IUserOwnedRepository;
import ru.forinnyy.tm.model.AbstractUserOwnedModel;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class AbstractUserOwnedServiceTest<M extends AbstractUserOwnedModel, R extends IUserOwnedRepository<M>>
        extends AbstractServiceTest<M, R> {

    protected static final String USER_ID = UUID.randomUUID().toString();

    @Override
    protected M createModel() {
        final M model = createEmptyModel();
        model.setId(UUID.randomUUID().toString());
        model.setUserId(USER_ID);
        return model;
    }

    protected abstract M createEmptyModel();

    @Test
    public void testAddAndFindAllByUserId() {
        final M model1 = createModel();
        final M model2 = createModel();
        service.add(Arrays.asList(model1, model2));

        List<M> userModels = service.findAll().stream()
                .filter(m -> USER_ID.equals(m.getUserId()))
                .collect(Collectors.toList());

        Assert.assertEquals(2, userModels.size());
        Assert.assertTrue(userModels.contains(model1));
        Assert.assertTrue(userModels.contains(model2));
    }

    @Test
    public void testRemoveAllByUserId() {
        final M model1 = createModel();
        final M model2 = createModel();
        final M otherUserModel = createEmptyModel();
        otherUserModel.setId(UUID.randomUUID().toString());
        otherUserModel.setUserId("another-user");

        service.add(Arrays.asList(model1, model2, otherUserModel));

        List<M> toRemove = service.findAll().stream()
                .filter(m -> USER_ID.equals(m.getUserId()))
                .collect(Collectors.toList());

        service.removeAll(toRemove);

        List<M> remaining = service.findAll();
        Assert.assertEquals(1, remaining.size());
        Assert.assertEquals("another-user", remaining.get(0).getUserId());
    }

    @Test
    public void testFindAllByUserIdWhenNoneExist() {
        List<M> userModels = service.findAll().stream()
                .filter(m -> "non-existent-user".equals(m.getUserId()))
                .collect(Collectors.toList());

        Assert.assertNotNull(userModels);
        Assert.assertTrue(userModels.isEmpty());
    }
}
