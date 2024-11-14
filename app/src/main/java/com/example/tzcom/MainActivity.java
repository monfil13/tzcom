package com.example.tzcom;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Verificar si el usuario está autenticado
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            // Si no está autenticado, redirigir al LoginActivity
            startActivity(new Intent(this, LoginActivity.class));
            finish(); // Finalizar esta actividad para evitar que el usuario regrese
        }

        // Manejo del botón de logout
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(v -> {
            // Cerrar sesión
            mAuth.signOut();
            // Redirigir al LoginActivity
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish(); // Terminar esta actividad
        });
    }
}
