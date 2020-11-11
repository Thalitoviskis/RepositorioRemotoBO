package com.example.AndroidBoletimOnline.model;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.AndroidBoletimOnline.R;
import com.example.AndroidBoletimOnline.view.TelaRedefinirSenha;
import com.example.AndroidBoletimOnline.view.UsarMetodos;
import com.example.AndroidBoletimOnline.databinding.ActivityMainBinding;
import com.example.AndroidBoletimOnline.view.RedefinirSenhaTempAluno;
import com.example.AndroidBoletimOnline.view.RedefinirSenhaTempProfessor;

public class MainActivity extends AppCompatActivity {



    EditText User, Pwd;
    Button btnLogin;
    TextView btnRedefinirSenha, btnSair;
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

        btnSair = findViewById(R.id.btnSair);
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exibirConfirmaçãoDois();
            }
        });


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
                    UsarMetodos.alert("Preencha todos os campos.",
                            getApplicationContext());

                } else if (UsarMetodos.login(usuario, senha) == false) {//Passa usúario e senha
                    UsarMetodos.alert("Usuário ou senha inválidos.",
                            getApplicationContext());
                    User.setText("");
                    Pwd.setText("");
                    User.requestFocus();

                } else if (binding.rbAluno.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), RedefinirSenhaTempAluno.class);
                    startActivity(intent);

                } else if (binding.rbProfessor.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), RedefinirSenhaTempProfessor.class);
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

    public void exibirConfirmaçãoDois() {
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Sair");
        msgBox.setMessage("Tem certeza que deseja sair?");
        msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        msgBox.show();
    }


    @Override
        protected void onDestroy () {
            super.onDestroy();
            binding = null;
        }
    }

