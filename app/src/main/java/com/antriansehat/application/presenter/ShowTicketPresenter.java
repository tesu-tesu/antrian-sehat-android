package com.antriansehat.application.presenter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import com.antriansehat.application.contract.ShowTicketContract;
import com.antriansehat.application.model.WaitingList;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class ShowTicketPresenter implements ShowTicketContract.Presenter {
    private ShowTicketContract.View view;

    public ShowTicketPresenter(ShowTicketContract.View view) {
        this.view = view;
    }

    @Override
    public void requestQRCode(WaitingList waitingList) {
        Bitmap bitmap;
        // Initializing the QR Encoder with your value to be encoded, type you required and Dimension
        QRGEncoder qrgEncoder = new QRGEncoder(waitingList.getBarcode(), null, QRGContents.Type.TEXT, 500);
        // Getting QR-Code as Bitmap
        bitmap = qrgEncoder.getBitmap();
        view.showTicket(bitmap);
    }
}
