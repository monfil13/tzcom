package com.example.tzcom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class ConfiFragment extends Fragment {

    private GoogleSignInClient gsc;
    private Button botonLogout;
    private Switch switchTheme;
    private TextView etiquetaNombre;

    public ConfiFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_confi, container, false);

        // Inicialización de los elementos de la UI
        etiquetaNombre = rootView.findViewById(R.id.etiquetaNombre);
        botonLogout = rootView.findViewById(R.id.botonLogout);
        switchTheme = rootView.findViewById(R.id.switchTheme);

        // Configuración de Google SignIn
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(getActivity(), gso);

        // Obtener el usuario logueado
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (account != null) {
            String userName = account.getDisplayName();
            etiquetaNombre.setText(userName);

            // Guardamos el nombre de usuario en SharedPreferences
            saveUserName(userName);  // Guardamos el nombre de usuario
        }

        // Configuración del tema
        boolean isNightMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
        switchTheme.setChecked(isNightMode);

        // Listener para cambiar el tema
        switchTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        // Listener para el botón de logout
        botonLogout.setOnClickListener(v -> signOut());

        return rootView;
    }

    // Método para guardar el nombre del usuario en SharedPreferences
    private void saveUserName(String userName) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPrefs", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", userName);
        editor.apply();  // Usamos apply() para escribir de manera asíncrona
    }

    // Método para cerrar sesión
    private void signOut() {
        gsc.signOut().addOnCompleteListener(task -> {
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();  // Terminamos la actividad actual para que no se pueda regresar
        });
    }
}
