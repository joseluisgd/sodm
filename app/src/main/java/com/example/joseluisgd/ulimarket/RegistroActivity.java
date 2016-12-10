package com.example.joseluisgd.ulimarket;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joseluisgd.ulimarket.clases.Respuesta;
import com.example.joseluisgd.ulimarket.clases.Usuario;
import com.example.joseluisgd.ulimarket.conexion.Conexion;
import com.example.joseluisgd.ulimarket.interfaces.IService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText user;
    private EditText name;
    private EditText car;
    private EditText email;
    private RadioGroup radioSex;
    private RadioButton radioM;
    private RadioButton radioF;
    private String sex="";
    private EditText pwd1;
    private EditText pwd2;
    private Button butRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //TTF SETTEANDO ESTILO DE LETRA
        TextView text = (TextView) findViewById(R.id.register_title);
        Typeface font = Typeface.createFromAsset(getAssets(), "Sneaksfood.ttf");
        text.setTypeface(font);

        //Registrando al Usuario
        user = (EditText) findViewById(R.id.eteCod);
        name = (EditText) findViewById(R.id.eteName);
        car = (EditText) findViewById(R.id.eteCar);
        email = (EditText) findViewById(R.id.eteEmail);
        radioSex = (RadioGroup) findViewById(R.id.sex);
        radioM = (RadioButton) findViewById(R.id.radioButtonMasculino);
        radioF = (RadioButton) findViewById(R.id.radioButtonFemenino);
        pwd1 = (EditText) findViewById(R.id.etePwd);
        pwd2 = (EditText) findViewById(R.id.eteRepeatPwd);


        butRegistrar = (Button) findViewById(R.id.butRegistrar);


            butRegistrar.setOnClickListener(this);



    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

    @Override
    public void onClick(View view) {
        if(user.getText().toString().equalsIgnoreCase("") ||
                name.getText().toString().equalsIgnoreCase("")||
                car.getText().toString().equalsIgnoreCase("")||
                email.getText().toString().equalsIgnoreCase("")||
                pwd1.getText().toString().equalsIgnoreCase("")|
                        pwd2.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Falta llenar datos", Toast.LENGTH_SHORT).show();
        }else{
            //Comprobando contraseñas
            if(!pwd1.getText().toString().equals(pwd2.getText().toString())){
                Toast.makeText(this, "Contraseñas no son iguales!", Toast.LENGTH_SHORT).show();
            }
            //Sexo de la persona
            if(radioF.isChecked()){
                sex = "F";
            }else {
                sex = "M";
            }

            Retrofit retrofit = new Conexion().getConexion();
            IService service = retrofit.create(IService.class);
            Call<Respuesta> registrar = service.registrar(new Usuario(
                    Integer.parseInt(user.getText().toString()),
                    pwd1.getText().toString(),
                    name.getText().toString(),
                    car.getText().toString(),
                    sex,
                    email.getText().toString()
            ));

            registrar.enqueue(new Callback<Respuesta>() {
                @Override
                public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                    Respuesta respuesta = response.body();
                    if(respuesta.getCode() == 1){
                        Intent intent = new Intent(RegistroActivity.this,MainActivity.class);
                        startActivity(intent);

                        Toast.makeText(RegistroActivity.this, respuesta.getMsg(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(RegistroActivity.this, respuesta.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Respuesta> call, Throwable t) {
                    Toast.makeText(RegistroActivity.this, "Problema en la conexión", Toast.LENGTH_SHORT).show();
                }
            });
        }



    }
}

