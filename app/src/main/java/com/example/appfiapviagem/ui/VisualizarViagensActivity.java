package com.example.appfiapviagem.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.appfiapviagem.R;
import com.example.appfiapviagem.adapter.DestinoAdapter;
import com.example.appfiapviagem.model.Destino;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class VisualizarViagensActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DestinoAdapter destinoAdapter;

    private static final List<Destino> destinos = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_viagens);

        recyclerView = findViewById(R.id.recycler1);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Destino")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Destino destino = new Destino();
                                destino.setPais(document.getData().get("pais").toString());
                                destino.setEstado(document.getData().get("estado").toString());
                                destino.setEndereco(document.getData().get("endereco").toString());
                                destino.setHospedagem(document.getData().get("hospedagem").toString());
                                destino.setValorGasto(document.getData().get("valorGasto").toString());
                                destino.setAvaliacao(document.getData().get("avaliacao").toString());
                                destino.setDescricao(document.getData().get("descricao").toString());
                                destinos.add(destino);
                            }
                        } else {
                            Log.w("sucesso", "Error getting documents.", task.getException());
                        }
                    }
                });

        destinoAdapter = new DestinoAdapter(destinos);

        recyclerView.setAdapter(destinoAdapter);
    }
}