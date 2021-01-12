package com.example.appfiapviagem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadastroViagemActivity extends AppCompatActivity {

    private EditText pais;
    private EditText estado;
    private EditText endereco;
    private EditText hospedagem;
    private EditText valorGasto;
    private EditText avaliacao;
    private EditText descricao;
    private Button buttonCadastrarDestino;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_viagem);

        pais = (EditText) findViewById(R.id.editTextTextPais);
        estado = (EditText) findViewById(R.id.editTextTextEstado);
        endereco = (EditText) findViewById(R.id.editTextTextEndereco);
        hospedagem = (EditText) findViewById(R.id.editTextTextHospedagem);
        valorGasto = (EditText) findViewById(R.id.editTextValorGasto);
        avaliacao = (EditText) findViewById(R.id.editTextAvaliacao);
        descricao = (EditText) findViewById(R.id.editTextTextDescricao);

        buttonCadastrarDestino = (Button) findViewById(R.id.buttonCadastrarDestino);

        db = FirebaseFirestore.getInstance();

        buttonCadastrarDestino.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        gravarDadosFireBase();
                    }
                }
        );
    }

    private void gravarDadosFireBase() {
        // Create a new user with a first and last name
        Map<String, Object> destinos = new HashMap<>();
        destinos.put("pais", pais.getText().toString());
        destinos.put("estado", estado.getText().toString());
        destinos.put("endereco", endereco.getText().toString());
        destinos.put("hospedagem", hospedagem.getText().toString());
        destinos.put("valorGasto", valorGasto.getText().toString());
        destinos.put("avaliacao", avaliacao.getText().toString());
        destinos.put("descricao", descricao.getText().toString());

        try {
            // Add a new document with a generated ID
            db.collection("destinos")
                    .add(destinos)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("Success", "DocumentSnapshot added with ID: " + documentReference.getId());
                            Toast.makeText(CadastroViagemActivity.this, "DocumentSnapshot added with ID: " + documentReference.getId(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("Failure", "Error adding document", e);
                            Toast.makeText(CadastroViagemActivity.this, "Error adding document", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        catch (Exception e){
            Log.w("error", e.getMessage());
            Toast.makeText(CadastroViagemActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
