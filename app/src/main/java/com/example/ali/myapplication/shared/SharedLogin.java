package com.example.ali.myapplication.shared;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class SharedLogin {

    //key
    public static final String SP_LEY = "Login";

    //key value
    public static final String SP_ID_USER = "id_user";
    public static final String SP_NAMA_USER = "nama_user";
    public static final String SP_PASSWORD_USER = "password";
    public static final String SP_KATEGORI = "kategori";
    public static final String SP_SPIN = "spin";
    public static final String SP_JENKEL = "jenkel";
    public static final String SP_NO_HP = "no_hp";
    public static final String SP_ALAMAT = "alamat";
    public static final String SP_SUDAH_LOGIN = "sudah_login";
    public static final String SP_SUDAH_LOGIN2 = "sudah_login2";

    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    //buat sharefpref dari class lain
    public SharedLogin(Context context) {
        //dengan key sampahku
        sp = context.getSharedPreferences(SP_LEY, Context.MODE_PRIVATE);
        this.context = context;
        spEditor = sp.edit();
    }

    public void saveIDUser(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
        //Toast.makeText(context, "data disimpan " + value, Toast.LENGTH_SHORT).show();
    }

    public void saveNamaUser(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
        //Toast.makeText(context, "data disimpan " + value, Toast.LENGTH_SHORT).show();
    }

    public void saveSpin(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
        //Toast.makeText(context, "data disimpan " + value, Toast.LENGTH_SHORT).show();
    }

    public void saveJenkel(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
        //Toast.makeText(context, "data disimpan " + value, Toast.LENGTH_SHORT).show();
    }

    public void saveNoHP(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
        //Toast.makeText(context, "data disimpan " + value, Toast.LENGTH_SHORT).show();
    }
    public void savePassUser(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
        //Toast.makeText(context, "data disimpan " + value, Toast.LENGTH_SHORT).show();
    }

    public void saveKategori(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
        //Toast.makeText(context, "data disimpan " + value, Toast.LENGTH_SHORT).show();
    }
    public void saveAlamat(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
        //Toast.makeText(context, "data disimpan " + value, Toast.LENGTH_SHORT).show();
    }

    public void saveSPBoolean(String keySP, boolean value) {
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }
    public Boolean getSPSudahLogin() {
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }

    public Boolean getSPSudahLogin2() {
        return sp.getBoolean(SP_SUDAH_LOGIN2, false);
    }

    public String getSpin() { return sp.getString(SP_SPIN, "");
    }

    public String getIdUser() { return sp.getString(SP_ID_USER, "");
    }

    public String getNamaUser() { return sp.getString(SP_NAMA_USER, "");
    }

    public String getPasswordUser() { return sp.getString(SP_PASSWORD_USER, "");
    }
    public String getKategori() { return sp.getString(SP_KATEGORI, "");
    }
}
