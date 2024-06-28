package ru.forinnyy.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

        Optional<M> findOneById(@Nullable String id) throws AbstractFieldException;

        Optional<M> findOneByIndex(@Nullable Integer index) throws AbstractFieldException;

    }

    default IRepositoryOptional<M> optional() {
        return new IRepositoryOptional<M>() {
            @Override
            public Optional<M> findOneById(@Nullable String id) throws AbstractFieldException {
                return Optional.ofNullable(IRepository.this.findOneById(id));
            }

            @Override
            public Optional<M> findOneByIndex(@Nullable Integer index) throws AbstractFieldException {
                return Optional.ofNullable(IRepository.this.findOneByIndex(index));
            }
        };
    }

    void clear();

    @NotNull
    List<M> findAll();

    @NotNull
    List<M> findAll(@Nullable Comparator<M> comparator);

    @NotNull
    M add(@Nullable M model) throws AbstractEntityException;

    boolean existsById(@Nullable String id);

    @Nullable
    M findOneById(@Nullable String id) throws AbstractFieldException;

    @Nullable
    M findOneByIndex(@Nullable Integer index) throws AbstractFieldException;

    int getSize();

    @Nullable
    M remove(@Nullable M model) throws AbstractEntityException;

    @Nullable
    M removeById(@Nullable String id) throws AbstractFieldException, AbstractEntityException;

    @Nullable
    M removeByIndex(@Nullable Integer index) throws AbstractFieldException, AbstractEntityException;

    void removeAll(@Nullable Collection<M> collection);

}
