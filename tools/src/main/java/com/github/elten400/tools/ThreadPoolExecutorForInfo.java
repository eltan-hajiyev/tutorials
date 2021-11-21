package com.github.elten400.tools;


import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class ThreadPoolExecutorForInfo {
    private final ThreadPoolTaskExecutor taskExecutor;

    public ThreadPoolExecutorForInfo() {
        taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(2000);
        taskExecutor.setQueueCapacity(0);
        taskExecutor.setThreadNamePrefix("TaskExecutor-");
        taskExecutor.initialize();
    }

    public ThreadExecutorInfo execute(int threadCount, Callable callable) {
        return execute(Collections.nCopies(threadCount, callable).toArray(new Callable[]{}));
    }

    public ThreadExecutorInfo execute(Callable... callable) {
        List<CompletableFuture> futureList = new ArrayList<>();
        List<CallableInfo> callableInfoList = new ArrayList<>();

        for (Callable c : callable) {
            CallableInfo callableInfo = new CallableInfo(c);
            callableInfoList.add(callableInfo);
            CompletableFuture future = taskExecutor.submitListenable(callableInfo).completable();
            futureList.add(future);
        }

        futureList.stream().forEach(f -> f.join());

        long totalTime = callableInfoList.stream().mapToLong(t -> t.getTotalSpendTime()).sum();

        return ThreadExecutorInfo.builder()
                .minTime(callableInfoList.stream().mapToLong(t -> t.getTotalSpendTime()).min().getAsLong())
                .maxTime(callableInfoList.stream().mapToLong(t -> t.getTotalSpendTime()).max().getAsLong())
                .totalTime(totalTime)
                .mimeTime(totalTime / callable.length)
                .build();
    }
}
