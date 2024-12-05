package com.example.tzcom;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LocalesAdapter extends RecyclerView.Adapter<LocalesAdapter.ViewHolder> {

    private ArrayList<Restaurante> listaLocales;
    private boolean esFavorito;  // Indicador para saber si es un favorito

    // Constructor que acepta la lista de restaurantes y un valor booleano (si es favorito)
    public LocalesAdapter(ArrayList<Restaurante> listaLocales, boolean esFavorito) {
        this.listaLocales = listaLocales;
        this.esFavorito = esFavorito;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_local, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurante restaurante = listaLocales.get(position);

        // Establecer los datos del restaurante
        holder.nombre.setText(restaurante.getNombre());
        holder.direccion.setText(restaurante.getDireccion());
        holder.telefono.setText(restaurante.getTelefono());
        holder.horario.setText(restaurante.getHorario());

        // Mostrar la imagen del restaurante
        holder.imagen.setImageResource(restaurante.getImagen());

        // Obtener SharedPreferences
        SharedPreferences prefs = holder.itemView.getContext().getSharedPreferences("Favoritos", Context.MODE_PRIVATE);

        // Verificar si el restaurante está en favoritos
        boolean esFavorito = prefs.getBoolean(restaurante.getNombre(), false);

        // Establecer el texto del botón según si es un favorito
        if (esFavorito) {
            holder.btnFavoritos.setText("Eliminar de Favoritos");
        } else {
            holder.btnFavoritos.setText("Añadir a Favoritos");
        }

        // Lógica para el botón "Añadir a Favoritos" o "Eliminar de Favoritos"
        holder.btnFavoritos.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            if (esFavorito) {
                // Si es un favorito, eliminarlo
                editor.remove(restaurante.getNombre());
                editor.remove(restaurante.getNombre() + "_direccion");
                editor.remove(restaurante.getNombre() + "_telefono");
                editor.remove(restaurante.getNombre() + "_horario");
                editor.remove(restaurante.getNombre() + "_dias");
                editor.remove(restaurante.getNombre() + "_imagen");
                editor.apply();

                // Notificar a la actividad/fragments que se eliminó el favorito
                Toast.makeText(v.getContext(), "Restaurante eliminado de favoritos", Toast.LENGTH_SHORT).show();
            } else {
                // Si no es un favorito, añadirlo
                editor.putBoolean(restaurante.getNombre(), true);
                editor.putString(restaurante.getNombre() + "_direccion", restaurante.getDireccion());
                editor.putString(restaurante.getNombre() + "_telefono", restaurante.getTelefono());
                editor.putString(restaurante.getNombre() + "_horario", restaurante.getHorario());
                editor.putString(restaurante.getNombre() + "_dias", restaurante.getDias());
                editor.putInt(restaurante.getNombre() + "_imagen", restaurante.getImagen());
                editor.apply();
            }

            // Actualizar la vista
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return listaLocales.size();
    }

    // Método para actualizar la lista en el adapter
    public void actualizarLista(ArrayList<Restaurante> nuevaLista) {
        this.listaLocales = nuevaLista;
        notifyDataSetChanged();
    }

    // ViewHolder para el RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;
        TextView nombre, direccion, telefono, horario;
        Button btnFavoritos;

        public ViewHolder(View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.ivImagen);
            nombre = itemView.findViewById(R.id.tvNombre);
            direccion = itemView.findViewById(R.id.tvDireccion);
            telefono = itemView.findViewById(R.id.tvTelefono);
            horario = itemView.findViewById(R.id.tvHorario);
            btnFavoritos = itemView.findViewById(R.id.btnFavoritos);
        }
    }
}
