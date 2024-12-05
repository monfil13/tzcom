package com.example.tzcom;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InicioFragment extends Fragment {

    private RecyclerView recyclerView;

    public InicioFragment() {
        // Constructor vacío necesario para Fragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        // Inicializar RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Datos de comida y sus imágenes
        String[] comidas = {
                "Tacos Al Pastor", "Tacos Árabes", "Antojitos", "Mariscos", "Comida Corrida"
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

            // Dependiendo de la comida seleccionada, cargar el fragmento correspondiente
            String comidaSeleccionada = comidas[position];
            new Handler().postDelayed(() -> {
                navigateToLocalesFragment(comidaSeleccionada);
            }, 5000); // Simular un delay de 5 segundos
        });

        return view;
    }

    // Mostrar el DialogFragment flotante
    private void showLocationDialog() {
        LocationDialogFragment dialog = new LocationDialogFragment();
        dialog.show(getParentFragmentManager(), "locationDialog");

        // Cerrar el diálogo después de 5 segundos
        new Handler().postDelayed(() -> dialog.dismiss(), 5000);
    }

    // Navegar al LocalesFragment con el tipo de comida seleccionado
    private void navigateToLocalesFragment(String comidaSeleccionada) {
        // Crear el fragmento LocalesFragment con el tipo de comida
        LocalesFragment localesFragment = new LocalesFragment(comidaSeleccionada);

        // Cambiar de fragmento
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, localesFragment);
        transaction.addToBackStack(null);
        transaction.commit();
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

            // Establecer el mensaje si es necesario
            return view;
        }
    }
}
