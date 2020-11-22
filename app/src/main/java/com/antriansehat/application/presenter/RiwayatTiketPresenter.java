package com.antriansehat.application.presenter;

import com.antriansehat.application.callback.RequestCallback;
import com.antriansehat.application.contract.RiwayatTiketContract;
import com.antriansehat.application.model.UserWaitingList;

public class RiwayatTiketPresenter implements RiwayatTiketContract.Presenter {
    private RiwayatTiketContract.View view;
    private RiwayatTiketContract.Interactor interactor;

    public RiwayatTiketPresenter(RiwayatTiketContract.View view, RiwayatTiketContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void requestTicket() {
        view.startLoading();
        System.out.println("MENGHUBUNGI");
        interactor.requestTicket(new RequestCallback<UserWaitingList>() {

            @Override
            public void requestSuccess(UserWaitingList data) {
                view.endLoading();
                System.out.println("BERHASIL");
                view.showCurrentWaitingList(data.getCurrentWaitingList());
                view.showFutureWaitingList(data.getFutureWaitingList());
                view.showHistoryWaitingList(data.getHistoryWaitingList());
            }

            @Override
            public void requestFailed(String errorMessage) {
                System.out.println("GAGAL");
                view.endLoading();
                view.showError(errorMessage);
            }
        });
    }
}
