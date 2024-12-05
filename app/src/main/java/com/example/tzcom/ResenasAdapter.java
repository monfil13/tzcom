package com.example.tzcom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class ResenasAdapter extends RecyclerView.Adapter<ResenasAdapter.ResenaViewHolder> {

    private List<DocumentSnapshot> reseñasList;

    public ResenasAdapter(List<DocumentSnapshot> reseñasList) {
        this.reseñasList = reseñasList;
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

        holder.nombreLocalTextView.setText(nombreLocal);
        holder.contenidoTextView.setText(contenido);
        holder.usuarioTextView.setText("Usuario: " + usuario);
        holder.fechaTextView.setText("Fecha: " + fecha);
    }

    @Override
    public int getItemCount() {
        return reseñasList.size();
    }

    public static class ResenaViewHolder extends RecyclerView.ViewHolder {
        TextView nombreLocalTextView, contenidoTextView, usuarioTextView, fechaTextView;

        public ResenaViewHolder(View itemView) {
            super(itemView);
            nombreLocalTextView = itemView.findViewById(R.id.nombreLocalTextView);
            contenidoTextView = itemView.findViewById(R.id.contenidoTextView);
            usuarioTextView = itemView.findViewById(R.id.usuarioTextView);
            fechaTextView = itemView.findViewById(R.id.fechaTextView);
        }
    }
}
