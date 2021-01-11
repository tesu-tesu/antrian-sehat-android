package com.antriansehat.application.api_response;

import com.antriansehat.application.model.UserWaitingList;
import com.antriansehat.application.model.WaitingList;

import java.util.List;

public class ListOfWaitingListResponse {
    public boolean success;
//    public List<WaitingList> currentWaitingList;
//    public List<WaitingList> historyWaitingList;
//    public List<WaitingList> futureWaitingList;
    public List<WaitingList> data;
    public String message;
}
