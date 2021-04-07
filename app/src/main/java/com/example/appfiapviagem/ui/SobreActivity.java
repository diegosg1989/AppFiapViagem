package com.example.appfiapviagem.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appfiapviagem.R;

public class SobreActivity extends AppCompatActivity {

    private Button buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        buttonVoltar = (Button) findViewById(R.id.buttonVoltarSobre);

        buttonVoltar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SobreActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}