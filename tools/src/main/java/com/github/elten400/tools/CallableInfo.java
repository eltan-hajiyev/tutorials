package com.github.elten400.tools;

import java.util.concurrent.Callable;

public class CallableInfo<T> implements Callable<T> {
    long beginTime;
    long endTime;

    Callable<T> callable;

    public CallableInfo(Callable<T> callable) {
        this.callable = callable;
    }

    @Override
    public T call() throws Exception {
        beginTime = System.currentTimeMillis();
        T t = callable.call();
        endTime = System.currentTimeMillis();
        return t;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getTotalSpendTime() {
        return endTime - beginTime;
    }
}
