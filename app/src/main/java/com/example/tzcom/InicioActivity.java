package com.example.tzcom;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InicioActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        // Inicializamos el BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Configuramos el menú de navegación
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            // Aquí usamos el item ID para decidir qué fragmento cargar
            if (item.getItemId() == R.id.nav_inicio) {
                loadFragment(new InicioFragment());
                return true;
            } else if (item.getItemId() == R.id.nav_resenas) {
                loadFragment(new ResenasFragment());
                return true;
            } else if (item.getItemId() == R.id.nav_favoritos) {
                loadFragment(new FavoritosFragment());
                return true;
            } else if (item.getItemId() == R.id.nav_confi) {
                loadFragment(new ConfiFragment());
                return true;
            }
            return false;
        });

        // Solo cargar el InicioFragment si la actividad es creada por primera vez
        if (savedInstanceState == null) {
            loadFragment(new InicioFragment());  // Cargar el fragment inicial
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment); // Reemplaza el fragmento actual
        transaction.commit(); // Ejecuta la transacción
    }
}
