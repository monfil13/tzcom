package com.example.tzcom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InicioFragment extends Fragment {

    public InicioFragment() {
        // Constructor vacío necesario para Fragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        // Inicializar RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Datos de comida y sus imágenes
        String[] comidas = {
                "Tacos al pastor", "Tacos árabes", "Antojitos", "Mariscos", "Comida Corrida"
        };
        int[] imagenes = {
                R.drawable.pastor, R.drawable.arabe, R.drawable.antojitos, R.drawable.mariscos, R.drawable.corrida
        };

        // Crear el adaptador
        ComidaAdapter adapter = new ComidaAdapter(getContext(), comidas, imagenes);
        recyclerView.setAdapter(adapter);

        // Configurar clics en los elementos
        adapter.setOnItemClickListener(position -> {
            // Mostrar el DialogFragment de "Espera...estamos detectando tu ubicación"
            showLocationDialog();
        });

        return view;
    }

    // Mostrar el DialogFragment flotante
    private void showLocationDialog() {
        LocationDialogFragment dialog = new LocationDialogFragment();
        dialog.show(getParentFragmentManager(), "locationDialog");

        // Cerrar el diálogo después de 10 segundos
        new android.os.Handler().postDelayed(() -> dialog.dismiss(), 10000);
    }

    // Clase interna para el DialogFragment que muestra el mensaje
    public static class LocationDialogFragment extends androidx.fragment.app.DialogFragment {

        public LocationDialogFragment() {
            // Constructor vacío necesario para el DialogFragment
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflar el layout del diálogo
            View view = inflater.inflate(R.layout.dialog_location, container, false);

            TextView tvMensaje = view.findViewById(R.id.tvMensaje);
            ImageView ivUbicacion = view.findViewById(R.id.ivUbicacion);

            // Establecer texto y la imagen SVG
            tvMensaje.setText("Espera... estamos detectando tu ubicación");
            ivUbicacion.setImageResource(R.drawable.ubication); // Asegúrate de tener la imagen SVG en drawable

            return view;
        }
    }
}
