package com.example.AndroidBoletimOnline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class OutraTela extends AppCompatActivity {

    ListView listView;
    Button btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outra_tela);

        btnSair = findViewById(R.id.btnSair);

        listView = findViewById(R.id.lisView);

        String[] relatorioGeral={"GUSTAVO HENRIQUE:",
                "ASOO = MÉDIA: 7,0",
                "PTI = MÉDIA: 7,0",
                "BADA2 = MÉDIA: 7,0",
                "LIP2 = MÉDIA: 7,0",
                "LIPR = MÉDIA: 7,0,",
                "PRAP2 = MÉDIA: 7,0"};

        listView.setAdapter(new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                relatorioGeral));



        //obter de Bundle os valores salvos
        Bundle extra = getIntent().getExtras();
        String usuarioInformado = "";
        if (extra != null) { //Verifica se btnSair.setOnClickListener(new View.OnClickListener() {
                finish();
            }
            //Obetem de extra valor salvo com a mesma chave usada
            usuarioInformado = extra.getString("chave");
        }
        //Apresenta o usúario logado

    }
