package com.example.agendaclientesmaxprocess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    EditText edtDATE;
    EditText etDate;
    TextView tvHOUR;
    EditText MascCel;
    EditText MascCPF;
    EditText MascUF;

    EditText nome_input, cpf_input, datan_input, datac_input, uf_input, telefone_input;
    TextView hora_input;
    Button butao_add;

    int tvHour, tvMinute;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        nome_input = findViewById(R.id.text_Nome);
        cpf_input = findViewById(R.id.text_CPF);
        datan_input = findViewById(R.id.text_Data);
        uf_input = findViewById(R.id.text_UF);
        telefone_input = findViewById(R.id.text_Telefone);
        datac_input = findViewById(R.id.text_DataCadastro);
        hora_input = findViewById(R.id.text_HoraC);
        butao_add = findViewById(R.id.butao_Cadastar);
        butao_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (uf_input.equals("SP") && cpf_input.length()<8){
                    cpf_input.setError("CPF OBRIGATORIO");
                } else {
                    MyDataBaseHelper myDB = new MyDataBaseHelper(AddActivity.this);
                    myDB.addCliente(nome_input.getText().toString().trim(),
                            cpf_input.getText().toString().trim(),
                            datan_input.getText().toString().trim(),
                            uf_input.getText().toString().trim(),
                            telefone_input.getText().toString().trim(),
                            datac_input.getText().toString().trim(),
                            hora_input.getText().toString().trim());

                }


            }
        });


        // Máscara de celular
        MascCel = findViewById(R.id.text_Telefone);
        SimpleMaskFormatter smf = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(MascCel, smf);
        MascCel.addTextChangedListener(mtw);

        // Máscara CPF
        MascCPF  = findViewById(R.id.text_CPF);
        SimpleMaskFormatter smf2 = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw2 = new MaskTextWatcher(MascCPF, smf2);
        MascCPF.addTextChangedListener(mtw2);

        //Máscara UF
        MascUF  = findViewById(R.id.text_UF);
        SimpleMaskFormatter smf3 = new SimpleMaskFormatter("UU");
        MaskTextWatcher mtw3 = new MaskTextWatcher(MascUF, smf3);
        MascUF.addTextChangedListener(mtw3);

        //Aqui começa o DatePicker

        etDate = findViewById(R.id.text_Data);
        edtDATE = findViewById(R.id.text_DataCadastro);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        //DatePicker data de nascimento
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        etDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();


            }
        });

        // DatePicker da data de cadastro
        edtDATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        edtDATE.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();


            }
        });


        // Hora

        tvHOUR = findViewById(R.id.text_HoraC);

        tvHOUR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inicializa o timePickerDialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        AddActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //inicializando hora minuto
                                tvHour = hourOfDay;
                                tvMinute = minute;

                                String time = tvHour + ":" + tvMinute;
                                //inicializando o formato 24 horas
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );

                                try {
                                    Date date = f24Hours.parse(time);
                                    //inicializando o formato 12 horas
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    //Setando a hora no TextView
                                    tvHOUR.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false

                );

                //setando o background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //setando a tela de seleção do horario
                timePickerDialog.updateTime(tvHour, tvMinute);
                //show dialog
                timePickerDialog.show();
            }
        });






    }
}
