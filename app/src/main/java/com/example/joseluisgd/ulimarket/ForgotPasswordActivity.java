package com.example.joseluisgd.ulimarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.joseluisgd.ulimarket.clases.Respuesta;
import com.example.joseluisgd.ulimarket.clases.Usuario;
import com.example.joseluisgd.ulimarket.conexion.Conexion;
import com.example.joseluisgd.ulimarket.interfaces.IService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener{
    EditText eteRenovarUser;
    EditText eteRenovarEmail;
    Button butEnviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        eteRenovarEmail = (EditText) findViewById(R.id.eteRenovarEmail);
        butEnviar = (Button) findViewById(R.id.butEnviar);
        eteRenovarUser = (EditText) findViewById(R.id.eteRenovarUser);


            butEnviar.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (eteRenovarEmail.getText().toString().equalsIgnoreCase("") ||
                eteRenovarUser.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Falta llenar datos", Toast.LENGTH_SHORT).show();
        }else{
            Retrofit retrofit = new Conexion().getConexion();
            IService service = retrofit.create(IService.class);
            final Call<Respuesta> forgotPassword = service.forgotPassword(new Usuario(
                    eteRenovarEmail.getText().toString(),
                    Integer.parseInt(eteRenovarUser.getText().toString())
            ));
            Log.i("ForgotPasswordActivity",eteRenovarUser.getText().toString());
            Log.i("ForgotPasswordActivity",eteRenovarEmail.getText().toString());
            forgotPassword.enqueue(new Callback<Respuesta>() {
                @Override
                public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                    Respuesta respuesta = response.body();
                    if(respuesta.getCode()==1){
                        Toast.makeText(ForgotPasswordActivity.this, respuesta.getMsg(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgotPasswordActivity.this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(ForgotPasswordActivity.this, respuesta.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Respuesta> call, Throwable t) {

                }
            });
        }

    }
}
