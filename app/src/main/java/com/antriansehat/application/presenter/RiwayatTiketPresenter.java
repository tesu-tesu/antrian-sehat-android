package com.antriansehat.application.presenter;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.contract.RiwayatTiketContract;
import com.antriansehat.application.model.UserWaitingList;
import com.antriansehat.application.model.WaitingList;

import java.util.List;

public class RiwayatTiketPresenter implements RiwayatTiketContract.Presenter {
    private RiwayatTiketContract.View view;
    private RiwayatTiketContract.Interactor interactor;

    public RiwayatTiketPresenter(RiwayatTiketContract.View view, RiwayatTiketContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void requestTicket(final int ticketType) {
        view.startLoading();
        System.out.println("menghubungkan");
        new Thread() {
            @Override
            public void run() {
                super.run();
                interactor.requestTicket(ticketType, new RequestCallback<List<WaitingList>>() {
                    @Override
                    public void requestSuccess(List<WaitingList> data) {
                        System.out.println("BERHASIL");
                        view.endLoading();
                        view.showWaitingList(data);
                    }

                    @Override
                    public void requestFailed(String errorMessage) {
                        System.out.println("GAGAL");
                        view.endLoading();
                        view.showError(errorMessage);
                    }
                });
            }
        }.start();
    }

}
