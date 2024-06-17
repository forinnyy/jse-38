package ru.forinnyy.tm.enumerated;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.forinnyy.tm.comparator.CreatedComparator;
import ru.forinnyy.tm.comparator.NameComparator;
import ru.forinnyy.tm.comparator.StatusComparator;

import java.util.Comparator;

@Getter
public enum Sort {

    BY_NAME("Sort by name", NameComparator.INSTANCE),
    BY_STATUS("Sort by status", StatusComparator.INSTANCE),
    BY_CREATED("Sort by created", CreatedComparator.INSTANCE);

    @Nullable
    private final String displayName;

    @Nullable
    private final Comparator<?> comparator;

    @Nullable
    public static Sort toSort(final String value) {
        if (value == null || value.isEmpty()) return null;
        for (final Sort sort: values()) {
            if (sort.name().equals(value)) return sort;
        }
        return null;
    }

    Sort(@Nullable String displayName, @Nullable Comparator<?> comparator) {
        this.displayName = displayName;
        this.comparator = comparator;
    }

    public Comparator getComparator() {
        return comparator;
    }

}
