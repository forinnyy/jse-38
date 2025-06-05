package ru.forinnyy.tm.enumerated;

import lombok.Getter;
import lombok.NonNull;
import ru.forinnyy.tm.comparator.CreatedComparator;
import ru.forinnyy.tm.comparator.NameComparator;
import ru.forinnyy.tm.comparator.StatusComparator;

import java.util.Comparator;

@Getter
public enum Sort {

    BY_NAME("Sort by name", NameComparator.INSTANCE),
    BY_STATUS("Sort by status", StatusComparator.INSTANCE),
    BY_CREATED("Sort by created", CreatedComparator.INSTANCE);
    
    private final String displayName;
    
    private final Comparator<?> comparator;

    public static Sort toSort(final String value) {
        if (value == null || value.isEmpty()) return null;
        for (@NonNull final Sort sort: values()) {
            if (sort.name().equals(value)) return sort;
        }
        return null;
    }

    Sort(String displayName, Comparator<?> comparator) {
        this.displayName = displayName;
        this.comparator = comparator;
    }

    @SuppressWarnings("rawtypes")
    public Comparator getComparator() {
        return comparator;
    }

}
