package com.campus.CampusCake.activity;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.campus.CampusCake.R;
import com.campus.CampusCake.api.StoregeHelper;
import com.campus.CampusCake.api.productHelper;
import com.campus.CampusCake.databinding.ActivityVenteGateauBinding;
import com.campus.CampusCake.model.ProductModel;
import com.campus.CampusCake.model.ProductPrizeEtDes;
import com.campus.CampusCake.model.productUidEtStatu;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class venteGateauActivity extends AppCompatActivity {
private ActivityVenteGateauBinding binding ;
private Uri imgSelected ;
private  String producId ;
private  ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    // Handle the returned Uri
                    binding.imgInflate.setImageURI(uri);
                   imgSelected=uri;
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityVenteGateauBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgBoxContain.setOnClickListener(v -> mGetContent.launch("image/*"));
        inflate();
        binding.validBtn.setOnClickListener(v -> {
            if (imgSelected!=null){
                send() ;
            }else{
                Toast.makeText(this ," selectionner une photo" ,Toast.LENGTH_LONG).show();
            }
        });
        onBuildProductId() ;
    }

    private void inflate(){
        binding.prouctDescrip.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.descripInflate.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        binding.productPrize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.prizeInflate.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.productNam.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.titleInflate.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private String getPrize(){
        return binding.productPrize.getText().toString() ;
    }

    private String getName(){
        return  binding.productNam.getText().toString() ;
    }

    private String getDescription(){
        return  binding.prouctDescrip.getText().toString() ;
    }

  private void send(){

      checkInputIsFull();
  }

  private void onBuildProductId(){
        long time = System.currentTimeMillis() ;
        String s = getResources().getString(R.string.current_user_UId) ;
       this.producId= time+s ;
  }

  private  void checkInputIsFull(){
        if (binding.productNam.getText().toString().isEmpty()){
            binding.productNam.setError("");
        }if (binding.productPrize.getText().toString().isEmpty()){
          binding.productPrize.setError("");
      }
        if (binding.prouctDescrip.getText().toString().isEmpty()){
            binding.prouctDescrip.setError("");
        }
        else{
            binding.editBox.setVisibility(View.GONE);
            binding.progressBarSend.setVisibility(View.VISIBLE);
            binding.validBtn.setVisibility(View.GONE);
            loadData(imgSelected);
        }
  }
  private void  loadData(Uri uri){
      StorageReference ref =StoregeHelper.getImgRef(producId);
      ref.putFile(uri).addOnSuccessListener(taskSnapshot -> ref.getDownloadUrl().addOnSuccessListener(uri1 -> {
          ProductModel product = new ProductModel(producId,getName(), uri1.toString(),getString(R.string.current_user_UId));
          loadProduct(product);
          loadDesEtPrize(uri1.toString());
      })) ;
  }
  private  void loadDesEtPrize(String img){
      productHelper.getGateauDetailRoomDoc(producId).set(new ProductPrizeEtDes(getName(),getPrize(),getDescription(),img)) ;
  }
  private  void loadProduct(ProductModel product){
      productHelper.getGateauRoomDoc(producId).set(product).addOnSuccessListener(aVoid -> SavePublicRegister());
  }
  private void SavePublicRegister(){
        productHelper.getPublicReference().document(producId)
                .set(new productUidEtStatu(producId,"non")).addOnSuccessListener(aVoid -> gotoMainActivity()) ;
  }

  private void gotoMainActivity(){
        Intent intent =new Intent(this ,MainActivity.class) ;
        startActivity(intent);
  }
}