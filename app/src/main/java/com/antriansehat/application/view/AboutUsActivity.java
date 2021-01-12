package com.antriansehat.application.view;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.antriansehat.application.R;
import com.antriansehat.application.databinding.ActivityAboutUsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.antriansehat.application.util.ScaleBitmapUtil.decodeSampledBitmapFromResource;

public class AboutUsActivity extends AppCompatActivity implements BaseAuthenticatedView, BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private ActivityAboutUsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutUsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        binding.bottomNav.setOnNavigationItemSelectedListener(this);
        binding.btBack.setOnClickListener(this);
        prepareImage();
    }

    private void prepareImage() {
        binding.ivDevRefano.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.dev_3_refano, 100, 100));
        binding.ivDevYanu.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.dev_4_yanu, 100, 100));
        binding.ivDevIim.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.dev_15_rochimatus, 100, 100));
        binding.ivDevBella.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.dev_21_sabillah, 100, 100));
        binding.ivDevGhozy.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.dev_22_ghozy, 100, 100));
        binding.ivDevAji.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.dev_27_aji, 100, 100));
        binding.ivVirtuaHive.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.sponsored_virtuahive, 100, 100));
        binding.ivRasyidTech.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.sponsored_rasyidtechnologies, 100, 100));
        binding.ivPTI.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.sponsored_pti_sq, 100, 100));
        binding.ivMaulidanGames.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.sponsored_maulidangames, 100, 100));
        binding.ivSindika.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.supported_sindika, 100, 100));
        binding.ivRasyidInst.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.supported_rasyidinstitute, 100, 100));
        binding.ivTrustMedis.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.supported_trustmedis, 100, 100));
        binding.ivProfilku.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.supported_profilku_bl, 100, 100));
        binding.ivAlterra.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.supported_alterra, 100, 100));
    }

    @Override
    public void bottomBarAction(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent home = new Intent(AboutUsActivity.this,HomeActivity.class);
                startActivity(home);
                this.finish();
                break;
            case R.id.action_user:
                Intent profile = new Intent(AboutUsActivity.this,ProfileActivity.class);
                startActivity(profile);
                this.finish();
                break;
            case R.id.action_time:
                Intent time = new Intent(AboutUsActivity.this,RiwayatTiketActivity.class);
                startActivity(time);
                this.finish();
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        bottomBarAction(item);
        return false;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btBack.getId())
            finish();
    }
}
