package com.antriansehat.application.contract;

import android.graphics.Bitmap;

import com.antriansehat.application.model.WaitingList;

public interface ShowTicketContract {
    interface View {
        void showError(String errorMessage);
        void showTicket(Bitmap qrCode);
    }

    interface Presenter {
        void requestQRCode(WaitingList waitingList);
    }
}
