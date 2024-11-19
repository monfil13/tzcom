package com.example.tzcom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class InicioActivity extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Button botonLogout;
    TextView etiquetaNombre;
    Switch switchTheme; // Para cambiar de tema

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio); // Inicializa el layout

        // Inicializamos las vistas
        etiquetaNombre = findViewById(R.id.etiquetaNombre);
        botonLogout = findViewById(R.id.botonLogout);
        switchTheme = findViewById(R.id.switchTheme); // Añadimos el switch para cambiar de tema

        // Inicializamos Google SignIn
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        // Obtenemos la cuenta de Google si está firmada
        GoogleSignInAccount act = GoogleSignIn.getLastSignedInAccount(this);
        if (act != null) {
            String nombreUsuario = act.getDisplayName();
            etiquetaNombre.setText(nombreUsuario);
        }

        // Configuramos el switch para alternar el tema
        boolean isNightMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
        switchTheme.setChecked(isNightMode); // Establece el estado inicial del switch

        switchTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Modo oscuro
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                // Modo claro
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        // Configuramos el botón de logout
        botonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    public void signOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(InicioActivity.this, MainActivity.class));
            }
        });
    }
}
