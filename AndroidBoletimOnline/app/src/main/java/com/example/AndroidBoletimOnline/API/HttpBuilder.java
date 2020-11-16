package com.example.AndroidBoletimOnline.API;
import androidx.appcompat.app.AppCompatActivity;

public class HttpBuilder {
    private String urlBase;
    private AppCompatActivity activity;

    public HttpBuilder(AppCompatActivity activity, String urlBase) {
        this.activity = activity;
        this.urlBase = urlBase;
    }

    public HttpCommand createGet() {
        return
                new HttpCommandGet(activity)
                        .urlBase(urlBase);
    }

    public HttpCommand createPost() {
        return
                new HttpCommandPost(activity)
                        .urlBase(urlBase);
    }

    //public HttpCommand createGetImage() {
        //return
                //new HttpCommandGetImage(activity)
                        //.urlBase(urlBase);
    //}

    public HttpCommand createPut(String url) {
        return
                new HttpCommandPost(activity)
                        .urlBase(urlBase + url)
                        .method("PUT");
    }

    public HttpCommand createDelete(String url) {
        return
                new HttpCommandGet(activity)
                        .urlBase(urlBase + url)
                        .method("DELETE");
    }
}
