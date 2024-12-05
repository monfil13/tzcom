package com.example.tzcom;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LocalesFragment extends Fragment {

    private RecyclerView recyclerView;
    private LocalesAdapter adapter;
    private ArrayList<Restaurante> listaLocales;
    private OnFavoritosActualizadosListener mListener;  // Listener para notificar a FavoritosFragment

    public LocalesFragment() {
        // Constructor vacío
    }

    // Constructor que recibe el tipo de comida
    public LocalesFragment(String tipoComida) {
        listaLocales = obtenerLocales(tipoComida);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_locales, container, false);

        // Inicializar RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewLocales);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializar y configurar el adaptador
        adapter = new LocalesAdapter(listaLocales, false);  // 'false' porque no son favoritos al principio
        recyclerView.setAdapter(adapter);

        // Configuramos el listener para que la actividad pueda recibir actualizaciones
        if (getActivity() instanceof OnFavoritosActualizadosListener) {
            mListener = (OnFavoritosActualizadosListener) getActivity();
        }

        return view;
    }

    // Método para obtener los locales según el tipo de comida
    private ArrayList<Restaurante> obtenerLocales(String tipoComida) {
        ArrayList<Restaurante> locales = new ArrayList<>();
        int imagenTipoComida = 0;  // El valor predeterminado es 0, ya que no necesitamos un valor inicial

        // Imprimir el valor de tipoComida para depuración
        Log.d("LocalesFragment", "Tipo de comida recibido: " + tipoComida);

        // Determinar qué imagen usar según el tipo de comida
        switch (tipoComida) {
            case "Tacos Al Pastor":
                imagenTipoComida = R.drawable.pastor;
                break;
            case "Tacos Árabes":
                imagenTipoComida = R.drawable.arabe;
                break;
            case "Mariscos":
                imagenTipoComida = R.drawable.mariscos;
                break;
            case "Comida Corrida":
                imagenTipoComida = R.drawable.corrida;
                break;
            case "Antojitos":
                imagenTipoComida = R.drawable.antojitos;
                break;
            default:
                Log.e("LocalesFragment", "Tipo de comida no válido: " + tipoComida);
                break;
        }

        if (imagenTipoComida == 0) {
            throw new IllegalArgumentException("Imagen no disponible para el tipo de comida: " + tipoComida);
        }
        if (tipoComida.equals("Tacos Al Pastor")) {
            locales.add(new Restaurante("La Piñita Pastorera", "Avenida Emiliano Zapata", "555-1101", "9:00 AM - 7:00 PM", "Lunes a Domingo", R.drawable.pastor));
            locales.add(new Restaurante("Salsas y Sazonadores", "Calle Frida Kahlo", "555-1102", "10:00 AM - 8:00 PM", "Martes a Domingo", R.drawable.pastor));
            locales.add(new Restaurante("El Cilantro Mágico", "Calle Pancho Villa", "555-1103", "9:30 AM - 6:30 PM", "Lunes a Sábado", R.drawable.pastor));
            locales.add(new Restaurante("La Piña del Sabor", "Avenida Zapata", "555-1104", "10:00 AM - 9:00 PM", "Lunes a Domingo", R.drawable.pastor));
            locales.add(new Restaurante("Salsas y Cebollas", "Calle El Chavo", "555-1105", "11:00 AM - 10:00 PM", "Martes a Domingo", R.drawable.pastor));
            locales.add(new Restaurante("La Salsa Roja de La Casa", "Avenida Benito Juárez", "555-1106", "8:00 AM - 6:00 PM", "Lunes a Sábado", R.drawable.pastor));
            locales.add(new Restaurante("El Pastor Con Todo", "Calle Cantinflas", "555-1107", "9:00 AM - 7:00 PM", "Lunes a Domingo", R.drawable.pastor));
            locales.add(new Restaurante("El Cilantro En Fuego", "Calle Lucha Libre", "555-1108", "12:00 PM - 8:00 PM", "Lunes a Viernes", R.drawable.pastor));
            locales.add(new Restaurante("La Piña Sazonada", "Avenida El Sol", "555-1109", "10:30 AM - 9:00 PM", "Martes a Domingo", R.drawable.pastor));
            locales.add(new Restaurante("La Cebolla En La Plancha", "Calle El Piporro", "555-1110", "9:00 AM - 8:00 PM", "Lunes a Sábado", R.drawable.pastor));
            locales.add(new Restaurante("La Salsa Caliente", "Calle Sor Juana Inés de la Cruz", "555-1111", "10:00 AM - 7:00 PM", "Lunes a Viernes", R.drawable.pastor));
            locales.add(new Restaurante("El Pastor y Sus Amigos", "Calle Diego Rivera", "555-1112", "8:30 AM - 9:00 PM", "Martes a Domingo", R.drawable.pastor));
            locales.add(new Restaurante("Cilantro y Picante", "Calle Agustín Lara", "555-1113", "9:00 AM - 8:00 PM", "Lunes a Domingo", R.drawable.pastor));
            locales.add(new Restaurante("La Fiesta del Pastor", "Avenida Mario Molina", "555-1114", "10:00 AM - 7:30 PM", "Martes a Sábado", R.drawable.pastor));
            locales.add(new Restaurante("Cebolla y Sazón", "Calle Chespirito", "555-1115", "9:00 AM - 7:00 PM", "Lunes a Viernes", R.drawable.pastor));
            locales.add(new Restaurante("Salsa y Piña", "Calle El Santo", "555-1116", "10:00 AM - 9:00 PM", "Lunes a Domingo", R.drawable.pastor));
            locales.add(new Restaurante("La Sazonada", "Calle Doña Florinda", "555-1117", "8:30 AM - 6:00 PM", "Lunes a Sábado", R.drawable.pastor));
            locales.add(new Restaurante("El Cilantro Sabroso", "Avenida Los Pinos", "555-1118", "11:00 AM - 8:00 PM", "Martes a Domingo", R.drawable.pastor));
            locales.add(new Restaurante("La Salsa en Su Punto", "Calle La Loba", "555-1119", "9:30 AM - 7:30 PM", "Lunes a Viernes", R.drawable.pastor));
            locales.add(new Restaurante("Piña Asada y Pastor", "Calle El Triunfo", "555-1120", "10:00 AM - 9:00 PM", "Lunes a Domingo", R.drawable.pastor));

        }

        // Si el tipo de comida es Tacos Árabes
        else if (tipoComida.equals("Tacos Árabes")) {
            locales.add(new Restaurante("Tacos Árabes El Halal", "Avenida Benito Juárez", "555-1201", "9:00 AM - 7:00 PM", "Lunes a Domingo", R.drawable.arabe));
            locales.add(new Restaurante("Tacos Árabes La Mezcla", "Calle Guerrero", "555-1202", "10:00 AM - 8:00 PM", "Martes a Domingo", R.drawable.arabe));
            locales.add(new Restaurante("Tacos Árabes La Esquina del Shawarma", "Calle 5 de Febrero", "555-1203", "9:30 AM - 6:30 PM", "Lunes a Sábado", R.drawable.arabe));
            locales.add(new Restaurante("Tacos Árabes El Zoco", "Avenida del Sol", "555-1204", "10:00 AM - 9:00 PM", "Lunes a Domingo", R.drawable.arabe));
            locales.add(new Restaurante("Tacos Árabes Sabe a Cordero", "Calle Hidalgo", "555-1205", "11:00 AM - 10:00 PM", "Martes a Domingo", R.drawable.arabe));
            locales.add(new Restaurante("Tacos Árabes El Rincón Árabe", "Avenida Reforma", "555-1206", "8:00 AM - 6:00 PM", "Lunes a Sábado", R.drawable.arabe));
            locales.add(new Restaurante("Tacos Árabes La Casa del Shawarma", "Calle del Árbol", "555-1207", "9:00 AM - 7:00 PM", "Lunes a Domingo", R.drawable.arabe));
            locales.add(new Restaurante("Tacos Árabes El Oasis", "Calle de la Luna", "555-1208", "12:00 PM - 8:00 PM", "Lunes a Viernes", R.drawable.arabe));
            locales.add(new Restaurante("Tacos Árabes La Duna", "Avenida Los Pinos", "555-1209", "10:30 AM - 9:00 PM", "Martes a Domingo", R.drawable.arabe));
            locales.add(new Restaurante("Tacos Árabes El Tajín", "Calle El Piporro", "555-1210", "9:00 AM - 8:00 PM", "Lunes a Sábado", R.drawable.arabe));
            locales.add(new Restaurante("Tacos Árabes La Llama", "Calle La Paz", "555-1211", "10:00 AM - 7:00 PM", "Lunes a Viernes", R.drawable.arabe));
            locales.add(new Restaurante("Tacos Árabes El Jabali", "Calle 16 de Septiembre", "555-1212", "8:30 AM - 9:00 PM", "Martes a Domingo", R.drawable.arabe));
            locales.add(new Restaurante("Tacos Árabes El Zaatar", "Avenida Insurgentes", "555-1213", "9:00 AM - 8:00 PM", "Lunes a Domingo", R.drawable.arabe));
            locales.add(new Restaurante("Tacos Árabes La Cueva del Shawarma", "Calle Agustín Lara", "555-1214", "10:00 AM - 7:30 PM", "Martes a Sábado", R.drawable.arabe));
            locales.add(new Restaurante("Tacos Árabes El Cedro", "Calle La Loba", "555-1215", "9:00 AM - 7:00 PM", "Lunes a Viernes", R.drawable.arabe));
            locales.add(new Restaurante("Tacos Árabes El Rastro", "Calle La Cima", "555-1216", "10:00 AM - 9:00 PM", "Lunes a Domingo", R.drawable.arabe));
            locales.add(new Restaurante("Tacos Árabes La Pita", "Calle Zócalo", "555-1217", "8:30 AM - 6:00 PM", "Lunes a Sábado", R.drawable.arabe));
            locales.add(new Restaurante("Tacos Árabes El Sabor del Medio Oriente", "Avenida Reforma", "555-1218", "11:00 AM - 8:00 PM", "Martes a Domingo", R.drawable.arabe));
            locales.add(new Restaurante("Tacos Árabes El Bastión", "Calle de los Reyes", "555-1219", "9:30 AM - 7:30 PM", "Lunes a Viernes", R.drawable.arabe));
            locales.add(new Restaurante("Tacos Árabes La Primavera", "Calle 20 de Noviembre", "555-1220", "10:00 AM - 9:00 PM", "Lunes a Domingo", R.drawable.arabe));
        }
        // Si el tipo de comida es Mariscos
        else if (tipoComida.equals("Mariscos")) {
            locales.add(new Restaurante("Mariscos El Delfín", "Calle Ricardo Flores Magón", "555-1301", "9:00 AM - 7:00 PM", "Lunes a Sábado", R.drawable.mariscos));
            locales.add(new Restaurante("Mariscos La Ola", "Avenida Bahía de Banderas", "555-1302", "10:00 AM - 8:00 PM", "Martes a Domingo", R.drawable.mariscos));
            locales.add(new Restaurante("Mariscos El Pez Vela", "Calle Luis Donaldo Colosio", "555-1303", "8:00 AM - 6:00 PM", "Lunes a Viernes", R.drawable.mariscos));
            locales.add(new Restaurante("Mariscos La Perla", "Avenida del Mar", "555-1304", "10:30 AM - 9:00 PM", "Lunes a Domingo", R.drawable.mariscos));
            locales.add(new Restaurante("Mariscos El Tiburón", "Calle Veracruz", "555-1305", "11:00 AM - 10:00 PM", "Martes a Sábado", R.drawable.mariscos));
            locales.add(new Restaurante("Mariscos El Camarón", "Calle El Puerto", "555-1306", "9:30 AM - 7:00 PM", "Lunes a Sábado", R.drawable.mariscos));
            locales.add(new Restaurante("Mariscos Los Pescadores", "Avenida Las Palmas", "555-1307", "10:00 AM - 8:00 PM", "Lunes a Domingo", R.drawable.mariscos));
            locales.add(new Restaurante("Mariscos La Langosta", "Calle Juan Rulfo", "555-1308", "12:00 PM - 10:00 PM", "Martes a Domingo", R.drawable.mariscos));
            locales.add(new Restaurante("Mariscos La Sirenita", "Avenida Playa del Carmen", "555-1309", "9:00 AM - 6:00 PM", "Lunes a Sábado", R.drawable.mariscos));
            locales.add(new Restaurante("Mariscos El Coral", "Calle Los Naranjos", "555-1310", "10:00 AM - 7:00 PM", "Lunes a Domingo", R.drawable.mariscos));
            locales.add(new Restaurante("Mariscos El Bahía", "Calle San José", "555-1311", "9:30 AM - 8:00 PM", "Martes a Domingo", R.drawable.mariscos));
            locales.add(new Restaurante("Mariscos La Tortuga", "Avenida del Sol", "555-1312", "10:30 AM - 9:00 PM", "Lunes a Viernes", R.drawable.mariscos));
            locales.add(new Restaurante("Mariscos La Isla", "Calle La Palma", "555-1313", "11:00 AM - 8:00 PM", "Martes a Domingo", R.drawable.mariscos));
            locales.add(new Restaurante("Mariscos El Atún", "Calle Benito Juárez", "555-1314", "9:00 AM - 7:00 PM", "Lunes a Sábado", R.drawable.mariscos));
            locales.add(new Restaurante("Mariscos La Bahía", "Avenida Santa Fe", "555-1315", "10:00 AM - 8:00 PM", "Lunes a Viernes", R.drawable.mariscos));
            locales.add(new Restaurante("Mariscos El Mero Mero", "Calle Juan Escutia", "555-1316", "9:30 AM - 6:30 PM", "Lunes a Domingo", R.drawable.mariscos));
            locales.add(new Restaurante("Mariscos Los Buzos", "Avenida Los Olivos", "555-1317", "10:00 AM - 7:00 PM", "Martes a Domingo", R.drawable.mariscos));
            locales.add(new Restaurante("Mariscos El Acuario", "Calle del Mar", "555-1318", "11:00 AM - 9:00 PM", "Lunes a Sábado", R.drawable.mariscos));
            locales.add(new Restaurante("Mariscos La Pescadora", "Avenida del Puerto", "555-1319", "8:30 AM - 6:30 PM", "Lunes a Viernes", R.drawable.mariscos));
            locales.add(new Restaurante("Mariscos El Faro", "Calle La Paz", "555-1320", "10:00 AM - 7:00 PM", "Martes a Domingo", R.drawable.mariscos));
        }

        // Si el tipo de comida es Comida Corrida
        else if (tipoComida.equals("Comida Corrida")) {
            locales.add(new Restaurante("Comida Corrida El Recuerdo", "Calle Miguel Hidalgo", "555-1401", "10:00 AM - 6:00 PM", "Lunes a Sábado", R.drawable.corrida));
            locales.add(new Restaurante("Comida Corrida La Comadre", "Avenida Juárez", "555-1402", "9:00 AM - 7:00 PM", "Martes a Domingo", R.drawable.corrida));
            locales.add(new Restaurante("Comida Corrida Los Tres Moles", "Calle Don Antonio", "555-1403", "11:00 AM - 8:00 PM", "Lunes a Sábado", R.drawable.corrida));
            locales.add(new Restaurante("Comida Corrida El Sol", "Avenida Revolución", "555-1404", "10:30 AM - 9:00 PM", "Lunes a Domingo", R.drawable.corrida));
            locales.add(new Restaurante("Comida Corrida El Buen Gusto", "Calle La Nochebuena", "555-1405", "9:30 AM - 6:30 PM", "Lunes a Viernes", R.drawable.corrida));
            locales.add(new Restaurante("Comida Corrida El Rancho", "Avenida Los Arcos", "555-1406", "10:00 AM - 7:00 PM", "Lunes a Domingo", R.drawable.corrida));
            locales.add(new Restaurante("Comida Corrida La Tía", "Calle El Zócalo", "555-1407", "12:00 PM - 8:00 PM", "Martes a Domingo", R.drawable.corrida));
            locales.add(new Restaurante("Comida Corrida Los Abuelos", "Avenida de la Cultura", "555-1408", "11:00 AM - 9:00 PM", "Lunes a Sábado", R.drawable.corrida));
            locales.add(new Restaurante("Comida Corrida El Comal", "Calle Benito Juárez", "555-1409", "9:30 AM - 7:30 PM", "Martes a Viernes", R.drawable.corrida));
            locales.add(new Restaurante("Comida Corrida La Casa del Sabor", "Calle Pino Suárez", "555-1410", "10:00 AM - 7:00 PM", "Lunes a Domingo", R.drawable.corrida));
            locales.add(new Restaurante("Comida Corrida Los Tesoros", "Avenida Centenario", "555-1411", "11:30 AM - 8:00 PM", "Lunes a Sábado", R.drawable.corrida));
            locales.add(new Restaurante("Comida Corrida La Comida de Mamá", "Calle Guerrero", "555-1412", "10:30 AM - 9:00 PM", "Martes a Domingo", R.drawable.corrida));
            locales.add(new Restaurante("Comida Corrida El Padrino", "Avenida Constitución", "555-1413", "9:00 AM - 6:00 PM", "Lunes a Viernes", R.drawable.corrida));
            locales.add(new Restaurante("Comida Corrida La Familia", "Calle Nueva Era", "555-1414", "11:00 AM - 7:30 PM", "Lunes a Sábado", R.drawable.corrida));
            locales.add(new Restaurante("Comida Corrida La Cocina de la Abuela", "Calle Juan Escutia", "555-1415", "9:00 AM - 8:00 PM", "Martes a Domingo", R.drawable.corrida));
            locales.add(new Restaurante("Comida Corrida El Sabor del Pueblo", "Avenida Hidalgo", "555-1416", "10:00 AM - 9:00 PM", "Lunes a Sábado", R.drawable.corrida));
            locales.add(new Restaurante("Comida Corrida El Tenedor", "Calle de la Paz", "555-1417", "12:00 PM - 7:00 PM", "Lunes a Viernes", R.drawable.corrida));
            locales.add(new Restaurante("Comida Corrida Los Hermanos", "Calle Las Palmas", "555-1418", "10:30 AM - 6:30 PM", "Lunes a Domingo", R.drawable.corrida));
            locales.add(new Restaurante("Comida Corrida La Mariscada", "Calle Real de Guadalupe", "555-1419", "11:00 AM - 8:30 PM", "Martes a Sábado", R.drawable.corrida));
            locales.add(new Restaurante("Comida Corrida El Arriero", "Avenida del Valle", "555-1420", "9:30 AM - 7:30 PM", "Lunes a Domingo", R.drawable.corrida));
        }
// Si el tipo de comida es Antojitos
        else if (tipoComida.equals("Antojitos")) {
            locales.add(new Restaurante("Antojitos La Doña", "Calle Emiliano Zapata", "555-1501", "9:00 AM - 7:00 PM", "Lunes a Sábado", R.drawable.antojitos));
            locales.add(new Restaurante("Antojitos El Chilito", "Avenida Los Reyes", "555-1502", "10:00 AM - 8:00 PM", "Martes a Domingo", R.drawable.antojitos));
            locales.add(new Restaurante("Antojitos El Charro", "Calle Guerrero", "555-1503", "11:00 AM - 6:00 PM", "Lunes a Viernes", R.drawable.antojitos));
            locales.add(new Restaurante("Antojitos La Chabelita", "Calle Lázaro Cárdenas", "555-1504", "9:00 AM - 6:00 PM", "Lunes a Domingo", R.drawable.antojitos));
            locales.add(new Restaurante("Antojitos El Fogón", "Avenida Madero", "555-1505", "10:30 AM - 8:00 PM", "Martes a Sábado", R.drawable.antojitos));
            locales.add(new Restaurante("Antojitos La Cazuela", "Calle Hidalgo", "555-1506", "9:00 AM - 7:00 PM", "Lunes a Sábado", R.drawable.antojitos));
            locales.add(new Restaurante("Antojitos La Comadre", "Avenida del Sol", "555-1507", "12:00 PM - 9:00 PM", "Lunes a Domingo", R.drawable.antojitos));
            locales.add(new Restaurante("Antojitos El Güero", "Calle del Pueblo", "555-1508", "10:00 AM - 6:30 PM", "Lunes a Viernes", R.drawable.antojitos));
            locales.add(new Restaurante("Antojitos El Baile", "Calle Las Flores", "555-1509", "9:00 AM - 8:00 PM", "Martes a Domingo", R.drawable.antojitos));
            locales.add(new Restaurante("Antojitos La Canasta", "Avenida Reforma", "555-1510", "11:00 AM - 7:00 PM", "Lunes a Sábado", R.drawable.antojitos));
            locales.add(new Restaurante("Antojitos El Saborcito", "Calle 5 de Febrero", "555-1511", "10:00 AM - 7:30 PM", "Lunes a Domingo", R.drawable.antojitos));
            locales.add(new Restaurante("Antojitos La Vaquita", "Avenida Los Insurgentes", "555-1512", "10:30 AM - 8:00 PM", "Martes a Domingo", R.drawable.antojitos));
            locales.add(new Restaurante("Antojitos La Abuelita", "Calle El Panteón", "555-1513", "9:00 AM - 6:00 PM", "Lunes a Sábado", R.drawable.antojitos));
            locales.add(new Restaurante("Antojitos El Jarocho", "Avenida San Martín", "555-1514", "10:00 AM - 7:00 PM", "Lunes a Domingo", R.drawable.antojitos));
            locales.add(new Restaurante("Antojitos La Poblana", "Calle de la Independencia", "555-1515", "11:00 AM - 8:00 PM", "Lunes a Viernes", R.drawable.antojitos));
            locales.add(new Restaurante("Antojitos El Muelle", "Calle Marqués", "555-1516", "12:00 PM - 9:00 PM", "Lunes a Domingo", R.drawable.antojitos));
            locales.add(new Restaurante("Antojitos La Charra", "Avenida Juárez", "555-1517", "9:30 AM - 7:00 PM", "Martes a Sábado", R.drawable.antojitos));
            locales.add(new Restaurante("Antojitos El Ranchito", "Calle Morelos", "555-1518", "11:00 AM - 6:00 PM", "Lunes a Domingo", R.drawable.antojitos));
            locales.add(new Restaurante("Antojitos La Fiesta", "Avenida Zapata", "555-1519", "10:00 AM - 7:00 PM", "Martes a Domingo", R.drawable.antojitos));
            locales.add(new Restaurante("Antojitos La Trajinera", "Calle Valle de Bravo", "555-1520", "9:30 AM - 8:30 PM", "Lunes a Sábado", R.drawable.antojitos));
        }

        return locales;
    }

    public void agregarEliminarFavoritos(Restaurante restaurante, int position, boolean esFavorito) {
        SharedPreferences prefs = getContext().getSharedPreferences("Favoritos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        if (esFavorito) {
            // Si es un favorito, eliminarlo
            editor.remove(restaurante.getNombre());
        } else {
            // Si no es un favorito, añadirlo
            editor.putBoolean(restaurante.getNombre(), true);
            editor.putString(restaurante.getNombre() + "_tipoComida", restaurante.getTipoComida());  // Guardar tipo de comida
        }
        editor.apply();

        // Actualizar el estado de los favoritos en el adapter
        adapter.notifyItemChanged(position);  // Sincronizar la vista
    }

    // Interfaz para notificar a la actividad que los favoritos han sido actualizados
    public interface OnFavoritosActualizadosListener {
        void actualizarFavoritos();
    }
}
