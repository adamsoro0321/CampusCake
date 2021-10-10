package com.campus.CampusCake.model;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.campus.CampusCake.R ;
import com.campus.CampusCake.databinding.CommandLayoutBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CommandDialog extends DialogFragment {
   private Context context ;
   private ProductPrizeEtDes product ;
   private ListenerDialog listenerDialog ;
   private CommandLayoutBinding binding ;
  public  interface ListenerDialog{
      ProductPrizeEtDes getProduct();
  }
    public CommandDialog(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
         product=this.listenerDialog.getProduct() ;
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity()) ;
        LayoutInflater inflater =getActivity().getLayoutInflater() ;
        binding =CommandLayoutBinding.inflate(inflater) ;
        builder.setView(binding.getRoot())
                .setPositiveButton("valider", (dialog, which) -> {

                }).setNegativeButton("annuler", (dialog, which) -> {

                }) ;
        binding.imgAdd.setOnClickListener(v -> {
           int i=Integer.parseInt(binding.quantiteInDialod.getText().toString())  ;

           binding.quantiteInDialod.setText(Integer.toString(i+1));
            int j =Integer.parseInt(product.getPrize()) ;
            binding.prizInDialod.setText(Integer.toString(j));
        });
        binding.imgMinize.setOnClickListener(v -> {
            int i=Integer.parseInt(binding.quantiteInDialod.getText().toString())  ;
            if (i>0){
                binding.quantiteInDialod.setText(Integer.toString(i-1));
            }

        });
        return builder.create() ;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listenerDialog= (ListenerDialog) context;
        }catch (ClassCastException e){

        }
    }
}
