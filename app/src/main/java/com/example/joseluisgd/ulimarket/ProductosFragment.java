package com.example.joseluisgd.ulimarket;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.joseluisgd.ulimarket.adapter.RecyclerAdapter;
import com.example.joseluisgd.ulimarket.clases.Producto;
import com.example.joseluisgd.ulimarket.clases.Respuesta;
import com.example.joseluisgd.ulimarket.clases.RespuestaProducto;
import com.example.joseluisgd.ulimarket.clases.SessionManager;
import com.example.joseluisgd.ulimarket.clases.Usuario;
import com.example.joseluisgd.ulimarket.conexion.Conexion;
import com.example.joseluisgd.ulimarket.interfaces.IService;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductosFragment extends Fragment {
    SessionManager sessionManager;
    boolean hasProduct=false;
    TextInputEditText eteProductoNombre,eteProductoStock,eteProductoPrecio,eteUbicacion;
    Button butRegistrarProducto;
    List<RespuestaProducto> rptaProducto;

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private GridLayoutManager mGridLayoutManager;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Context context;


    public ProductosFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        //LAYOUT = fragment_productos
        Log.i("ProductosFragment","hasProduct: " + hasProduct);
            View view = inflater.inflate(R.layout.fragment_productos, container, false);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
            mRecyclerView.setHasFixedSize(true);
            // use a linear layout manager
            mGridLayoutManager = new GridLayoutManager(getContext(), 1);
            mRecyclerView.setLayoutManager(mGridLayoutManager);
            // specify an adapter (see also next example)

            //FIREBASE
            sessionManager = new SessionManager();
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("users/" + sessionManager.getPreferences(getContext(),"user") +  "/products");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    context = getContext();
                    //Log.i("ProductosFragment", dataSnapshot.getValue().toString());
                    if(dataSnapshot.getValue()==null){
                        Toast.makeText(context, "No tiene ningun producto", Toast.LENGTH_SHORT).show();
                    }else{
                        List<RespuestaProducto> values = new ArrayList<RespuestaProducto>();
                        for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                            RespuestaProducto rptaProducto = messageSnapshot.getValue(RespuestaProducto.class);
                            values.add(rptaProducto);

                        }
                        mAdapter = new RecyclerAdapter(values);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            return view;


    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*Letra
        TextView text = (TextView) getView().findViewById(R.id.tviIngresaProducto);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Sneaksfood.ttf");
        text.setTypeface(font);*/


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_addproduct,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_addproduct:
                mostrarAlertDialogAgregarProducto();

        }
        return super.onOptionsItemSelected(item);
    }

    public void mostrarAlertDialogAgregarProducto(){
        //Alert Dialog
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_addproduct, null);
        builder.setView(v);
        //Settear titulo
        TextView text = (TextView) v.findViewById(R.id.title_ingresa_producto);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Sneaksfood.ttf");
        text.setTypeface(font);
        eteProductoNombre = (TextInputEditText) v.findViewById(R.id.eteNombreProducto);
        eteProductoPrecio = (TextInputEditText) v.findViewById(R.id.eteProductoPrecio);
        eteProductoStock = (TextInputEditText) v.findViewById(R.id.eteProductoStock);
        eteUbicacion = (TextInputEditText) v.findViewById(R.id.eteUbicacion);
        butRegistrarProducto = (Button) v.findViewById(R.id.butProductoRegistrar);
        final AlertDialog alertDialog = builder.show();



            butRegistrarProducto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (eteProductoPrecio.getText().toString().equalsIgnoreCase("") ||
                            eteProductoNombre.getText().toString().equalsIgnoreCase("") ||
                            eteProductoStock.getText().toString().equalsIgnoreCase("") ||
                            eteUbicacion.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getContext(), "Llenar datos", Toast.LENGTH_SHORT).show();

                    } else {
                        Retrofit retrofit = new Conexion().getConexion();
                        IService service = retrofit.create(IService.class);
                        final Call<Respuesta> addProducto = service.addProducto(
                                new Producto(sessionManager.getPreferences(getContext(), "user"),
                                        eteProductoNombre.getText().toString(),
                                        Integer.parseInt(eteProductoPrecio.getText().toString()),
                                        eteUbicacion.getText().toString(),
                                        Integer.parseInt(eteProductoStock.getText().toString()))
                        );
                        addProducto.enqueue(new Callback<Respuesta>() {
                            @Override
                            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                                Respuesta respuesta = response.body();
                                Toast.makeText(getActivity(), respuesta.getMsg(), Toast.LENGTH_SHORT).show();
                                if (!hasProduct) {
                                    hasProduct = true;
                                }

                            }

                            @Override
                            public void onFailure(Call<Respuesta> call, Throwable t) {
                                Toast.makeText(getActivity(), "Problemas en la conexion", Toast.LENGTH_SHORT).show();

                            }
                        });
                        alertDialog.dismiss();
                    }

                }
            });
    }

}


