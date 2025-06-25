package ru.forinnyy.tm.repository;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import ru.forinnyy.tm.model.User;

public final class UserRepositoryTest extends AbstractRepositoryTest<User> {

    @Override
    protected AbstractRepository<User> createRepository() {
        return new UserRepository();
    }

    @Override
    protected User createModel() {
        return new User();
    }

    @Test
    @SneakyThrows
    public void testFindByLogin() {
        User user = userRepository.findByLogin("admin");
        Assert.assertEquals(adminUser, user);

        Assert.assertThrows(NullPointerException.class, () -> userRepository.findByLogin(null));
    }

    @Test
    @SneakyThrows
    public void testFindByEmail() {
        User user = userRepository.findByEmail("test@test.ru");
        Assert.assertEquals(testUser, user);

        Assert.assertThrows(NullPointerException.class, () -> userRepository.findByEmail(null));
    }

    @Test
    public void testIsLoginExist() {
        Assert.assertTrue(userRepository.isLoginExist("admin"));
        Assert.assertThrows(NullPointerException.class, () -> userRepository.isLoginExist(null));
    }

    @Test
    public void testIsEmailExist() {
        Assert.assertTrue(userRepository.isEmailExist("test@test.ru"));
        Assert.assertThrows(NullPointerException.class, () -> userRepository.isEmailExist(null));
    }

}
