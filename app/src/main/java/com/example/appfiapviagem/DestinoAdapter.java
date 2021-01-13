package com.example.appfiapviagem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class DestinoAdapter extends FirebaseRecyclerAdapter<Destino, DestinoAdapter.DestinoViewholder> {

    public DestinoAdapter(@NonNull FirebaseRecyclerOptions<Destino> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DestinoViewholder holder, int position, @NonNull Destino model){

        holder.pais.setText(model.getPais());
        holder.estado.setText(model.getEstado());
        holder.endereco.setText(model.getEndereco());
    }

    @NonNull
    @Override
    public DestinoViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destino, parent, false);
        return new DestinoAdapter.DestinoViewholder(view);
    }

    class DestinoViewholder extends RecyclerView.ViewHolder {

        TextView pais, estado, endereco;

        public DestinoViewholder(@NonNull View itemView) {
            super(itemView);

            pais = itemView.findViewById(R.id.pais);
            estado = itemView.findViewById(R.id.estado);
            endereco = itemView.findViewById(R.id.endereco);
        }
    }
}
