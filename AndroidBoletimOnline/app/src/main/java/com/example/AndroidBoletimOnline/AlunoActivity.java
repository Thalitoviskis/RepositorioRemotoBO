package com.example.AndroidBoletimOnline;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;

import com.example.AndroidBoletimOnline.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AlunoActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        //Tudo que está abaixo do setContentView está relacionado as outras partes do layout.


        //Configura barra de navegação
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //código responsavel pelo icone de Mensagem
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exibirConfirmação();
                }
               //finish();
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
            }
    );

        //Cria referencia para toda a area do Navigation Drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        //Cria referencia para a area de navegação
        NavigationView navigationView = findViewById(R.id.nav_view);


        //Define configurações do navigation drawer
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio, R.id.nav_boletim,R.id.nav_sair)
                .setDrawerLayout(drawer)
                .build();

        //Configura a area que carrega os fragments
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        //Responsavel pelo botão do lado que abre o menu gaveta
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        //Configura a navegação para o navigation view(se eu clico em algum icone do menu, ele nao abre)
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void exibirConfirmação () {
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Sair");
        msgBox.setIcon(R.drawable.ic_menu_sair);
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
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
