package com.example.AndroidBoletimOnline;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.AndroidBoletimOnline.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    EditText User, Pwd;
    Button btnLogin;
    TextView btnRedefinirSenha;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        User = findViewById(R.id.edtUser);
        Pwd = findViewById(R.id.edtPwd);

        btnLogin = findViewById(R.id.btnLogin);
        btnRedefinirSenha = findViewById(R.id.btnRedefinirSenha);

        User.requestFocus();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View v) {

                String usuario = User.getText().toString(); //Obter usúario e senha dos objetos
                String senha = Pwd.getText().toString();

                if (usuario.isEmpty() || senha.isEmpty()) {//Verificar se estão vazio
                    UsarMetodos.alert("Não deixe em branco.",
                            getApplicationContext());

                } else if (UsarMetodos.login(usuario, senha) == false) {//Passa usúario e senha
                    UsarMetodos.alert("Usuário ou senha inválidos.",
                            getApplicationContext());
                    User.setText("");
                    Pwd.setText("");
                    User.requestFocus();

                } else if (binding.rbAluno.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), AlunoActivity.class);
                    startActivity(intent);

                } else if (binding.rbProfessor.isChecked()) {
                         Intent intent = new Intent(getApplicationContext(), ProfessorActivity.class);
                         startActivity(intent);
                     }
                 }

                //} else {
                    //Intent intent = new Intent(getApplicationContext(), AlunoActivity.class);
                    //intent.putExtra("chave", usuario);
                    //startActivity(intent);
                //}
            //}

        });
        btnRedefinirSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TelaRedefinirSenha.class);
                startActivity( intent );
            }
        });

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        binding=null;
    }

}