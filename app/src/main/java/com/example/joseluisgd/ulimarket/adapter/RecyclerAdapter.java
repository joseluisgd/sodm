package com.example.joseluisgd.ulimarket.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joseluisgd.ulimarket.R;
import com.example.joseluisgd.ulimarket.clases.RespuestaProducto;

import java.util.List;

/**
 * Created by joseluisgd on 12/2/16.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ProductHolder>{
    private List<RespuestaProducto> rptaProducto;

    public RecyclerAdapter(List<RespuestaProducto> rptaProducto) {
        this.rptaProducto = rptaProducto;
    }

    @Override
    public RecyclerAdapter.ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.producto, parent, false);
        return new ProductHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ProductHolder holder, int position) {
        Log.i("RecyclerAdapter",rptaProducto.get(position).toString());
        holder.nombreProducto.setText(rptaProducto.get(position).getName());
        holder.ubicacion.setText(rptaProducto.get(position).getLocation());
        holder.precio.setText(Long.toString(rptaProducto.get(position).getPrice()));

    }

    @Override
    public int getItemCount() {
        return rptaProducto.size();
    }

    public static class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nombreProducto,ubicacion,precio;
        public ProductHolder(View itemView) {
            super(itemView);
            nombreProducto = (TextView) itemView.findViewById(R.id.nombreProducto);
            ubicacion = (TextView) itemView.findViewById(R.id.ubicacion);
            precio = (TextView) itemView.findViewById(R.id.precio);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.i("RecyclerAdapter","Me has dado click!");
        }
    }
}
