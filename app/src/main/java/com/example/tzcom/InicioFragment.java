package com.example.tzcom;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class InicioFragment extends Fragment {

    private FusedLocationProviderClient fusedLocationClient;
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

            // Detectar ubicación
            getLocation();
        });

        // Inicializar FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        return view;
    }

    // Mostrar el DialogFragment flotante
    private void showLocationDialog() {
        LocationDialogFragment dialog = new LocationDialogFragment();
        dialog.show(getParentFragmentManager(), "locationDialog");

        // Cerrar el diálogo después de 5 segundos
        new Handler().postDelayed(() -> dialog.dismiss(), 5000);
    }

    // Método para obtener la ubicación actual
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Solicitar permisos si no están concedidos
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), location -> {
                    if (location != null) {
                        // Aquí puedes usar la ubicación para realizar otras acciones si es necesario
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        // Puedes agregar aquí lógica adicional para usar la ubicación
                    }
                });
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Verifica si el permiso fue concedido
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, obtener la ubicación
                getLocation();
            } else {
                // Permiso denegado
                Toast.makeText(getContext(), "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
