package com.example.appfiapviagem.ui.usuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appfiapviagem.R;
import com.example.appfiapviagem.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistroUsuarioActivity extends AppCompatActivity {

    private EditText cadNome;
    private EditText cadEmail;
    private EditText cadTelefone;
    private EditText cadPassword;
    private Button buttonCad;
    private TextView fazerLogin;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        cadNome = (EditText) findViewById(R.id.editTextTextCadNome);
        cadEmail = (EditText) findViewById(R.id.editTextTextCadEmail);
        cadTelefone = (EditText) findViewById(R.id.editTextCadTelefone);
        cadPassword = (EditText) findViewById(R.id.editTextTextCadPassword);
        buttonCad = (Button) findViewById(R.id.buttonCad);
        fazerLogin = (TextView) findViewById(R.id.textViewFazerLogin);

        mAuth = FirebaseAuth.getInstance();

        buttonCad.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        autenticaCadastroForm();
                    }
                }
        );

        fazerLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RegistroUsuarioActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }

    public void autenticaCadastroForm(){

        if(cadNome.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.cad_nome_erro), Toast.LENGTH_SHORT).show();
            return;
        }

        if(cadEmail.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.cad_email_erro), Toast.LENGTH_SHORT).show();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(cadEmail.getText().toString()).matches()){
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.cad_email_invalido), Toast.LENGTH_SHORT).show();
            return;
        }

        if(cadTelefone.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.cad_telefone_erro), Toast.LENGTH_SHORT).show();
            return;
        }

        if(!Patterns.PHONE.matcher(cadTelefone.getText().toString()).matches()){
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.cad_telefone_invalido), Toast.LENGTH_SHORT).show();
            return;
        }

        if(cadPassword.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.cad_password_erro), Toast.LENGTH_SHORT).show();
            return;
        }

        if(cadPassword.getText().length() < 6){
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.cad_password_invalido), Toast.LENGTH_SHORT).show();
            return;
        }

        cadastraUsuario();
    }

    public void cadastraUsuario(){

        try{
            mAuth.createUserWithEmailAndPassword(cadEmail.getText().toString(), cadPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("success", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                Toast.makeText(getApplicationContext(), "Usuario cadastrado com sucesso", Toast.LENGTH_SHORT).show();

                            } else {
                                Log.w("error", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegistroUsuarioActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            );
        }
        catch (Exception e){
            Log.w("error", e.getMessage());
            Toast.makeText(RegistroUsuarioActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}