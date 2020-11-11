package com.example.AndroidBoletimOnline;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Tudo que ocorre no inicio do app > Fica em tela cheia
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        User = findViewById(R.id.edtUser);
        Pwd = findViewById(R.id.edtPwd);

        btnLogin = findViewById(R.id.btnLogin);
        btnRedefinirSenha = findViewById(R.id.btnRedefinirSenha);


        /* SharedPreferences sharedPrefs = getApplicationContext().getSharedPreferences("primeiro acesso", Context.MODE_PRIVATE);

        if (!sharedPrefs.getBoolean("primeiroAcesso", false)) {
            Intent intent = new Intent(this, RedefinirSenhaTempActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();

            return;
        }
        */

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
                        Intent intent = new Intent(getApplicationContext(),AlunoActivity .class);
                        startActivity(intent);

                    } else if (binding.rbProfessor.isChecked()) {
                        Intent intent = new Intent(getApplicationContext(),RedefinirSenhaTempActivity.class);
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
                    Intent intent = new Intent(getApplicationContext(), TelaRedefinirSenha.class);
                    startActivity(intent);
                }
            });

        }
        @Override
        protected void onDestroy () {
            super.onDestroy();
            binding = null;
        }
    }

