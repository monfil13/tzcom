package com.example.tzcom;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ResenasFragment extends Fragment {

    private FirebaseFirestore db;
    private EditText nombreLocalEditText, contenidoEditText;
    private Button guardarResenaButton;
    private RecyclerView recyclerView;
    private ResenasAdapter resenasAdapter;
    private List<String> arregloLocalesList;

    public ResenasFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflamos el layout del fragmento
        View rootView = inflater.inflate(R.layout.fragment_resenas, container, false);

        // Inicializamos Firestore
        db = FirebaseFirestore.getInstance();

        // Inicializamos los elementos de la UI
        nombreLocalEditText = rootView.findViewById(R.id.nombreLocalEditText);
        contenidoEditText = rootView.findViewById(R.id.contenidoEditText);
        guardarResenaButton = rootView.findViewById(R.id.guardarResenaButton);
        recyclerView = rootView.findViewById(R.id.recyclerView);

        // Configuramos el RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Cargamos el arreglo de nombres de locales
        cargarArregloLocales();

        // Configuramos el botón para guardar la reseña
        guardarResenaButton.setOnClickListener(v -> guardarResena());

        // Cargar las reseñas desde Firestore
        cargarResenas();

        return rootView;
    }

    private void cargarArregloLocales() {
        // Obtenemos el arreglo de nombres de locales desde los recursos
        String[] arregloLocales = getResources().getStringArray(R.array.arregloLocales);
        arregloLocalesList = new ArrayList<>();
        for (String local : arregloLocales) {
            arregloLocalesList.add(local.trim().toLowerCase());  // Convertimos a minúsculas para la comparación
        }
    }

    private void guardarResena() {
        // Obtenemos el nombre del local y el contenido de la reseña
        String nombreLocal = nombreLocalEditText.getText().toString().trim().toLowerCase(); // Convertimos a minúsculas para comparar
        String contenido = contenidoEditText.getText().toString();

        // Verificamos si el nombre del local es válido
        if (!arregloLocalesList.contains(nombreLocal)) {
            // Si no está en el arreglo, mostramos un mensaje y no guardamos la reseña
            Toast.makeText(getActivity(), "El nombre del local es incorrecto", Toast.LENGTH_SHORT).show();
            return;
        }

        // Recuperamos el nombre del usuario desde SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPrefs", getActivity().MODE_PRIVATE);
        String usuario = sharedPreferences.getString("userName", "UsuarioEjemplo");  // Default a "UsuarioEjemplo" si no está disponible

        // Verificamos si el nombre de usuario fue recuperado correctamente
        if (usuario.equals("UsuarioEjemplo")) {
            Toast.makeText(getActivity(), "No se ha encontrado el nombre de usuario", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtenemos la fecha actual
        String fecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        // Verificamos que los campos no estén vacíos
        if (!nombreLocal.isEmpty() && !contenido.isEmpty()) {
            // Creamos un mapa con los datos de la reseña
            Map<String, Object> reseña = new HashMap<>();
            reseña.put("nombreLocal", nombreLocal);
            reseña.put("contenido", contenido);
            reseña.put("usuario", usuario);  // Usamos el nombre de usuario recuperado
            reseña.put("fecha", fecha);

            // Guardamos la reseña en la colección "resenas" en Firestore
            db.collection("resenas")
                    .add(reseña)
                    .addOnSuccessListener(documentReference -> {
                        // Si la reseña se guarda con éxito
                        Toast.makeText(getActivity(), "Reseña guardada", Toast.LENGTH_SHORT).show();
                        cargarResenas();  // Recargamos las reseñas
                    })
                    .addOnFailureListener(e -> {
                        // Si ocurre un error al guardar la reseña
                        Toast.makeText(getActivity(), "Error al guardar reseña", Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(getActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    private void cargarResenas() {
        // Cargar todas las reseñas desde Firestore
        db.collection("resenas")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null) {
                            // Crear el adaptador con los resultados de Firestore
                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPrefs", getActivity().MODE_PRIVATE);
                            String usuario = sharedPreferences.getString("userName", "UsuarioEjemplo");  // Default a "UsuarioEjemplo"
                            resenasAdapter = new ResenasAdapter(querySnapshot.getDocuments(), usuario);
                            recyclerView.setAdapter(resenasAdapter);
                        }
                    } else {
                        Toast.makeText(getActivity(), "Error al cargar reseñas", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
