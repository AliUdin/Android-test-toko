package com.example.ali.myapplication.aktivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.ali.myapplication.Daftar;
import com.example.ali.myapplication.R;
import com.example.ali.myapplication.model.ResponUser;
import com.example.ali.myapplication.model.UserItem;
import com.example.ali.myapplication.network.ApiService;
import com.example.ali.myapplication.network.RetroClient;
import com.example.ali.myapplication.shared.SharedLogin;
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import java.util.List;

import static com.example.ali.myapplication.helper.FunctionError.cekEditText;
import static com.example.ali.myapplication.helper.FunctionError.setErrorEditText;
import static com.example.ali.myapplication.shared.SharedLogin.SP_ID_USER;
import static com.example.ali.myapplication.shared.SharedLogin.SP_NAMA_USER;
import static com.example.ali.myapplication.shared.SharedLogin.SP_PASSWORD_USER;
import static com.example.ali.myapplication.shared.SharedLogin.SP_SPIN;
import static com.example.ali.myapplication.shared.SharedLogin.SP_SUDAH_LOGIN;
import static com.example.ali.myapplication.shared.SharedLogin.SP_SUDAH_LOGIN2;



public class Login extends AppCompatActivity {

    @BindView(R.id.editTextNama)
    EditText edtNama;
    @BindView(R.id.editTextPassword)
    ShowHidePasswordEditText edtPassword2;
    @BindView(R.id.spinner_pengguna)
    Spinner spinPengguna;
    String pengguna;
    @BindView(R.id.textRegister)
    TextView Register;

    @BindView(R.id.buttonLogin)
    AppCompatButton btnLogin;
    SharedLogin sharedLogin;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sharedLogin = new SharedLogin(this);

        progressDialog = new ProgressDialog(Login.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");

        //cek apakah user sudah login
        if (sharedLogin.getSPSudahLogin()) {
            if (sharedLogin.getSPSudahLogin2()) {
                if (sharedLogin.getSpin().equalsIgnoreCase("pembeli")){
                    startActivity(new Intent(Login.this, Pembeli.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }else {
                    startActivity(new Intent(Login.this, Pelapak.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
            }
            //cache
            edtNama.setText(sharedLogin.getNamaUser());
            edtPassword2.setText(sharedLogin.getPasswordUser());

        }

        //Spinner
        final ArrayAdapter<CharSequence> adapterStatus = ArrayAdapter.createFromResource(Login.this, R.array.pengguna,
                R.layout.support_simple_spinner_dropdown_item);
        spinPengguna.setAdapter(adapterStatus);

        //select Sp
        spinPengguna.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                pengguna = adapterStatus.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @OnClick({R.id.buttonLogin, R.id.textRegister})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonLogin:
                if (cekEditText(edtNama)) {
                    edtNama.setError("NAMA KOSONG");
                } else if (TextUtils.isEmpty(edtPassword2.getText().toString())) {
                    setErrorEditText(edtPassword2, "Masukkan Password");
                } else {
                    progressDialog.show();
                    login(edtNama.getText().toString(), edtPassword2.getText().toString(), pengguna);

                }
                break;

            case R.id.textRegister:
                //start the signup aktifity
                Intent intent = new Intent(getApplicationContext(), Daftar.class);
                // startActivityForResult(Intent, Request_signup)
                startActivity(intent);
                finish();
                break;
        }
    }

    private void login(String nama_user, String password, String kategori) {
        //eksekusi
        ApiService service = RetroClient.getApiService();
        Call<ResponUser> login = service.login_user(nama_user,password,kategori);
        login.enqueue(new Callback<ResponUser>() {
            @Override
            public void onResponse(Call<ResponUser> call, Response<ResponUser> response) {
                //cek code apakah sukses atau tidak
                progressDialog.dismiss();
                if (response.body().getCode() == 200) {
                    Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    List<UserItem> dataUser = response.body().getUser();
                    //simpan di sharef pref
                    //simpan id_user, nama_user, email dan password

                    sharedLogin.saveIDUser(SP_ID_USER, dataUser.get(0).getIdUser());
                    sharedLogin.saveNamaUser(SP_NAMA_USER, dataUser.get(0).getNama());
                    sharedLogin.savePassUser(SP_PASSWORD_USER, edtPassword2.getText().toString());
                    //buat cache
                    sharedLogin.saveSPBoolean(SP_SUDAH_LOGIN, true);
                    //buat cache
                    sharedLogin.saveSpin(SP_SPIN,dataUser.get(0).getKategori());
                    //cek login
                    sharedLogin.saveSPBoolean(SP_SUDAH_LOGIN2, true);

                   if (pengguna.equalsIgnoreCase("pembeli")){
                       startActivity(new Intent(Login.this, Pembeli.class)
                                         .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                   }else {
                       startActivity(new Intent(Login.this, Pelapak.class)
                               .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                   }
                    //buka home jika berhasil login

                    //     startActivity(new Intent(Login.this, MainActivity.class)
                    //          .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    //hancurkan activity
                    finish();
                } else {
                    Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponUser> call, Throwable t) {
                //hilangkan loading
                progressDialog.dismiss();
                Toast.makeText(Login.this, "Koneksi Gagal Keserver", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
