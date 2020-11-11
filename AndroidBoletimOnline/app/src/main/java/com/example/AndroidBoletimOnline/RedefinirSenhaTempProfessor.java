package com.example.AndroidBoletimOnline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RedefinirSenhaTempProfessor extends AppCompatActivity {

   /* SharedPreferences sharedpreferences;
    boolean splashScreen;

    */

    TextView tvTextoRS;
    EditText edtConfirSenhaTempProfessor, edtSenhaTempProfessor;
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefinir_senha_temp_professor);

        tvTextoRS = findViewById(R.id.tvTextoRS);
        edtSenhaTempProfessor = findViewById(R.id.edtSenhaTempProfessor);
        edtConfirSenhaTempProfessor = findViewById(R.id.edtConfirSenhaTmpProfessor);
        btnOk = findViewById(R.id.btnOk);


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String senhaT = edtSenhaTempProfessor.getText().toString();
                String confirS = edtConfirSenhaTempProfessor.getText().toString();

                if (senhaT.isEmpty() || confirS.isEmpty()) {//Verificar se estão vazios
                    UsarMetodos.alert("Não deixe em branco.",
                            getApplicationContext());
                    edtSenhaTempProfessor.setText("");
                    edtConfirSenhaTempProfessor.setText("");
                    edtSenhaTempProfessor.requestFocus();

                } else if (!senhaT.equals(confirS)) {
                    UsarMetodos.alert("Senhas não conferem.", getApplicationContext());
                    edtSenhaTempProfessor.setText("");
                    edtConfirSenhaTempProfessor.setText("");

                } else {
                    Intent intent = new Intent(getApplicationContext(), ProfessorActivity.class);
                    startActivity(intent);
                    finish();
                }
            }


        });

    }
}