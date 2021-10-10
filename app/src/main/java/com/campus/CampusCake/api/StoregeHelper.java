package com.campus.CampusCake.api;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class StoregeHelper {
   private  static  final String IMAGE_PRODUCT="PRODUCT_IMG_ROOM" ;
   private static  final   StorageReference getInstance(){
        FirebaseStorage storage = FirebaseStorage.getInstance() ;
        return  storage.getReference();
   }
    public  static final StorageReference getImgRef(String productId){
      return StoregeHelper.getInstance().child(productId) ;
    }
}
