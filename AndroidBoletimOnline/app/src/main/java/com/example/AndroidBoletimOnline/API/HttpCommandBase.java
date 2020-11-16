package com.example.AndroidBoletimOnline.API;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public abstract class HttpCommandBase extends HttpCommand {

    public HttpCommandBase(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    protected Object readResponse(InputStream inputStream) throws IOException {
        return super.readString(inputStream);
    }

    @Override
    public void configUrlConnection(HttpURLConnection urlConnection, String bddy) {

    }

    /*Assumindo como padrao que nao tem corpo na requisicao como
    * no GET/DELETE*/
    protected void sendHttpBody(HttpURLConnection urlConnection, String body) throws IOException {

    }

    /*Assumindo como padrao que nao tem corpo na requisicao como
     * no GET/DELETE*/
    @Override
    protected String getBody() {
        return "";
    }
}
