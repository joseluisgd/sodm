package com.example.joseluisgd.ulimarket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joseluisgd.ulimarket.R;
import com.example.joseluisgd.ulimarket.clases.RespuestaProducto;

import java.util.List;

/**
 * Created by joseluisgd on 12/9/16.
 */

public class RecyclerAdapterVender extends RecyclerView.Adapter<RecyclerAdapterVender.ProductHolderVender>{
    private List<RespuestaProducto> rptaProducto;
    private Context context;

    public RecyclerAdapterVender(List<RespuestaProducto> rptaProducto, Context context) {
        this.rptaProducto = rptaProducto;
        this.context = context;
    }

    @Override
    public RecyclerAdapterVender.ProductHolderVender onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.venderproducto, parent, false);
        return new RecyclerAdapterVender.ProductHolderVender(inflatedView);
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapterVender.ProductHolderVender holder, final int position) {
        Log.i("RecyclerAdapter",rptaProducto.get(position).toString());
        holder.venderNombreProducto.setText(rptaProducto.get(position).getName());
        holder.venderStock.setText(Long.toString(rptaProducto.get(position).getStock()));
        holder.venderPrecio.setText(Long.toString(rptaProducto.get(position).getPrice()));
        holder.venderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Lo intente" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return rptaProducto.size();
    }
    public static class ProductHolderVender extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView venderNombreProducto,venderStock,venderPrecio;
        private Button venderButton;
        public ProductHolderVender(View itemView) {
            super(itemView);
            venderNombreProducto = (TextView) itemView.findViewById(R.id.venderNombreProducto);
            venderStock = (TextView) itemView.findViewById(R.id.venderStock);
            venderPrecio = (TextView) itemView.findViewById(R.id.venderPrecio);
            venderButton = (Button) itemView.findViewById(R.id.venderButton);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.i("RecyclerAdapter","Me has dado click!");
        }
    }
}
