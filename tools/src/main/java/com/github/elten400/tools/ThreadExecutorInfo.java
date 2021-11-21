package com.github.elten400.tools;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThreadExecutorInfo {
    private long totalTime;
    private long mimeTime;
    private long maxTime;
    private long minTime;
}
