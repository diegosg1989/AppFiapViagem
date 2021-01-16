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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class DestinoAdapter extends RecyclerView.Adapter<DestinoAdapter.DestinoViewholder> {

    private List<Destino> destinos;

    public DestinoAdapter(List<Destino> destinos) {
        this.destinos = destinos;
    }

    @NonNull
    @Override
    public DestinoAdapter.DestinoViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DestinoViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_alterar_viagem, parent, false));
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
        holder.buttonEditar.setOnClickListener(view -> editarItem(position, destino));
    }

    @Override
    public int getItemCount() {
        return destinos.size();
    }

    private void removerItem(int position){

        Destino destino = destinos.get(position);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Destino").document(destino.getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("sucesso", "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("error", "Error deleting document", e);
                    }
                });

        destinos.remove(destino);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, destinos.size());
    }

    private void editarItem(int position, Destino destino){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference documentReference = db.collection("Destino").document(destino.getId());
        documentReference.update("pais", destino.getPais());
        documentReference.update("estado", destino.getEstado());
        documentReference.update("endereco", destino.getEndereco());
        documentReference.update("hospedagem", destino.getHospedagem());
        documentReference.update("valorGasto", destino.getValorGasto());
        documentReference.update("avaliacao", destino.getAvaliacao());
        documentReference.update("descricao", destino.getDescricao())
        .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d("sucesso", "DocumentSnapshot successfully update!");
                }
            });




    }



    class DestinoViewholder extends RecyclerView.ViewHolder {

        TextView pais, estado, endereco, hospedagem, valorGasto, avaliacao, descricao;
        Button buttonDeletar, buttonEditar;

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
            buttonEditar = itemView.findViewById(R.id.buttonEditar);
        }
    }
}
