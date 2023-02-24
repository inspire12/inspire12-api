package com.inspire12.practice.api.sample.lab.parellel;

//

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.function.Consumer;

//
public class ForkingStreamConsumer<T> implements Consumer<T>, StreamTest.StreamForker.Results {

    public <T> ForkingStreamConsumer(List<BlockingQueue<T>> queues, Map<Object, Future<?>> actions) {

    }

    @Override
    public <R> R get(Object key) {
        return null;
    }

    @Override
    public void accept(T t) {

    }
}
