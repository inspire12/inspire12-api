package com.inspire12.practice.api.provider;


import java.util.Collection;
import java.util.Iterator;

public class RoundRobinList<T> {
    private final Collection<T> elements;
    private Iterator<T> iterator;

    public RoundRobinList(Collection<T> elements) {
        this.elements = elements;
        iterator = this.elements.iterator();
    }

    synchronized public T get() {
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            iterator = elements.iterator();
            return iterator.next();
        }
    }

    public int size() {
        return elements.size();
    }
}
