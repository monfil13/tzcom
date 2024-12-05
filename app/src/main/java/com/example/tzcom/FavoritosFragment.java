package com.example.tzcom;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoritosFragment extends Fragment {

    private RecyclerView recyclerView;
    private LocalesAdapter adapter;
    private ArrayList<Restaurante> listaFavoritos;

    public FavoritosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        // Inicializar el RecyclerView y la lista de favoritos
        recyclerView = view.findViewById(R.id.recyclerViewFavoritos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        listaFavoritos = obtenerFavoritos();  // Método que obtiene la lista de favoritos desde SharedPreferences

        // Crear el adaptador y asignarlo al RecyclerView
        adapter = new LocalesAdapter(listaFavoritos, true); // true porque es el fragmento de favoritos
        recyclerView.setAdapter(adapter);

        return view;
    }

    // Método que obtiene los restaurantes favoritos de SharedPreferences
    private ArrayList<Restaurante> obtenerFavoritos() {
        SharedPreferences prefs = getContext().getSharedPreferences("Favoritos", Context.MODE_PRIVATE);
        ArrayList<Restaurante> favoritos = new ArrayList<>();

        // Buscar los restaurantes favoritos en SharedPreferences
        for (String key : prefs.getAll().keySet()) {
            if (!key.endsWith("_direccion")) {  // Ignoramos las claves que terminan en "_direccion" (asociadas a los restaurantes)
                continue;
            }
            String nombre = key.substring(0, key.indexOf("_"));
            String direccion = prefs.getString(nombre + "_direccion", "");
            String telefono = prefs.getString(nombre + "_telefono", "");
            String horario = prefs.getString(nombre + "_horario", "");
            String dias = prefs.getString(nombre + "_dias", "");
            int imagen = prefs.getInt(nombre + "_imagen", 0);

            favoritos.add(new Restaurante(nombre, direccion, telefono, horario, dias, imagen));
        }

        return favoritos;
    }

    // Método para actualizar el RecyclerView después de eliminar un favorito
    public void actualizarLista() {
        listaFavoritos = obtenerFavoritos();
        adapter.actualizarLista(listaFavoritos); // Este método lo tendrás que implementar en tu adaptador
    }
}
