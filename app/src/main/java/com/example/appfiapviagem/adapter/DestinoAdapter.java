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

public class DestinoAdapter extends FirebaseRecyclerAdapter<Destino, DestinoAdapter.DestinoViewholder>{

    FirebaseRecyclerOptions<Destino> options;

    public DestinoAdapter(@NonNull FirebaseRecyclerOptions<Destino> options){
        super(options);
    }


    @NonNull
    @Override
    public DestinoViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destino, parent, false);
        return new DestinoAdapter.DestinoViewholder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull DestinoViewholder holder, int position, @NonNull Destino model){

        holder.pais.setText(model.getPais());
        holder.estado.setText(model.getEstado());
        holder.endereco.setText(model.getEndereco());
        holder.hospedagem.setText(model.getHospedagem());
        holder.valorGasto.setText(model.getValorGasto());
        holder.avaliacao.setText(model.getAvaliacao());
        holder.descricao.setText(model.getDescricao());

//        holder.buttonDeletar.setOnClickListener(view -> itemRemoved(holder, position));

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



//    private void removerItem(int position) {
//        destinos.remove(position);
//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, destinos.size());
//    }
}
