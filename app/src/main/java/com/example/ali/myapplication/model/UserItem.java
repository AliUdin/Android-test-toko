package com.example.ali.myapplication.model;


import com.google.gson.annotations.SerializedName;


public class UserItem{

	@SerializedName("password")
	private String password;

	@SerializedName("nama")
	private String nama;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("kategori")
	private String kategori;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("jenkel")
	private String jenkel;

	@SerializedName("alamat")
	private String alamat;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setNoHp(String noHp){
		this.noHp = noHp;
	}

	public String getNoHp(){
		return noHp;
	}

	public void setKategori(String kategori){
		this.kategori = kategori;
	}

	public String getKategori(){
		return kategori;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setJenkel(String jenkel){
		this.jenkel = jenkel;
	}

	public String getJenkel(){
		return jenkel;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	@Override
 	public String toString(){
		return 
			"UserItem{" + 
			"password = '" + password + '\'' + 
			",nama = '" + nama + '\'' + 
			",no_hp = '" + noHp + '\'' + 
			",kategori = '" + kategori + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",jenkel = '" + jenkel + '\'' + 
			",alamat = '" + alamat + '\'' + 
			"}";
		}
}