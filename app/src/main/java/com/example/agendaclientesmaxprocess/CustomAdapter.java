package com.example.agendaclientesmaxprocess;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
        private Context context;
        private ArrayList cliente_id, nome_txt, cpf_id, datanasc_id, uf_id, telefone_id, datacad_id, hora_id;

        CustomAdapter(Context context, ArrayList cliente_id, ArrayList nome_txt, ArrayList cpf_id,
                      ArrayList datanasc_id, ArrayList uf_id, ArrayList telefone_id, ArrayList datacad_id,
                      ArrayList hora_id){
            this.context = context;
            this.cliente_id = cliente_id;
            this.nome_txt = nome_txt;
            this.cpf_id = cpf_id;
            this.datanasc_id = datanasc_id;
            this.uf_id = uf_id;
            this.telefone_id = telefone_id;
            this.datacad_id = datacad_id;
            this.hora_id = hora_id;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.my_row, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.cliente_id.setText(String.valueOf(cliente_id.get(position)));
            holder.nome_txt.setText(String.valueOf(nome_txt.get(position)));
            holder.cpf_id.setText(String.valueOf(cpf_id.get(position)));
            holder.datanasc_id.setText(String.valueOf(datanasc_id.get(position)));
            holder.uf_id.setText(String.valueOf(uf_id.get(position)));
            holder.telefone_id.setText(String.valueOf(telefone_id.get(position)));
            holder.datacad_id.setText(String.valueOf(datacad_id.get(position)));
            holder.hora_id.setText(String.valueOf(hora_id.get(position)));

               }

        @Override
        public int getItemCount() {
            return cliente_id.size();
        }

        public class MyViewHolder extends  RecyclerView.ViewHolder {

            TextView  cliente_id, nome_txt, cpf_id, datanasc_id, uf_id,telefone_id, datacad_id, hora_id; //add aqui


            public MyViewHolder(@NonNull View itemView) { //envia os dados pro myrow
                super(itemView);
                cliente_id = itemView.findViewById(R.id.cliente_id);
                nome_txt = itemView.findViewById(R.id.nome_txt);
                cpf_id = itemView.findViewById(R.id.cpf_txt);
                datanasc_id = itemView.findViewById(R.id.datanasc_txt);
                uf_id = itemView.findViewById(R.id.UF_txt);
                telefone_id = itemView.findViewById(R.id.telefone_txt);
                datacad_id = itemView.findViewById(R.id.datacad_txt);
                hora_id = itemView.findViewById(R.id.hora_txt);


            }
        }
    }
