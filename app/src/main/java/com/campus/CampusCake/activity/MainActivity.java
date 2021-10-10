package com.campus.CampusCake.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.campus.CampusCake.R;
import com.campus.CampusCake.adapter.MainVentAdapter;
import com.campus.CampusCake.api.productHelper;
import com.campus.CampusCake.databinding.ActivityMainBinding;
import com.campus.CampusCake.databinding.SheetShowOptionBinding;
import com.campus.CampusCake.model.productUidEtStatu;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener    {
   private BottomSheetDialog bottomSheetDialog ;
   private ActivityMainBinding mainBinding ;
   private SheetShowOptionBinding sheetShowOptionBinding ;
   private Query query ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater()) ;
        setContentView(mainBinding.getRoot());
       mainBinding.bottomAppBar.setOnNavigationItemSelectedListener(this);

      query= productHelper.getGateauQuery() ;
      inflateAdapter();

    }

    private void inflateAdapter(){
        FirestoreRecyclerOptions<productUidEtStatu> options = new FirestoreRecyclerOptions.Builder<productUidEtStatu>()
                .setQuery(query ,productUidEtStatu.class)
                .setLifecycleOwner(this)
                .build() ;
        MainVentAdapter adapter =new MainVentAdapter(options,this) ;
        mainBinding.mainRecv.setAdapter(adapter);
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2,1);
        mainBinding.mainRecv.setLayoutManager(manager);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.show_sheet_item :
                showSheet();
                break;
        }
        return true;

    }

   private void showSheet(){
        sheetShowOptionBinding=SheetShowOptionBinding.inflate(getLayoutInflater());
        bottomSheetDialog =new BottomSheetDialog(this) ;
        bottomSheetDialog.setContentView(sheetShowOptionBinding.getRoot());
        bottomSheetDialog.show();

        sheetShowOptionBinding.vende.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAdd();
            }
        });
   }

   private void goToAdd(){
        Intent i=new Intent(this ,venteGateauActivity.class) ;
        startActivity(i);
   }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.head_menu ,menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_pro :
                gotoLogin();
                break;
        }
        return true;
    }
    private void gotoLogin(){
        Intent i =new Intent(this ,LoginActivity.class) ;
        startActivity(i);
    }
}