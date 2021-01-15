package com.example.appfiapviagem.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfiapviagem.R;
import com.example.appfiapviagem.model.Destino;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class DestinoAdapter extends RecyclerView.Adapter<DestinoAdapter.DestinoViewholder> {

    private List<Destino> destinos;

    public DestinoAdapter(List<Destino> destinos) {
        this.destinos = destinos;
    }


    @NonNull
    @Override
    public DestinoAdapter.DestinoViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destino, parent, false);
        return new DestinoAdapter.DestinoViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinoAdapter.DestinoViewholder holder, int position) {

        Destino destino = destinos.get(position);

        holder.pais.setText(destino.getPais());
        holder.estado.setText(destino.getEstado());
        holder.endereco.setText(destino.getEndereco());
        holder.hospedagem.setText(destino.getHospedagem());
        holder.valorGasto.setText(destino.getValorGasto());
        holder.avaliacao.setText(destino.getAvaliacao());
        holder.descricao.setText(destino.getDescricao());

        holder.buttonDeletar.setOnClickListener(view -> removerItem(position));
    }

    @Override
    public int getItemCount() {
        return destinos.size();
    }

    public void removerItem(int position){

    }

    class DestinoViewholder extends RecyclerView.ViewHolder {

        TextView pais, estado, endereco, hospedagem, valorGasto, avaliacao, descricao;
        Button buttonDeletar;

        public DestinoViewholder(@NonNull View itemView) {
            super(itemView);

            pais = itemView.findViewById(R.id.pais);
            estado = itemView.findViewById(R.id.estado);
            endereco = itemView.findViewById(R.id.endereco);
            hospedagem = itemView.findViewById(R.id.hospedagem);
            valorGasto = itemView.findViewById(R.id.valorGasto);
            avaliacao = itemView.findViewById(R.id.avaliacao);
            descricao = itemView.findViewById(R.id.descricao);

            buttonDeletar = itemView.findViewById(R.id.buttonDeletar);
        }
    }
}
