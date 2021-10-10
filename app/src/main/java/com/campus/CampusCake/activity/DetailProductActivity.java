package com.campus.CampusCake.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.campus.CampusCake.databinding.ActivityDetailProductBinding;
import com.campus.CampusCake.model.CommandDialog;
import com.campus.CampusCake.model.ProductPrizeEtDes;
import com.like.LikeButton;
import com.like.OnLikeListener;

public class DetailProductActivity extends AppCompatActivity implements CommandDialog.ListenerDialog  {
   private  String productId ;
   private ProductPrizeEtDes product ;
private ActivityDetailProductBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailProductBinding.inflate(getLayoutInflater()) ;
        setContentView(binding.getRoot());
        product= (ProductPrizeEtDes) getIntent().getSerializableExtra("product");
        productId =getIntent().getStringExtra("product_id") ;
        inflate();
        binding.starButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                Toast.makeText(getApplicationContext() ,"vous aimer ca" ,Toast.LENGTH_LONG).show();
            }

            @Override
            public void unLiked(LikeButton likeButton) {

            }
        });
        binding.comandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          showDialog();
            }
        });

    }

    private void inflate(){
        Glide.with(this).load(product.getImg()).into(binding.imgPro) ;
        binding.nameTitle.setText(product.getName());
        binding.prizeTi.setText(product.getPrize() +" FCFA");
        binding.desId.setText(product.getDescription());

    }

    private void showDialog(){
        DialogFragment dialog =new CommandDialog(this) ;
        dialog.show(getSupportFragmentManager(),"command dialog");
    }

    @Override
    public ProductPrizeEtDes getProduct() {
        return this.product ;
    }
}