package com.example.ali.myapplication.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ResponUser{

	@SerializedName("code")
	private int code;

	@SerializedName("message")
	private String message;

	@SerializedName("user")
	private List<UserItem> user;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setUser(List<UserItem> user){
		this.user = user;
	}

	public List<UserItem> getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"ResponUser{" + 
			"code = '" + code + '\'' + 
			",message = '" + message + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}
}