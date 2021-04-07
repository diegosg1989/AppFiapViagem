package com.example.appfiapviagem.ui.viagens;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.appfiapviagem.R;
import com.example.appfiapviagem.adapter.DestinoAdapter;
import com.example.appfiapviagem.model.Destino;
import com.example.appfiapviagem.ui.ProfileActivity;
import com.example.appfiapviagem.ui.login.LoginActivity;
import com.example.appfiapviagem.ui.usuario.RegistroUsuarioActivity;
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

    private List<Destino> destinos = new ArrayList<>();

    private Button buttonVoltar;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_viagens);

        buttonVoltar = (Button) findViewById(R.id.buttonVoltarViagens);

        recyclerView = findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Destino")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Destino destino = new Destino(
                                        document.getData().get("pais").toString(),
                                        document.getData().get("estado").toString(),
                                        document.getData().get("endereco").toString(),
                                        document.getData().get("hospedagem").toString(),
                                        document.getData().get("valorGasto").toString(),
                                        document.getData().get("avaliacao").toString(),
                                        document.getData().get("descricao").toString());
                                destino.setId(document.getId());

                                destinos.add(destino);

                                Log.i("sucesso", document.getId() + " => " + document.getData());
                            }

                            destinoAdapter = new DestinoAdapter(destinos, new DestinoAdapter.Listener() {
                                @Override
                                public void onItemClick(Destino destino) {

                                    Intent intent = new Intent(VisualizarViagensActivity.this, CadastroViagemActivity.class);
                                    intent.putExtra("destino", destino);
                                    intent.putExtra("acao", "alterar");
                                    startActivity(intent);

                                    Log.d("sucesso", "irá alterar os dados");
                                }
                            });

                            recyclerView.setAdapter(destinoAdapter);

                        } else {
                            Log.w("erro", "Error getting documents.", task.getException());
                        }
                    }
                });

        buttonVoltar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(VisualizarViagensActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}