package com.alifadepe.android_example.presenter;

import com.alifadepe.android_example.api_response.LoginResponse;
import com.alifadepe.android_example.constant.ApiConstant;
import com.alifadepe.android_example.contract.LoginContract;
import com.alifadepe.android_example.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private SharedPreferencesUtil sharedPreferencesUtil;

    public LoginPresenter(LoginContract.View view, SharedPreferencesUtil sharedPreferencesUtil) {
        this.view = view;
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void login(String username, String password) {
        view.startLoading();

        AndroidNetworking.post(ApiConstant.BASE_URL + "login.php")
                .addBodyParameter("username", username)
                .addBodyParameter("password", password)
                .build()
                .getAsObject(LoginResponse.class, new ParsedRequestListener<LoginResponse>() {
                    @Override
                    public void onResponse(LoginResponse response) {
                        view.endLoading();

                        if(response == null){
                            view.loginFailed("Null Response");
                        }
                        else if(response.is_success){
                            sharedPreferencesUtil.setToken(response.token);
                            view.loginSuccess();
                        }
                        else {
                            view.loginFailed(response.message);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        view.endLoading();
                        view.loginFailed(anError.getMessage());
                    }
                });
    }
}
