package com.inspire12.practice.api.lib.rank;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class RankCollector<T> implements Collector<T, List<Rank<T>>, List<Rank<T>>> {

    private Comparator<T> comparator;

    @Override
    public Supplier<List<Rank<T>>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<Rank<T>>, T> accumulator() {
        return (List<Rank<T>> list, T current) -> {
            if (list.isEmpty()) {
                list.add(new Rank<>(1, current));
            } else {
                Rank<T> lastElement = list.get(list.size() - 1);
                int rank = 0;
                if (comparator.compare(lastElement.getContent(), current) == 0) {
                    rank = lastElement.getRank();
                } else if (comparator.compare(lastElement.getContent(), current) < 0) {
                    rank = list.size() + 1;
                } else {
                    throw new RuntimeException(); // 정렬이 되어있어야함
                }
                list.add(new Rank<>(rank, current));
            }
        };
    }

    @Override
    public BinaryOperator<List<Rank<T>>> combiner() {
        return (List<Rank<T>> list1, List<Rank<T>> list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<Rank<T>>, List<Rank<T>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Stream.of(Characteristics.IDENTITY_FINISH).collect(Collectors.toSet());
    }
}
