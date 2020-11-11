package com.example.AndroidBoletimOnline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RedefinirSenhaTempActivity extends AppCompatActivity {

   /* SharedPreferences sharedpreferences;
    boolean splashScreen;

    */

    TextView tvTextoRS;
    EditText edtConfirSenhaTemp, edtSenhaTemp;
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefinir_senha_temp);

        tvTextoRS = findViewById(R.id.tvTextoRS);
        edtSenhaTemp = findViewById(R.id.edtSenhaTemp);
        edtConfirSenhaTemp = findViewById(R.id.edtConfirSenhaTmp);
        btnOk = findViewById(R.id.btnOk);


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String senhaT = edtSenhaTemp.getText().toString();
                String confirS = edtConfirSenhaTemp.getText().toString();

                if (senhaT.isEmpty() || confirS.isEmpty()) {//Verificar se estão vazios
                    UsarMetodos.alert("Não deixe em branco.",
                            getApplicationContext());
                    edtSenhaTemp.setText("");
                    edtConfirSenhaTemp.setText("");
                    edtSenhaTemp.requestFocus();

                } else {
                    Intent intent = new Intent(getApplicationContext(), ProfessorActivity.class);
                    startActivity(intent);
                    finish();
                }
            }


        });

    }
}
