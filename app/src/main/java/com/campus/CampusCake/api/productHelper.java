package com.campus.CampusCake.api;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class productHelper {
    private static final String PUBLIC_REF="PUBLIC_PRODUCT_REFERENCE";
    private static final String PRODUCT_NAME="GATEAU_COLLECTION";
    public static  final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public  static CollectionReference getPublicReference(){
        return db.collection(PUBLIC_REF) ;
    }
    public  static DocumentReference getGateauCollection(String geteauRoom){
        return db.collection(PRODUCT_NAME).document(geteauRoom);
    }
    public  static CollectionReference getGateauRoom(String geteauRoom){
        return productHelper.getGateauCollection(geteauRoom).collection("maindocument") ;
    }
    public  static DocumentReference getGateauRoomDoc(String geteauRoom){
        return productHelper.getGateauRoom(geteauRoom).document(geteauRoom);
    }
    public  static DocumentReference getGateauDetailRoomDoc(String geteauRoom){
        return productHelper.getGateauRoom(geteauRoom).document("detailGateau");
    }
   //query pour le
    public  static Query getGateauQuery(){
        return productHelper.getPublicReference() ;
    }

}
