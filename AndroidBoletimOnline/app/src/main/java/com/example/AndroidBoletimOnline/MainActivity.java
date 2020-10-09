package com.example.AndroidBoletimOnline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.AndroidBoletimOnline.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity {
    EditText User, Pwd;
    Button btnLogin;
    TextView btnRedefinirSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User = findViewById(R.id.edtUser);
        Pwd = findViewById(R.id.edtPwd);

        btnLogin = findViewById(R.id.btnLogin);
        btnRedefinirSenha = findViewById(R.id.btnRedefinirSenha);

        User.requestFocus();

        btnLogin.setOnClickListener(new View.OnClickListener() {
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
                } else {
                    Intent intent = new Intent(getApplicationContext(),TelaPrincipal.class);
                    intent.putExtra("chave", usuario);
                    startActivity(intent);
                }
            }

        });
        btnRedefinirSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TelaRedefinirSenha.class);
                startActivity( intent );
            }
        });
    }
}