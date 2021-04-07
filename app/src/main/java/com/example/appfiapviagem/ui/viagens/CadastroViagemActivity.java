package com.example.appfiapviagem.ui.viagens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appfiapviagem.R;
import com.example.appfiapviagem.model.Destino;
import com.example.appfiapviagem.ui.ProfileActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
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

    private TextView textViewNovoDestino;

    private FirebaseFirestore db;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_viagem);

        textViewNovoDestino = (TextView) findViewById(R.id.textViewNovoDestino);
        buttonCadastrarDestino = (Button) findViewById(R.id.buttonCadastrarDestino);

        db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();

        buttonCadastrarDestino.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        autenticaCadastroViagemForm();

                        gravarViagens();

                        Intent intent = new Intent(CadastroViagemActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    }
                }
        );

        if(bundle != null){

            String acao = (String) bundle.getSerializable("acao");

            if(acao.equals("alterar")){

                getValoresTela();

                Destino destino = (Destino) bundle.getSerializable("destino");

                textViewNovoDestino.setText("Alterar Viagem");

                pais.setText(destino.getPais());
                estado.setText(destino.getEstado());
                endereco.setText(destino.getEndereco());
                hospedagem.setText(destino.getHospedagem());
                valorGasto.setText(destino.getValorGasto());
                avaliacao.setText(destino.getAvaliacao());
                descricao.setText(destino.getDescricao());

                buttonCadastrarDestino.setText("Alterar");

                buttonCadastrarDestino.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                autenticaCadastroViagemForm();

                                Destino destinoAlterado = criaDestino();

                                destinoAlterado.setId(destino.getId());

                                editarViagem(destinoAlterado);

                                Intent intent = new Intent(CadastroViagemActivity.this, ProfileActivity.class);
                                startActivity(intent);
                            }
                        }
                );
            }
        }
    }

    private void getValoresTela(){

        pais = (EditText) findViewById(R.id.editTextTextPais);
        estado = (EditText) findViewById(R.id.editTextTextEstado);
        endereco = (EditText) findViewById(R.id.editTextTextEndereco);
        hospedagem = (EditText) findViewById(R.id.editTextTextHospedagem);
        valorGasto = (EditText) findViewById(R.id.editTextValorGasto);
        avaliacao = (EditText) findViewById(R.id.editTextAvaliacao);
        descricao = (EditText) findViewById(R.id.editTextTextDescricao);
    }


    private void autenticaCadastroViagemForm(){

        getValoresTela();

        if(pais.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "O país deve ser digitado", Toast.LENGTH_SHORT).show();
            return;
        }

        if(estado.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "O estado deve ser digitado", Toast.LENGTH_SHORT).show();
            return;
        }

        if(endereco.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "O endereço deve ser digitado", Toast.LENGTH_SHORT).show();
            return;
        }

        if(hospedagem.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "A hospedagem deve ser digitado", Toast.LENGTH_SHORT).show();
            return;
        }

        if(valorGasto.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "A valor gasto deve ser digitado", Toast.LENGTH_SHORT).show();
            return;
        }

        if(avaliacao.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "A avaliação deve ser digitada", Toast.LENGTH_SHORT).show();
            return;
        }

        if(descricao.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "A descricao deve ser digitada", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void gravarViagens(){

        try {

            Destino destino = criaDestino();

            db.collection("Destino")
                    .add(destino)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("sucesso", "Nova viagem cadastrada com sucesso: " + documentReference.getId());
                            Toast.makeText(getApplicationContext(), "Nova viagem cadastrada com sucesso", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("error", "Erro ao cadastrar nova viagem", e);
                            Toast.makeText(getApplicationContext(), "Erro ao cadastrar nova viagem", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        catch (Exception e){
            Log.w("error", e.getMessage());
            Toast.makeText(CadastroViagemActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void editarViagem(Destino destino) {

        try {

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
                            Log.d("sucesso", "Viagem alterada com sucesso");
                            Toast.makeText(getApplicationContext(), "Viagem alterada com sucesso", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("error", "Erro ao alterar a viagem", e);
                            Toast.makeText(getApplicationContext(), "Erro ao alterar a viagem", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        catch (Exception e){
            Log.w("error", e.getMessage());
            Toast.makeText(CadastroViagemActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private Destino criaDestino(){

        return new Destino(
                pais.getText().toString(),
                estado.getText().toString(),
                endereco.getText().toString(),
                hospedagem.getText().toString(),
                valorGasto.getText().toString(),
                avaliacao.getText().toString(),
                descricao.getText().toString());
    }
}
