package com.inspire12.practice.api.provider.designpattern.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class BookIterator implements Iterator<Book> {

    List<Book> bookList;
    private int last = 0;

    @Override
    public boolean hasNext() {
        return last + 1 <= bookList.size();
    }

    @Override
    public Book next() {
        if (hasNext()) {
            last += 1;
            return bookList.get(last);
        }
        return null;
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }

    @Override
    public void forEachRemaining(Consumer<? super Book> action) {
        Iterator.super.forEachRemaining(action);
    }
}
