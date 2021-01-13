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

        holder.firstname.setText(model.getPais());

        holder.lastname.setText(model.getEstado());

        holder.age.setText(model.getEndereco());
    }

    @NonNull
    @Override
    public DestinoViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destino, parent, false);
        return new DestinoAdapter.DestinoViewholder(view);
    }

    class DestinoViewholder extends RecyclerView.ViewHolder {

        TextView firstname, lastname, age;

        public DestinoViewholder(@NonNull View itemView) {
            super(itemView);

            firstname = itemView.findViewById(R.id.firstname);
            lastname = itemView.findViewById(R.id.lastname);
            age = itemView.findViewById(R.id.age);
        }
    }
}
