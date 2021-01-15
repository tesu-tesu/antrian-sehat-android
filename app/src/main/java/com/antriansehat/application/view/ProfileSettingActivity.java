package com.antriansehat.application.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.antriansehat.application.constant.ApiConstant;
import com.antriansehat.application.contract.ProfileSettingContract;
import com.antriansehat.application.databinding.ActivityProfileSettingBinding;
import com.antriansehat.application.interactor.ProfileSettingInteractor;
import com.antriansehat.application.model.User;
import com.antriansehat.application.presenter.ProfileSettingPresenter;
import com.antriansehat.application.util.UtilProvider;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.squareup.picasso.Picasso;

import java.io.File;

public class  ProfileSettingActivity extends AppCompatActivity implements ProfileSettingContract.View, View.OnClickListener{
    private ProfileSettingPresenter presenter;
    private ActivityProfileSettingBinding binding;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ProfileSettingPresenter(this, new ProfileSettingInteractor(UtilProvider.getSharedPreferencesUtil()));
        presenter.setUserData();

        initView();
    }

    private void initView() {
        binding.btnSave.setOnClickListener(this);
        binding.btnCancel.setOnClickListener(this);
        binding.btChangeProfileImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btnSave.getId()){
            onSaveUpdateClick();
            backToProfile();
        } else if(v.getId() == binding.btnCancel.getId()){
            backToProfile();
        } else if(v.getId() == binding.btChangeProfileImage.getId()) {
            selectImage();
        }
    }

    private void selectImage() {
        ImagePicker.Companion.with(this)
                .cropSquare()  			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            binding.ivProfileImage.setImageURI(uri);
            file = ImagePicker.Companion.getFile(data);
            presenter.updateProfileImage(file);
        }else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    private void backToProfile() {
        this.finish();
    }

    @Override
    public void startLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSaveUpdateClick() {
        binding.btnSave.setEnabled(false);

        presenter.updateProfile(binding.etNama.getText().toString(),
                binding.etEmail.getText().toString(),
                binding.etNIK.getText().toString(),
                binding.etPhone.getText().toString()
        );

    }

    @Override
    public void updateSuccess(String message) {
        binding.btnSave.setEnabled(true);
        makeToast(message);
        backToProfile();
    }

    @Override
    public void updateFailed(String message) {
        binding.btnSave.setEnabled(true);
        makeToast(message);
    }

    @Override
    public void showUserData(User user) {
        binding.etNama.setText(user.getName());
        binding.etEmail.setText(user.getEmail());
        binding.etNIK.setText(user.getResidence_number());
        if(user.getResidence_number()!= null)
            binding.etNIK.setEnabled(false);
        binding.etPhone.setText(user.getPhone());
        if(user.getImagePath() != null) {
            Picasso.get()
                    .load(ApiConstant.SERVER_NAME + "/users/" + user.getImagePath())
                    .fit()
                    .into(binding.ivProfileImage);
        }
    }

    @Override
    public void updateProfileImageSuccess(String message, String path) {
        makeToast(message);
        if(path != null) {
            Picasso.get().load(ApiConstant.SERVER_NAME + "/users/" + path).into(binding.ivProfileImage);
            System.out.println("PATH: " + ApiConstant.SERVER_NAME + path);
        }
    }

    private void makeToast(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
}
