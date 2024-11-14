package com.example.tzcom;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();  // Firebase Auth instance

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        findViewById(R.id.loginButton).setOnClickListener(v -> loginUser());
        findViewById(R.id.registerButton).setOnClickListener(v -> registerUser());
    }

    // Método para iniciar sesión
    private void loginUser() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(LoginActivity.this, "¡Bienvenido, " + user.getEmail() + "!", Toast.LENGTH_SHORT).show();
                        // Aquí podrías ir a la pantalla principal de tu aplicación
                    } else {
                        Toast.makeText(LoginActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Método para registrar usuario
    private void registerUser() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(LoginActivity.this, "Usuario registrado: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        // Puedes redirigir a otra actividad o a la pantalla principal
                    } else {
                        Toast.makeText(LoginActivity.this, "Error al registrar usuario: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
