package com.example.AndroidBoletimOnline.API;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpCommandPost extends HttpCommandBase {

    public HttpCommandPost(AppCompatActivity activity) {
        super(activity);
        this.method("POST");
    }

    @Override
    public void configUrlConnection(HttpURLConnection urlConnection, String body) {
        //configura cabecalho
        int tamanhoCorpo = body.getBytes().length;//body tem o texto a ser enviado como corpo
        Log.i("tamanhoCorpo", "tamanhoCorpo(String)=" + body.length());
        Log.i("tamanhoCorpo", "tamanhoCorpo(Bytes)=" + tamanhoCorpo);
        urlConnection.setFixedLengthStreamingMode(tamanhoCorpo);
        urlConnection.setDoInput(true); // Allow Inputs
        urlConnection.setDoOutput(true); // Allow Outputs
        urlConnection.setUseCaches(false); // Don't use a Cached Copy
        urlConnection.setRequestProperty("Connection", "Keep-Alive");
        urlConnection.setRequestProperty("ENCTYPE", super.encType);
        urlConnection.setRequestProperty("Content-Type", super.encType);
    }

    protected void sendHttpBody(HttpURLConnection urlConnection, String body)
            throws IOException {
        OutputStreamWriter request;
        request = new OutputStreamWriter(
                urlConnection.getOutputStream(),
                "utf-8");

        request.write(body);
        request.flush();
        request.close();
    }

    protected String getBody() {
        if (super.body != null) {
            return super.body;
        }
        String formParans = getParans();
        return formParans;
    }
}
