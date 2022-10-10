package sample.lib.rank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RankRunner {
    public static void run() {
        List<Batter> batters = Arrays.asList(
                new Batter("이승엽", 5),
                new Batter("김태균", 3),
                new Batter("이대호", 6),
                new Batter("나성범", 5)
        );

        Comparator<Batter> comparator = Comparator.comparingInt(Batter::getHr).reversed();
        List<Rank<Batter>> batterRanks = batters.stream()
                .sorted(comparator)
                .collect(new RankCollector<>(comparator));
        batterRanks.forEach(batterRank -> System.out.println(batterRank.getRank() + " " + batterRank.getContent().getName()));
    }

    @ToString
    @Getter
    @AllArgsConstructor
    public static class Batter {
        String name;
        int hr; // 홈런
    }
}
