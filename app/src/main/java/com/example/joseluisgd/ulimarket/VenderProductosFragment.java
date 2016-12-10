package com.example.joseluisgd.ulimarket;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.R;

import com.example.joseluisgd.ulimarket.adapter.RecyclerAdapter;
import com.example.joseluisgd.ulimarket.adapter.RecyclerAdapterVender;
import com.example.joseluisgd.ulimarket.clases.RespuestaProducto;
import com.example.joseluisgd.ulimarket.clases.SessionManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VenderProductosFragment extends Fragment {
    SessionManager sessionManager;
    boolean hasProduct=false;
    List<RespuestaProducto> rptaProducto;

    private RecyclerView mRecyclerView;
    private RecyclerAdapterVender mAdapter;
    private GridLayoutManager mGridLayoutManager;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Context context;

    public VenderProductosFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //LAYOUT = vender_producto_fragment
        View view = inflater.inflate(com.example.joseluisgd.ulimarket.R.layout.fragment_vender_productos, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(com.example.joseluisgd.ulimarket.R.id.my_recycler_view_vender);
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
                    mAdapter = new RecyclerAdapterVender(values,getContext());
                    mRecyclerView.setAdapter(mAdapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }
}
