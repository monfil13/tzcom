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

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            // Si el usuario no está autenticado, redirige al LoginActivity
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
