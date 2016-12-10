package com.example.joseluisgd.ulimarket;

import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joseluisgd.ulimarket.clases.SessionManager;

public class LoginActivity extends AppCompatActivity {
    SessionManager sessionManager;
    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    TextView mnavNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionManager = new SessionManager();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navview);
        //mnavNombre = (TextView) findViewById(R.id.navNombre);

        View view = mNavigationView.getHeaderView(0);
        mnavNombre = (TextView) view.findViewById(R.id.navNombre);
        mnavNombre.setText(sessionManager.getPreferences(this,"name"));

        if(sessionManager.getPreferences(this,"status").equalsIgnoreCase("1")){
            initToolBar();
            mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    item.setChecked(true);
                    // TODO: Manejar la navegacion (cambiar Fragments)

                    boolean fragmentTransaccion = false;
                    Fragment fragment = null;
                    switch (item.getItemId()){
                        case R.id.mPerfil:
                            fragment = new PerfilFragment();
                            fragmentTransaccion = true;
                            break;
                        case R.id.mProductos:
                            fragment = new ProductosFragment();
                            fragmentTransaccion = true;
                            break;
                        case R.id.mVenderProd:
                            fragment = new VenderProductosFragment();
                            fragmentTransaccion = true;
                            break;
                        case R.id.mLogOut:
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            sessionManager.setPreferences(LoginActivity.this,"status","0");
                            sessionManager.setPreferences(LoginActivity.this,"user","");
                            sessionManager.setPreferences(LoginActivity.this,"name","");
                            sessionManager.setPreferences(LoginActivity.this,"car","");
                            sessionManager.setPreferences(LoginActivity.this,"email","");
                            startActivity(intent);
                            overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
                            SystemClock.sleep(1000);
                            break;
                    }

                    Log.i("LoginActivity", "" + fragmentTransaccion);

                    if(fragmentTransaccion) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content_frame, fragment)
                                .commit();

                        item.setChecked(true);
                        getSupportActionBar().setTitle(item.getTitle());
                    }

                    mDrawerLayout.closeDrawers();

                    return true;

                }
            });




        }else{
            Toast.makeText(this, "Ingrese sus credenciales", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }
    public void initToolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Bienvenido "+ sessionManager.getPreferences(this,"name") + " !");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_name);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("LoginActivity",String.valueOf(item.getItemId()));
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
