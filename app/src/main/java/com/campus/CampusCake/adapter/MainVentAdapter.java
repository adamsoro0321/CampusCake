package com.campus.CampusCake.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.campus.CampusCake.R;
import com.campus.CampusCake.activity.DetailProductActivity;
import com.campus.CampusCake.api.productHelper;
import com.campus.CampusCake.model.ProductPrizeEtDes;
import com.campus.CampusCake.model.productUidEtStatu;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

public class MainVentAdapter extends FirestoreRecyclerAdapter<productUidEtStatu , MainVentAdapter.viewHolder> {

   Context contex ;
    public MainVentAdapter(@NonNull FirestoreRecyclerOptions<productUidEtStatu> options, Context context) {
        super(options);
        this.contex= context ;
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull productUidEtStatu model) {
       getProduct(model.getpId(),holder);

    }
  private  void getProduct(String geteauRoom,viewHolder holder){
      productHelper.getGateauDetailRoomDoc(geteauRoom).get().addOnSuccessListener(documentSnapshot -> {
          ProductPrizeEtDes product =documentSnapshot.toObject(ProductPrizeEtDes.class);
          holder.inflate(product);
          holder.itemView.setOnClickListener(v -> gotoDetaiproduct(product,geteauRoom));
      }) ;
  }

  private void gotoDetaiproduct(ProductPrizeEtDes product,String id){
        Intent i = new Intent(contex, DetailProductActivity.class) ;
        i.putExtra("product_id" ,id);
        i.putExtra("product" ,product) ;
        contex.startActivity(i);
  }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layou ,parent,false) ;
        return new viewHolder(view) ;
    }

    public  class viewHolder extends RecyclerView.ViewHolder {
        TextView name ,description ;
        ImageView img ;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
       name =itemView.findViewById(R.id.name_id);
       description=itemView.findViewById(R.id.descrip_id) ;
       img = itemView.findViewById(R.id.img_id);
        }
        private void inflate( ProductPrizeEtDes product){
            Glide.with(contex).load(product.getImg()).into(img) ;
            name.setText(product.getName());
            description.setText(product.getDescription());
        }
    }
}
