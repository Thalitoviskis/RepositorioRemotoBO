package com.example.AndroidBoletimOnline.API;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class HttpCommandGet extends HttpCommandBase {

    public HttpCommandGet(AppCompatActivity activity) {
        super(activity);
        this.method("GET");
    }

    protected String getUrl(String urlBase) {
        return urlBase + "?" + getParans();
    }

    @Override
    protected Object readResponse(InputStream inputStream) throws IOException {
        return super.readString(inputStream);
    }

}
