package com.example.tzcom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ResenasAdapter extends RecyclerView.Adapter<ResenasAdapter.ResenaViewHolder> {

    private List<DocumentSnapshot> reseñasList;
    private String usuarioActual;

    public ResenasAdapter(List<DocumentSnapshot> reseñasList, String usuarioActual) {
        this.reseñasList = reseñasList;
        this.usuarioActual = usuarioActual;
    }

    @NonNull
    @Override
    public ResenaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_resena, parent, false);
        return new ResenaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResenaViewHolder holder, int position) {
        DocumentSnapshot resenaDoc = reseñasList.get(position);

        String nombreLocal = resenaDoc.getString("nombreLocal");
        String contenido = resenaDoc.getString("contenido");
        String usuario = resenaDoc.getString("usuario");
        String fecha = resenaDoc.getString("fecha");
        String resenaId = resenaDoc.getId();  // Obtenemos el ID del documento para eliminarlo luego

        holder.nombreLocalTextView.setText(nombreLocal);
        holder.contenidoTextView.setText(contenido);
        holder.usuarioTextView.setText("Usuario: " + usuario);
        holder.fechaTextView.setText("Fecha: " + fecha);

        // Comprobamos si el usuario actual es el dueño de la reseña
        if (usuario.equals(usuarioActual)) {
            holder.btnEliminar.setVisibility(View.VISIBLE);  // Habilitamos el botón de eliminar

            // Lógica para eliminar la reseña
            holder.btnEliminar.setOnClickListener(v -> {
                eliminarResena(resenaId, position);
            });
        } else {
            holder.btnEliminar.setVisibility(View.GONE);  // Ocultamos el botón de eliminar si no es su reseña
        }
    }

    @Override
    public int getItemCount() {
        return reseñasList.size();
    }

    // Método para eliminar la reseña
    private void eliminarResena(String resenaId, int position) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("resenas")
                .document(resenaId)
                .delete()
                .addOnSuccessListener(aVoid -> {
                    // Si se eliminó correctamente
                    Toast.makeText(reseñasList.get(0).getReference().getFirestore().getApp().getApplicationContext(), "Reseña eliminada", Toast.LENGTH_SHORT).show();
                    reseñasList.remove(position);  // Remover la reseña de la lista
                    notifyItemRemoved(position);  // Notificar que se eliminó un ítem
                })
                .addOnFailureListener(e -> {
                    // Si ocurre un error
                    Toast.makeText(reseñasList.get(0).getReference().getFirestore().getApp().getApplicationContext(), "Error al eliminar la reseña", Toast.LENGTH_SHORT).show();
                });
    }

    public static class ResenaViewHolder extends RecyclerView.ViewHolder {
        TextView nombreLocalTextView, contenidoTextView, usuarioTextView, fechaTextView;
        Button btnEliminar;

        public ResenaViewHolder(View itemView) {
            super(itemView);
            nombreLocalTextView = itemView.findViewById(R.id.nombreLocalTextView);
            contenidoTextView = itemView.findViewById(R.id.contenidoTextView);
            usuarioTextView = itemView.findViewById(R.id.usuarioTextView);
            fechaTextView = itemView.findViewById(R.id.fechaTextView);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
