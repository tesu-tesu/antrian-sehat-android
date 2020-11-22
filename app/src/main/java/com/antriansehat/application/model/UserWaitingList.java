package com.antriansehat.application.model;

import java.util.List;

public class UserWaitingList {
    private List<WaitingList> futureWaitingList;
    private List<WaitingList> currentWaitingList;
    private List<WaitingList> historyWaitingList;

    public UserWaitingList(List<WaitingList> currentWaitingList, List<WaitingList> futureWaitingList, List<WaitingList> historyWaitingList) {
        this.futureWaitingList = futureWaitingList;
        this.currentWaitingList = currentWaitingList;
        this.historyWaitingList = historyWaitingList;
    }

    public List<WaitingList> getFutureWaitingList() {
        return futureWaitingList;
    }

    public List<WaitingList> getCurrentWaitingList() {
        return currentWaitingList;
    }

    public List<WaitingList> getHistoryWaitingList() {
        return historyWaitingList;
    }
}
