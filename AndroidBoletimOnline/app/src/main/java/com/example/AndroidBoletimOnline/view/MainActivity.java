package com.example.AndroidBoletimOnline.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.AndroidBoletimOnline.API.HttpBuilder;
import com.example.AndroidBoletimOnline.API.HttpCommand;
import com.example.AndroidBoletimOnline.R;
import com.example.AndroidBoletimOnline.view.TelaRedefinirSenha;
import com.example.AndroidBoletimOnline.view.UsarMetodos;
import com.example.AndroidBoletimOnline.databinding.ActivityMainBinding;
import com.example.AndroidBoletimOnline.view.RedefinirSenhaTempAluno;
import com.example.AndroidBoletimOnline.view.RedefinirSenhaTempProfessor;

import java.sql.CallableStatement;

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

        final SharedPreferences sharedPrefs = getApplicationContext().getSharedPreferences("socorro", Context.MODE_PRIVATE);

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



        User.requestFocus();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View v) {

                String usuario = User.getText().toString(); //Obter usúario e senha dos objetos
                String senha = Pwd.getText().toString();

                HttpBuilder httpBuilder =
                        new HttpBuilder(
                                MainActivity.this,
                                "http://10.0.2.2:44372/api/Login");
               // "http://10.0.2.2:51882/api/Login");

                httpBuilder
                        .createPost()
                        .retorno(new HttpCommand.TratadorRetornoChamada() {
                            @Override
                            public void trataRetornoChamada(Object dados) {
                                MainActivity.MsgShowRetorno.newInstance((String)dados)
                                        .show(getSupportFragmentManager(), "ShowRetorno");
                            }
                        })
                        .addParam("Email", usuario)
                        .addParam("Senha", senha)
                        .execute();






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

                } else if (!sharedPrefs.getBoolean("primeiroAcesso", false)) {
                    Intent intent = new Intent(getApplicationContext(), RedefinirSenhaTempAluno.class );
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    finish();

                    return;
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

    class integracao extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            //Codigo
        }

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String doInBackgroundValueReturned) {
            //Codigo
        }

        protected void onProgressUpdate(Integer... params) {//Codigo}
        }

    }
        @Override
        protected void onDestroy() {
            super.onDestroy();
            binding = null;
        }
    public static class MsgShowRetorno extends DialogFragment {
        static MainActivity.MsgShowRetorno newInstance(String retorno) {

            MainActivity.MsgShowRetorno dialog = new MainActivity.MsgShowRetorno();
            Bundle bundle = new Bundle();
            bundle.putString("retorno", retorno);
            dialog.setArguments(bundle);
            return dialog;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            String retorno = getArguments().getString("retorno");

            //Use o Bulder(construtor) para facilitar a construcao
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(retorno)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //acao para o botão “OK”
                                    dialog.dismiss();
                                }
                            }
                    );
            //Cria a caixa de dialogo configurada nos métodos acima
            return builder.create();
        }


        }
    }


