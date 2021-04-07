package com.example.appfiapviagem.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appfiapviagem.R;
import com.example.appfiapviagem.ui.login.LoginActivity;
import com.example.appfiapviagem.ui.usuario.RegistroUsuarioActivity;
import com.example.appfiapviagem.ui.viagens.CadastroViagemActivity;
import com.example.appfiapviagem.ui.viagens.VisualizarViagensActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    private Button buttonNovaViagem;
    private Button buttonListaViagens;
    private Button buttonNovoUsuario;
    private Button buttonSobre;

    private Button buttonLogout;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();

        buttonNovaViagem = (Button) findViewById(R.id.buttonNovaViagem);
        buttonListaViagens = (Button) findViewById(R.id.buttonListaViagens);
        buttonNovoUsuario = (Button) findViewById(R.id.buttonNovoUsuario);
        buttonSobre = (Button) findViewById(R.id.buttonSobre);

        buttonLogout = (Button) findViewById(R.id.buttonLogout);

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
                        Intent intent = new Intent(ProfileActivity.this, VisualizarViagensActivity.class);
                        startActivity(intent);
                    }
                }
        );

        buttonNovoUsuario.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ProfileActivity.this, RegistroUsuarioActivity.class);
                        startActivity(intent);
                    }
                }
        );

        buttonSobre.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ProfileActivity.this, SobreActivity.class);
                        startActivity(intent);
                    }
                }
        );

        buttonLogout.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        mAuth.signOut();
                        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }
}