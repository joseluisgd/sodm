package com.example.joseluisgd.ulimarket;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joseluisgd.ulimarket.clases.Respuesta;
import com.example.joseluisgd.ulimarket.clases.SessionManager;
import com.example.joseluisgd.ulimarket.clases.Usuario;
import com.example.joseluisgd.ulimarket.conexion.Conexion;
import com.example.joseluisgd.ulimarket.interfaces.IService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText user;
    private EditText pwd;
    private Button butLogin;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TTF SETTEANDO ESTILO DE LETRA
        TextView text = (TextView) findViewById(R.id.login_title);
        Typeface font = Typeface.createFromAsset(getAssets(), "Sneaksfood.ttf");
        text.setTypeface(font);
        //Llenando datos
        user = (EditText) findViewById(R.id.eteUser);
        pwd = (EditText) findViewById(R.id.etePassword);
        butLogin = (Button) findViewById(R.id.butLogin);


            butLogin.setOnClickListener(this);




    }
    public void registrar(View view){
        Intent intent = new Intent(this,RegistroActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }

    public void forgotPassword(View view){
        Intent intent = new Intent(this,ForgotPasswordActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(user.getText().toString().equalsIgnoreCase("")||
                pwd.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Faltan llenar datos", Toast.LENGTH_SHORT).show();
        }else{
            sessionManager = new SessionManager();
            Retrofit retrofit = new Conexion().getConexion();
            IService service = retrofit.create(IService.class);
            final Call<Respuesta> login = service.login(new Usuario(
                    Integer.parseInt(
                            user.getText().toString()),
                    pwd.getText().toString()
            ));

            login.enqueue(new Callback<Respuesta>() {
                @Override
                public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                    Log.i("MainActivity","Ok");
                    Respuesta respuesta=response.body();
                    if(respuesta.getCode()==0){
                        Toast.makeText(MainActivity.this, respuesta.getMsg(), Toast.LENGTH_SHORT).show();
                    }else{
                    /*{
                        "name": data.child("name").val(),
                        "user": data.key,
                        "car": data.child("car").val()
                    }*/
                        sessionManager.setPreferences(MainActivity.this,"status","1");
                        sessionManager.setPreferences(MainActivity.this,"user",String.valueOf(user.getText().toString()));
                        sessionManager.setPreferences(MainActivity.this,"name",respuesta.getName());
                        sessionManager.setPreferences(MainActivity.this,"car",respuesta.getCar());
                        sessionManager.setPreferences(MainActivity.this,"email",respuesta.getEmail());

                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                        Toast.makeText(MainActivity.this, "Bienvenido " + respuesta.getName(), Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<Respuesta> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Problema en la conexión", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}
