package ru.forinnyy.tm.api.repository;

import lombok.NonNull;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.AbstractModel;

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

    @NonNull
    Collection<M> add(@NonNull Collection<M> models);

    @NonNull
    Collection<M> set(@NonNull Collection<M> models);

    void clear();

    @NonNull
    List<M> findAll();

    @NonNull
    List<M> findAll(Comparator<M> comparator);

    @NonNull
    M add(@NonNull M model) throws AbstractEntityException;

    boolean existsById(String id);

    @NonNull
    M findOneById(@NonNull String id) throws AbstractFieldException;

    M findOneByIndex(Integer index) throws AbstractFieldException;

    int getSize();

    @NonNull
    M remove(@NonNull M model) throws AbstractEntityException;

    M removeById(String id) throws AbstractFieldException, AbstractEntityException;

    M removeByIndex(Integer index) throws AbstractFieldException, AbstractEntityException;

    void removeAll(Collection<M> collection);

}
