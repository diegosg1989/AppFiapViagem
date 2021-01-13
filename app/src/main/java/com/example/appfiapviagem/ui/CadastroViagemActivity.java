package com.example.appfiapviagem.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appfiapviagem.R;
import com.example.appfiapviagem.model.Destino;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

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

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;
    private String userId;

    private Destino destino;

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
                        gravarDadosFireBase3();
                    }
                }
        );
    }

    private void gravarDadosFireBase3() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Destino").push();

        Destino destino = new Destino();
        destino.setPais(pais.getText().toString());
        destino.setEstado(estado.getText().toString());
        destino.setEndereco(endereco.getText().toString());
        destino.setHospedagem(hospedagem.getText().toString());
        destino.setValorGasto(valorGasto.getText().toString());
        destino.setAvaliacao(avaliacao.getText().toString());
        destino.setDescricao(descricao.getText().toString());

        databaseReference.setValue(destino);

        Toast.makeText(CadastroViagemActivity.this, "Dados gravados com sucesso", Toast.LENGTH_SHORT).show();
    }
}
