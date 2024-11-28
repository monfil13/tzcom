package com.example.tzcom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ComidaAdapter extends RecyclerView.Adapter<ComidaAdapter.ComidaViewHolder> {

    private Context context;
    private String[] comidas;
    private int[] imagenes;
    private OnItemClickListener onItemClickListener;

    // Constructor
    public ComidaAdapter(Context context, String[] comidas, int[] imagenes) {
        this.context = context;
        this.comidas = comidas;
        this.imagenes = imagenes;
    }

    // Interfaz para el clic en el item
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ComidaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comida, parent, false);
        return new ComidaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComidaViewHolder holder, int position) {
        holder.tvComida.setText(comidas[position]);
        holder.ivComida.setImageResource(imagenes[position]);

        // Establecer el clic en el item
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return comidas.length;
    }

    public static class ComidaViewHolder extends RecyclerView.ViewHolder {
        TextView tvComida;
        ImageView ivComida;

        public ComidaViewHolder(View itemView) {
            super(itemView);
            tvComida = itemView.findViewById(R.id.tvComida);
            ivComida = itemView.findViewById(R.id.ivComida);
        }
    }
}
