package com.example.appfiapviagem.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appfiapviagem.R;

public class ProfileActivity extends AppCompatActivity {

    private Button buttonNovaViagem;
    private Button buttonListaViagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        buttonNovaViagem = (Button) findViewById(R.id.buttonNovaViagem);

        buttonListaViagens = (Button) findViewById(R.id.buttonListaViagens);

        buttonNovaViagem.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(ProfileActivity.this, CadastroViagemActivity.class);
                        startActivity(intent);
                    }
                }
        );

        buttonListaViagens.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(ProfileActivity.this, ViewDestinosActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }
}