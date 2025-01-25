package com.inspire12.practice.api.sample.lab.parellel;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.function.Function;
import java.util.stream.Stream;

public class StreamTest {


    @Test
    void test() {
        List<String> l = List.of("hihi", "hihi2");
        StreamForker<String> stringStreamForker = new StreamForker<>(l.stream());

    }

    public class StreamForker<T> {
        private final Stream<T> stream;
        private final Map<Object, Function<Stream<T>, ?>> forks = new HashMap();

        public StreamForker(Stream<T> stream) {
            this.stream = stream;
        }

        public StreamForker<T> fork(Object key, Function<Stream<T>, ?> f) {
            forks.put(key, f);
            return this;
        }

//        public Results getResults() {
//            ForkingStreamConsumer<T> consumer = build();
//            try {
//                stream.sequential().forEach(consumer);
//            } finally {
//                consumer.finish();
//            }
//            return
//        }

        private ForkingStreamConsumer<T> build() {
            List<BlockingQueue<T>> queues = new ArrayList<>();
            Map<Object, Future<?>> actions = forks.entrySet()
                    .stream().reduce(new HashMap<>(),
                            (map, e) -> {
                                map.put(e.getKey(), getOperationResult(queues, e.getValue()));
                                return map;
                            }, (m1, m2) -> {
                                m1.putAll(m2);
                                return m1;
                            });
            return new ForkingStreamConsumer<>(queues, actions);
        }

        private Future<?> getOperationResult(List<BlockingQueue<T>> queues, Function<Stream<T>, ?> value) {
            return new FutureTask<>(() -> "hi");
        }

        public interface Results {
            <R> R get(Object key);
        }
    }
}
