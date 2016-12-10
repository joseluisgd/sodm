package com.example.joseluisgd.ulimarket;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joseluisgd.ulimarket.clases.SessionManager;

public class PerfilFragment extends Fragment {
    TextView perfilNombre,perfilCarrera,perfilCorreo,perfilCodigo;
    SessionManager sessionManager;


    public PerfilFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView text = (TextView) getView().findViewById(R.id.perfil_title);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Sneaksfood.ttf");
        text.setTypeface(font);
        sessionManager = new SessionManager();

        perfilNombre = (TextView) getView().findViewById(R.id.perfilNombre);
        perfilCarrera = (TextView) getView().findViewById(R.id.perfilCarrera);
        perfilCorreo = (TextView) getView().findViewById(R.id.perfilCorreo);
        perfilCodigo = (TextView) getView().findViewById(R.id.perfilCodigo);

        perfilNombre.setText("Nombre: " + sessionManager.getPreferences(getActivity(),"name"));
        perfilCarrera.setText("Carrera: " + sessionManager.getPreferences(getActivity(),"car"));
        perfilCorreo.setText("Email: " + sessionManager.getPreferences(getActivity(),"email"));
        perfilCodigo.setText("Codigo: " + sessionManager.getPreferences(getActivity(),"user"));
    }
}
