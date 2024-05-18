package ru.forinnyy.tm.service;

import ru.forinnyy.tm.api.repository.IUserRepository;
import ru.forinnyy.tm.api.service.IUserService;
import ru.forinnyy.tm.enumerated.Role;
import ru.forinnyy.tm.exception.entity.*;
import ru.forinnyy.tm.exception.field.*;
import ru.forinnyy.tm.exception.user.*;
import ru.forinnyy.tm.model.User;
import ru.forinnyy.tm.util.HashUtil;

import java.util.List;

public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(final String login, final String password) throws AbstractUserException, AbstractFieldException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(HashUtil.salt(password));
        user.setRole(Role.USUAL);
        try {
            return userRepository.add(user);
        } catch (AbstractEntityException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User create(final String login, final String password, final String email) throws AbstractUserException, AbstractFieldException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (isEmailExist(email)) throw new ExistsEmailException();
        final User user = create(login, password);
        user.setEmail(email);
        return user;
    }

    @Override
    public User create(final String login, final String password, final Role role) throws AbstractUserException, AbstractFieldException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        if (isLoginExist(login)) throw new ExistsLoginException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        if (role == null) throw new RoleEmptyException();
        final User user = create(login, password);
        if (role != null) user.setRole(role);
        return user;
    }

    @Override
    public User add(final User user) throws AbstractEntityException {
        if (user == null) throw new UserNotFoundException();
        return userRepository.add(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(final String id) throws AbstractFieldException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        final User user = userRepository.findById(id);
        if (user == null) return null;
        return user;
    }

    @Override
    public User findByLogin(final String login) throws LoginEmptyException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        final User user = userRepository.findByLogin(login);
        if (user == null) return null;
        return user;
    }

    @Override
    public User findByEmail(final String email) throws ExistsEmailException {
        if (email == null || email.isEmpty()) throw new ExistsEmailException();
        final User user = userRepository.findByEmail(email);
        if (user == null) return null;
        return user;
    }

    @Override
    public User remove(final User user) throws UserNotFoundException {
        if (user == null) throw new UserNotFoundException();
        return userRepository.remove(user);
    }

    @Override
    public User removeById(final String id) throws AbstractFieldException, AbstractEntityException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        final User user = findById(id);
        return remove(user);
    }

    @Override
    public User removeByLogin(final String login) throws AbstractEntityException, AbstractFieldException {
        if (login == null || login.isEmpty()) throw new LoginEmptyException();
        final User user = findByLogin(login);
        return remove(user);
    }

    @Override
    public User removeByEmail(final String email) throws AbstractEntityException, AbstractUserException, AbstractFieldException {
        if (email == null || email.isEmpty()) throw new EmailEmptyException();
        final User user = findByEmail(email);
        return remove(user);
    }

    @Override
    public Boolean isLoginExist(final String login) {
        if (login == null || login.isEmpty()) return false;
        return userRepository.isLoginExist(login);
    }

    @Override
    public Boolean isEmailExist(final String email) {
        if (email == null || email.isEmpty()) return false;
        return userRepository.isEmailExist(email);
    }

    @Override
    public User setPassword(final String id, final String password) throws AbstractFieldException, AbstractEntityException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        if (password == null || password.isEmpty()) throw new PasswordEmptyException();
        final User user = findById(id);
        if (user == null) throw new UserNotFoundException();
        user.setPasswordHash(HashUtil.salt(password));
        return user;
    }

    @Override
    public User updateUser(
            final String id,
            final String firstName,
            final String lastName,
            final String middleName
    ) throws AbstractFieldException, AbstractEntityException {
        if (id == null || id.isEmpty()) throw new IdEmptyException();
        final User user = findById(id);
        if (user == null) throw new UserNotFoundException();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setMiddleName(middleName);
        return user;
    }

}
