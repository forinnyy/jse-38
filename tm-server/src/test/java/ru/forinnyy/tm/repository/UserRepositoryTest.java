package ru.forinnyy.tm.repository;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ru.forinnyy.tm.model.User;

public final class UserRepositoryTest extends AbstractRepositoryTest<User> {

    private UserRepository userRepository;

    @Override
    protected AbstractRepository<User> createRepository() {
        return new UserRepository();
    }

    @Override
    protected User createModel() {
        @NonNull final User user = new User();
        return user;
    }

    private UserRepository getUserRepository() {
        return (UserRepository) createRepository();
    }

    public void initUserAndUserRepository() {
        @NonNull final User user = createModel();
        userRepository = getUserRepository();
        user.setId(UUID1);
        user.setLogin("user");
        user.setEmail("user@user.ru");
        userRepository.add(user);
    }

    @Test
    @SneakyThrows
    public void testFindByLogin() {
        initUserAndUserRepository();
        Assert.assertEquals(userRepository.findOneById(UUID1), userRepository.findByLogin("user"));
        Assert.assertThrows(NullPointerException.class, () -> userRepository.findByLogin(null));
    }

    @Test
    @SneakyThrows
    public void testFindByEmail() {
        initUserAndUserRepository();
        Assert.assertEquals(userRepository.findOneById(UUID1), userRepository.findByEmail("user@user.ru"));
        Assert.assertThrows(NullPointerException.class, () -> userRepository.findByEmail(null));
    }

    @Test
    public void testIsLoginExist() {
        initUserAndUserRepository();
        Assert.assertTrue(userRepository.isLoginExist("user"));
        Assert.assertThrows(NullPointerException.class, () -> userRepository.isLoginExist(null));
    }

    @Test
    public void testIsEmailExist() {
        initUserAndUserRepository();
        Assert.assertTrue(userRepository.isEmailExist("user@user.ru"));
        Assert.assertThrows(NullPointerException.class, () -> userRepository.isEmailExist(null));
    }

}
