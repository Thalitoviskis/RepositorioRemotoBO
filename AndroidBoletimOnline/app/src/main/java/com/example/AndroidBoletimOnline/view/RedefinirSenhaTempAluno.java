package com.example.AndroidBoletimOnline.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.AndroidBoletimOnline.R;

public class RedefinirSenhaTempAluno extends AppCompatActivity {

    TextView tvAlert;
    EditText edtSenhaTempAluno, edtConfirSenhaTempAluno;
    Button btnOK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefinir_senha_temp_aluno);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        //lerSharedPreferences();
       /* PreferencesManager.storeInt(getApplicationContext(),
                PreferencesManager.ENTERING_FIRST_TIME, 1 );*/

        tvAlert = findViewById(R.id.tvAlert);
        edtSenhaTempAluno = findViewById(R.id.edtSenhaTempAluno);
        edtConfirSenhaTempAluno = findViewById(R.id.edtConfirSenhaTempAluno);

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

                } else if (!senhaT.equals(confirS)) {
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



