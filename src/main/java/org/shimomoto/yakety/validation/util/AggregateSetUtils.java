package org.shimomoto.yakety.validation.util;

import com.codepoetics.protonpack.maps.MapStream;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@UtilityClass
public class AggregateSetUtils {

    @NonNull
    public static <T> Set<T> getDuplicates(@NonNull final List<T> items) {
        return items.stream()
                .filter(i -> Collections.frequency(items, i) > 1)
                .collect(Collectors.toSet());
    }

    @NonNull
    public static <T, I> Set<I> getDuplicateIds(@NonNull final List<T> items,
                                                @NonNull final Function<T, I> idGetter) {
        //TODO: check if using frequency or distinct would yield better performance or lower memory usage
        final Map<I, Long> countById =
                items.stream()
                        .map(idGetter)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return MapStream.of(countById)
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    @NonNull
    public static <T, I, F> Set<I> getIdsOfDuplicateField(@NonNull final List<T> items,
                                                          @NonNull final Function<T, I> idGetter,
                                                          @NonNull final Function<T, F> getter) {
        final Map<F, List<T>> itemsByField = items.stream()
                .collect(Collectors.groupingBy(getter));

        return MapStream.of(itemsByField)
                .filter(e -> e.getValue().size() > 1)
                .flatMap(e -> e.getValue().stream())
                .map(idGetter)
                .collect(Collectors.toSet());
    }
}
