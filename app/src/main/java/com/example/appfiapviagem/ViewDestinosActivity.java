package com.example.appfiapviagem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ViewDestinosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DestinoAdapter destinoAdapter;
    private DatabaseReference mBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_destinos);

        mBase = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recycler1);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Query query = FirebaseDatabase.getInstance().getReference().child("Destino");

        FirebaseRecyclerOptions<Destino> options = new FirebaseRecyclerOptions.Builder<Destino>()
                .setQuery(query, new SnapshotParser<Destino>() {
                    @NonNull
                    @Override
                    public Destino parseSnapshot(@NonNull DataSnapshot snapshot) {

                        Destino destino = new Destino();
                        destino.setPais(snapshot.child("pais").getValue().toString());
                        destino.setEstado(snapshot.child("estado").getValue().toString());
                        destino.setEndereco(snapshot.child("endereco").getValue().toString());

                        return destino;
                    }
                })
                .build();

        destinoAdapter = new DestinoAdapter(options);

        recyclerView.setAdapter(destinoAdapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        destinoAdapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        destinoAdapter.stopListening();
    }
}