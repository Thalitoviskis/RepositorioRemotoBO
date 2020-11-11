package com.example.AndroidBoletimOnline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RedefinirSenhaTempAluno extends AppCompatActivity {

    TextView tvAlert;
    EditText edtSenhaTempAluno, edtConfirSenhaTempAluno;
    Button btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefinir_senha_temp_aluno);

        tvAlert = findViewById(R.id.tvAlert);
        edtSenhaTempAluno = findViewById(R.id.edtSenhaTempAluno);
        edtConfirSenhaTempAluno = findViewById(R.id.edtConfirmeSPerfilAluno);

        btnOK = findViewById(R.id.btnOK);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String senhaTempA = edtSenhaTempAluno.getText().toString();
                String confirSTempA = edtConfirSenhaTempAluno.getText().toString();


                if (senhaTempA.isEmpty() || confirSTempA.isEmpty()) {//Verificar se estão vazios
                    UsarMetodos.alert("Não deixe em branco.",
                            getApplicationContext());
                    edtSenhaTempAluno.setText("");
                    edtConfirSenhaTempAluno.setText("");
                    edtSenhaTempAluno.requestFocus();

                } else if (!senhaTempA.equals(confirSTempA)) {
                    UsarMetodos.alert("Senhas não conferem.", getApplicationContext());
                    edtSenhaTempAluno.setText("");
                    edtConfirSenhaTempAluno.setText("");

                } else {
                    Intent intent = new Intent(getApplicationContext(), AlunoActivity.class);
                    startActivity(intent);
                    finish();
                }
            }


        });

    }
}
