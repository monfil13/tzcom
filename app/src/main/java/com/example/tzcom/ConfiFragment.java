package com.example.tzcom;

import android.content.Intent;
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
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflamos el layout para este fragmento
        View rootView = inflater.inflate(R.layout.fragment_confi, container, false);

        // Inicializamos los elementos del layout
        etiquetaNombre = rootView.findViewById(R.id.etiquetaNombre);
        botonLogout = rootView.findViewById(R.id.botonLogout);
        switchTheme = rootView.findViewById(R.id.switchTheme);

        // Configuración de Google SignIn
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(getActivity(), gso);

        // Mostrar el nombre del usuario si está logueado
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (account != null) {
            String userName = account.getDisplayName();
            etiquetaNombre.setText(userName);
        }

        // Configuración del tema
        boolean isNightMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
        switchTheme.setChecked(isNightMode);

        // Listener para el switch que cambia el tema
        switchTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        // Configuración del botón de logout
        botonLogout.setOnClickListener(v -> signOut());

        return rootView;  // Retornamos la vista inflada
    }

    private void signOut() {
        gsc.signOut().addOnCompleteListener(task -> {
            // Después de cerrar sesión, se regresa a la pantalla de inicio (login)
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();  // Terminamos la actividad actual para que no se pueda regresar
        });
    }
}
