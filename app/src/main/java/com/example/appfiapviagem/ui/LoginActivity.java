package com.example.appfiapviagem.ui;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText emailAddress;
    private EditText password;
    private Button buttonLogin;

    private TextView textViewCadastro;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        textViewCadastro = (TextView) findViewById(R.id.textViewCadastro);

        mAuth = FirebaseAuth.getInstance();

        buttonLogin.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        autenticaUsuarioForm();
                    }
                }
        );

        textViewCadastro.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LoginActivity.this, RegistroUsuarioActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }

    public void autenticaUsuarioForm(){

        if(emailAddress.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.email_erro), Toast.LENGTH_SHORT).show();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress.getText().toString()).matches()){
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.email_invalido), Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.password_erro), Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.getText().length() < 6){
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.password_invalido), Toast.LENGTH_SHORT).show();
            return;
        }

        autenticaUsuarioFirebase();
    }

    protected void autenticaUsuarioFirebase(){

        try{
            mAuth.signInWithEmailAndPassword(emailAddress.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Log.d("success", "signInWithEmail:success");
                                Toast.makeText(LoginActivity.this, "Usuario logado firebase com sucesso", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
                                i.putExtra("Email", mAuth.getCurrentUser().getEmail());

                                startActivity(i);

                            } else {
                                Log.w("error", "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Erro de autenticacao firebase", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        catch (Exception e){
            Log.w("error", e.getMessage());
            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}