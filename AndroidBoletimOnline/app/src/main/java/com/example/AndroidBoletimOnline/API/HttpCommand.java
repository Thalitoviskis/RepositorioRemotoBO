package com.example.AndroidBoletimOnline.API;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.AndroidBoletimOnline.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public abstract class HttpCommand extends AsyncTask<Void, Void, Object> {
    private AppCompatActivity activity;
    private String urlBase;
    private TratadorRetornoChamada tratadorRetorno;
    private TratadorCancelamento tratadorCancelamento;
    private TelaCarregamento progressDialog;
    private Map<String, String> parans = new HashMap<>();
    protected String method;
    protected String body = null;
    protected String encType = "application/x-www-form-urlencoded";

    public interface TratadorRetornoChamada {
        public void trataRetornoChamada(Object retorno);
    }

    public interface TratadorCancelamento {
        public void trataCancelamento();
    }


    public HttpCommand(AppCompatActivity activity) {
        this.activity = activity;
        this.tratadorRetorno = null;
        this.tratadorCancelamento = null;
        this.progressDialog = TelaCarregamento.newInstance();
        this.progressDialog.setRequisicaoHttp(this);
   }



    public HttpCommand addParam(String chave, String valor) {
        this.parans.put(chave, valor);
        return this;
    }

    public HttpCommand method(String method) {
        this.method = method;
        return this;
    }

    public HttpCommand jsonBody(String body) {
        this.body = body;
        return this;
    }

    public HttpCommand encType(String encType) {
        this.encType = encType;
        return this;
    }

    public HttpCommand retorno(TratadorRetornoChamada tratadorRetorno) {
        this.tratadorRetorno = tratadorRetorno;
        return this;
    }

    public HttpCommand cancelamento(TratadorCancelamento tratadorCancelamento) {
        this.tratadorCancelamento = tratadorCancelamento;
        return this;
    }

    public HttpCommand urlBase(String urlBase) {
        this.urlBase = urlBase;
        return this;
    }

    @Override
    protected Object doInBackground(Void... params) {
        // Esta etapa é usada para executar a tarefa em background ou fazer a chamada para o webservice
        HttpURLConnection urlConnection = null;
        OutputStreamWriter request = null;
        BufferedReader reader = null;

        try {
            //Se for GET/DELETE os parametros vao na url
            //Se for POST/PUT nao tem parametro na url
            String urlStr = getUrl(this.urlBase);//url base
            URL url = new URL(urlStr);

            urlConnection = sendRequest(url);

            Object resposta = readResponse(urlConnection);


            return resposta;
        } catch (Exception e) {
            e.printStackTrace();
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }

    protected HttpURLConnection sendRequest(URL url) throws IOException {
        String body = getBody();
        HttpURLConnection urlConnection = getHttpURLConnection(url);

        //configura cabecalho
        configUrlConnection(urlConnection, body);

        //Se for GET/DELETE nao tem corpo
        //Se for POST/PUT tem corpo
        sendHttpBody(urlConnection, body);

        //urlConnection.connect();

        return urlConnection;
    }

    protected abstract String getBody();


    /*Esse eh diferente entre POST e GET*/
    protected String getUrl(String urlBase) {
        return urlBase;
    }

    private Object readResponse(HttpURLConnection urlConnection) throws IOException {
        Object resposta = null;
        if (urlConnection.getResponseCode() >= 200
                && urlConnection.getResponseCode() <= 299) {

            InputStream inputStream = urlConnection.getInputStream();
            resposta = readResponse(inputStream);
        }
        return resposta;
    }

    protected abstract Object readResponse(InputStream inputStream) throws IOException;

    /*Esse eh diferente entre POST e GET*/
    protected abstract void sendHttpBody(HttpURLConnection urlConnection, String body)
            throws IOException;

    /*Esse eh diferente entre POST e GET*/
    protected HttpURLConnection getHttpURLConnection(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setReadTimeout(10000);
        urlConnection.setConnectTimeout(15000);
        urlConnection.setRequestMethod(this.method);//POST ou GET
        //urlConnection.connect();

        return urlConnection;
    }

    public abstract void configUrlConnection(HttpURLConnection urlConnection, String body);

    protected Object readString(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String linha;
        StringBuffer buffer = new StringBuffer();
        while((linha = reader.readLine()) != null) {
            buffer.append(linha);
            buffer.append("\n");
        }

        return buffer.toString();
    }

   protected String getParans()  {
        StringBuilder formParans = new StringBuilder();
        if (this.parans.size() > 0) {
            //parans.append("?");
            Set<Map.Entry<String, String>> entradas = this.parans.entrySet();

            for (Map.Entry<String, String> entrada : entradas) {

                formParans.append(entrada.getKey())
                        .append("=")
                        .append(entrada.getValue())
                        .append("&");

            }
        }

        String parans = formParans.substring(0, Math.max(0, formParans.length() - 1)).toString();
        //parans = URLEncoder.encode(parans, "UTF-8");
        return parans;
    }

    @Override
    protected void onPreExecute(){
        Log.i(
                "AsyncTask",
                "Exibindo TelaCarregamento na tela Thread: " +
                        Thread.currentThread().getName());
        this.progressDialog.show(activity.getSupportFragmentManager(), "carregamento");


    }
    @Override
    protected void onPostExecute(Object dados) {
        // Faça alguma coisa com os dados
        if (this.tratadorRetorno != null) {
            this.tratadorRetorno.trataRetornoChamada(dados);
        }
        this.progressDialog.dismiss();
    }

    public static class TelaCarregamento extends DialogFragment {

        private HttpCommand requisicaoHttp;

        public void setRequisicaoHttp(HttpCommand requisicaoHttp) {
            this.requisicaoHttp = requisicaoHttp;
        }

        static TelaCarregamento newInstance() {
            return new TelaCarregamento();
        }

        @Override
        public void onCancel(DialogInterface dialog) {
            Log.i("TelaCarregamento", "onCancel");
            this.requisicaoHttp.cancelar();
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //Use o Bulder(construtor) para facilitar a construcao
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            //obtem o inflater
            LayoutInflater inflater = requireActivity().getLayoutInflater();
            //"Infla" a view tela_carregamento
            View view = inflater.inflate(R.layout.activity_tela_carregamento, null);
            builder.setView(view)
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //acao para o botao "Cancelar"
                            Log.i("TelaCarregamento", "Clicou Cancelar");
                            dialog.cancel();
                        }
                    });

            //Cria a caixa de dialogo configurada nos metodos acima
            return builder.create();
        }
    }

    private void cancelar() {
        if (tratadorCancelamento != null) {
            tratadorCancelamento.trataCancelamento();
        }
        this.tratadorRetorno = null;
    }
}
