package com.example.AndroidBoletimOnline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RedefinirSenhaTempAluno extends AppCompatActivity {

    /* SharedPreferences sharedpreferences;
    boolean splashScreen;

    */

    TextView tvAlert ;
    EditText edtSenhaTempAluno, edtConfirSenhaTempAluno ;
    Button btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefinir_senha_temp_aluno);

        tvAlert = findViewById(R.id.tvTextoRS);
        edtSenhaTempAluno = findViewById(R.id.edtSenhaTemp);
        edtConfirSenhaTempAluno= findViewById(R.id.edtConfirSenhaTempAluno);
        btnOK = findViewById(R.id.btnOK);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String senhaT = edtSenhaTempAluno.getText().toString();
                String confirS = edtConfirSenhaTempAluno.getText().toString();

                if (senhaT.isEmpty() || confirS.isEmpty()) {//Verificar se estão vazios
                    UsarMetodos.alert("Não deixe em branco.",
                            getApplicationContext());
                    edtSenhaTempAluno.setText("");
                    edtConfirSenhaTempAluno.setText("");
                    edtSenhaTempAluno.requestFocus();

                } else {
                    Intent intent = new Intent(getApplicationContext(), AlunoActivity.class);
                    startActivity(intent);

                }
            }


        });


    }
}