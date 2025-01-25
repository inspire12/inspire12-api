package com.inspire12.practice.api.sample.lib.rank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RankRunner<T> {

    @Test
    void testRun() {
        Comparator<Batter> comparator = Comparator.comparingInt(Batter::getHr).reversed();
        List<Batter> batters = Arrays.asList(
                new Batter("이승엽", 5),
                new Batter("김태균", 3),
                new Batter("이대호", 6),
                new Batter("나성범", 5)
        );
        RankRunner rankRunner = new RankRunner();
        rankRunner.run(batters, comparator);
    }

    public List<Rank<T>> run(List<T> l, Comparator<T> comparator) {
        List<Rank<T>> ranks = l.stream()
                .sorted(comparator)
                .collect(new RankCollector<>(comparator));
        ranks.forEach(rank -> System.out.println(rank.getRank() + " " + rank.getContent()));
        return ranks;
    }

    @ToString
    @Getter
    @AllArgsConstructor
    public static class Batter {
        String name;
        int hr; // 홈런
    }
}
