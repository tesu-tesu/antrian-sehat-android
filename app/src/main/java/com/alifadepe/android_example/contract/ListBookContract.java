package com.alifadepe.android_example.contract;

import com.alifadepe.android_example.model.Book;

import java.util.List;

public interface ListBookContract {
    interface View {
        void startLoading();
        void endLoading();
        void showListBook(List<Book> books);
        void showError(String errorMessage);
    }

    interface Presenter {
        void requestListBook();
        void logout();
    }
}
