package ru.bogdanov.mythbusters.items;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryItem {
    private int threadCount;
    private String dateTime;
    private long secondsSpent;

    public HistoryItem(int threadCount, long secondsSpent) {
        this.threadCount = threadCount;
        this.dateTime = new SimpleDateFormat("mm:HH:ss").format(new Date());
        this.secondsSpent = secondsSpent;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public long getSecondsSpent() {
        return secondsSpent;
    }
}
