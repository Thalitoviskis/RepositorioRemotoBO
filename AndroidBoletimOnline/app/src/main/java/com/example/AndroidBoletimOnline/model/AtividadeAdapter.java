package com.example.AndroidBoletimOnline.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AndroidBoletimOnline.R;

import java.util.ArrayList;

public class AtividadeAdapter extends RecyclerView.Adapter<AtividadeAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Atividade> atividade;
    private String url = "";

    public AtividadeAdapter(Context context, ArrayList<Atividade> atividade, String url) {
        this.context = context;
        this.atividade = atividade;
        this.url = url;
    }

    @NonNull
    @Override
    public AtividadeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.lista_atividade, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AtividadeAdapter.MyViewHolder holder, int position) {
        holder.title.setText(atividade.get(position).getNome_atividade());
        holder.no.setText("#" + String.valueOf(position+1));

    }

    @Override
    public int getItemCount() {
        return atividade.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title,  no;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            no = (TextView) itemView.findViewById(R.id.no);

            title = (TextView) itemView.findViewById(R.id.nomeAtv);
        }
    }
}
