package com.example.agendaclientesmaxprocess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton butao_add;

    MyDataBaseHelper myDB;
    ArrayList<String> cliente_id, nome, cpf,  datan, uf,  telefone,  datac, horac;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        butao_add = findViewById(R.id.butao_add);
        butao_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }

        });

        myDB = new MyDataBaseHelper (MainActivity.this);
        cliente_id = new ArrayList<>();
        nome = new ArrayList<>();
        cpf = new ArrayList<>();
        datan = new ArrayList<>();
        uf = new ArrayList<>();
        telefone = new ArrayList<>();
        datac = new ArrayList<>();
        horac = new ArrayList<>();

        storeDataInArrays ();

        customAdapter = new CustomAdapter(MainActivity.this,cliente_id, nome, cpf, datan,
                uf, telefone, datac, horac);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void storeDataInArrays () {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Sem Clientes", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()) {
                cliente_id.add(cursor.getString(0));
                nome.add(cursor.getString(1));
                cpf.add(cursor.getString(2));
                datan.add(cursor.getString(3));
                uf.add(cursor.getString(4));
                telefone.add(cursor.getString(5));
                datac.add(cursor.getString(6));
                horac.add(cursor.getString(7));

            }

        }
    }


}
