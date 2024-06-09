package ru.forinnyy.tm.api.repository;

import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.exception.field.UserIdEmptyException;
import ru.forinnyy.tm.model.AbstractModel;
import ru.forinnyy.tm.model.User;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface IRepository<M extends AbstractModel> {

    interface IRepositoryOptional<M> {

        Optional<M> findOneById(String id) throws AbstractFieldException;

        Optional<M> findOneByIndex(Integer index) throws AbstractFieldException;

    }

    default IRepositoryOptional<M> optional() {
        return new IRepositoryOptional<M>() {
            @Override
            public Optional<M> findOneById(String id) throws AbstractFieldException {
                return Optional.ofNullable(IRepository.this.findOneById(id));
            }

            @Override
            public Optional<M> findOneByIndex(Integer index) throws AbstractFieldException {
                return Optional.ofNullable(IRepository.this.findOneByIndex(index));
            }
        };
    }

    void clear();

    List<M> findAll();

    List<M> findAll(Comparator<M> comparator);

    M add(M model);

    boolean existsById(String id);

    M findOneById(String id) throws AbstractFieldException;

    M findOneByIndex(Integer index) throws AbstractFieldException;

    int getSize();

    M remove(M model) throws AbstractEntityException;

    M removeById(String id) throws AbstractFieldException, AbstractEntityException;

    M removeByIndex(Integer index) throws AbstractFieldException, AbstractEntityException;

    void removeAll(Collection<M> collection);

}
