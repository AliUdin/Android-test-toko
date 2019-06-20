package com.example.ali.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ali.myapplication.aktivity.Login;
import com.example.ali.myapplication.model.ResponInsert;
import com.example.ali.myapplication.network.ApiService;
import com.example.ali.myapplication.network.RetroClient;
import com.example.ali.myapplication.shared.SharedLogin;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ali.myapplication.helper.FunctionError.cekEditText;
import static com.example.ali.myapplication.helper.FunctionError.getTextEditText;
import static com.example.ali.myapplication.shared.SharedLogin.SP_ALAMAT;
import static com.example.ali.myapplication.shared.SharedLogin.SP_JENKEL;
import static com.example.ali.myapplication.shared.SharedLogin.SP_KATEGORI;
import static com.example.ali.myapplication.shared.SharedLogin.SP_NAMA_USER;
import static com.example.ali.myapplication.shared.SharedLogin.SP_NO_HP;
import static com.example.ali.myapplication.shared.SharedLogin.SP_PASSWORD_USER;

public class Daftar extends AppCompatActivity {
    @BindView(R.id.editNama)
    TextInputEditText editNama;
    @BindView(R.id.textInputLayoutEmail)
    TextInputLayout textInputLayoutEmail;
    @BindView(R.id.editPass)
    TextInputEditText editPass;
    @BindView(R.id.editTextAlmt)
    TextInputEditText editTextAlmt;
    @BindView(R.id.spinner_pengguna)
    Spinner spinnerPengguna;
    String pengguna;
    @BindView(R.id.buttonRegis)
    Button buttonRegis;
    @BindView(R.id.textLog)
    TextView textLog;
    @BindView(R.id.rg_jenkel)
    RadioGroup rgJenkel;
    String Jk;
    @BindView(R.id.laki_laki)
    RadioButton rbLk;
    @BindView(R.id.perempuan)
    RadioButton rbPrem;
    @BindView(R.id.editNoHp)
    TextInputEditText ednoHp;


    AppCompatButton btnRegister;
    SharedLogin sharedLogin;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        ButterKnife.bind(this);

        sharedLogin = new SharedLogin(this);

        progressDialog = new ProgressDialog(Daftar.this);
        //   R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");

        //Spinner
        final ArrayAdapter<CharSequence> adapterStatus = ArrayAdapter.createFromResource(Daftar.this, R.array.pengguna,
                R.layout.support_simple_spinner_dropdown_item);
        spinnerPengguna.setAdapter(adapterStatus);

        //select Sp
        spinnerPengguna.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                pengguna = adapterStatus.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rgJenkel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                Jk = rb.getText().toString();
            }
        });


    }


    private void simpan(final String nama, final String jenkel, final String no_hp, final String password, final String alamat, final String kategori) {
        progressDialog.dismiss();
        ApiService service = RetroClient.getApiService();
        Call<ResponInsert> call = service.insertUser(nama,password,alamat,kategori,jenkel,no_hp);
        call.enqueue(new Callback<ResponInsert>() {
            @Override
            public void onResponse(Call<ResponInsert> call, Response<ResponInsert> response) {
                //cek code apakah sukses atau tidak
                progressDialog.dismiss();
                if (response.body().getCode() == 200) {
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    //simpan di sharef pref
                    //simpan id_user, nama_user, email dan password
                    sharedLogin.saveNamaUser(SP_NAMA_USER, nama);
                    sharedLogin.saveJenkel(SP_JENKEL, jenkel);
                    sharedLogin.saveNoHP(SP_NO_HP, no_hp);
                    sharedLogin.savePassUser(SP_PASSWORD_USER, password);
                    sharedLogin.saveKategori(SP_KATEGORI, kategori);
                    sharedLogin.saveAlamat(SP_ALAMAT, alamat);


//                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponInsert> call, Throwable t) {
                //hilangkan loading
                progressDialog.dismiss();
                Toast.makeText(Daftar.this, "Koneksi Gagal Keserver", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.buttonRegis, R.id.textLog})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonRegis:

                if (cekEditText(editNama)) {
                    editNama.setError("NAMA KOSONG");
                } else if (cekEditText(editPass)) {
                    editPass.setError("PASSWORD KOSONG");
                } else if (cekEditText(editTextAlmt)) {
                    editTextAlmt.setError("ALAMAT KOSONG");
                } else if (cekEditText(ednoHp)) {
                    editTextAlmt.setError("NO_HP KOSONG");
                } else {
                    simpan(getTextEditText(editNama), Jk, getTextEditText(ednoHp), getTextEditText(editPass), getTextEditText(editTextAlmt), pengguna);
                }
                break;
            case R.id.textLog:
                //start the signup aktifity
                Intent intent = new Intent(getApplicationContext(), Login.class);
                // startActivityForResult(Intent, Request_signup)
                startActivity(intent);
                finish();
                break;
        }
    }
}
