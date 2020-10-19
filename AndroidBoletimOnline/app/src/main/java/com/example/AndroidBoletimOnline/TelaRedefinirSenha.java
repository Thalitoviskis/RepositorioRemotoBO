package com.example.AndroidBoletimOnline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.AndroidBoletimOnline.R;

public class TelaRedefinirSenha extends AppCompatActivity {

    Button btnConfirmar;
    EditText edtUsuariored, edtNovaSenha, edtConfirmarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_redefinir_senha);
        //Tudo que ocorre no inicio do app > Fica em tela cheia
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        btnConfirmar = findViewById(R.id.btnConfirmar);
        edtUsuariored = findViewById(R.id.edtUsuarioRed);
        edtNovaSenha = findViewById(R.id.edtNovaSenha);
        edtConfirmarSenha = findViewById(R.id.edtConfirmarSenha);

        edtUsuariored.requestFocus();

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuarioRed = edtUsuariored.getText().toString();
                String novaSenha = edtNovaSenha.getText().toString();
                String confirmarS = edtConfirmarSenha.getText().toString();

                if (usuarioRed.isEmpty() || novaSenha.isEmpty() || confirmarS.isEmpty()) {
                    UsarMetodos.alert("Não deixe em branco.", getApplicationContext());
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}


