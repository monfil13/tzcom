package com.example.tzcom;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FavoritosFragment extends Fragment {

    public FavoritosFragment() {
        // Constructor vacío necesario para Fragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        return inflater.inflate(R.layout.fragment_favoritos, container, false);
    }
}
