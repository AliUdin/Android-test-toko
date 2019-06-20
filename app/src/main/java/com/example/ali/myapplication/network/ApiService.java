package com.example.ali.myapplication.network;



import com.example.ali.myapplication.model.ResponInsert;
import com.example.ali.myapplication.model.ResponNoUrut;
import com.example.ali.myapplication.model.ResponUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    //read
    @FormUrlEncoded
    @POST("read/login_user.php")
    Call<ResponUser>login_user(@Field("nama_user") String nama_user,  @Field("password") String password, @Field("kategori") String kategori );

    //read
    @FormUrlEncoded
    @POST("read/no_user.php")
    Call<ResponNoUrut>getNoUrut();
    //create
    @FormUrlEncoded
    @POST("create/user.php")
    Call<ResponInsert> insertUser(@Field("nama") String nama_user, @Field("jenkel") String jenkel, @Field("no_hp") String no_hp, @Field("password") String password, @Field("alamat") String alamat, @Field("kategori") String kategori);

    }