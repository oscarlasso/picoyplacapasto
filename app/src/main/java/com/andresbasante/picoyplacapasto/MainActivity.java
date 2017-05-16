package com.andresbasante.picoyplacapasto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String[] PYP = {"4 - 5", "6 - 7", "8 - 9","0 - 1", "2 - 3"};
    private static final Integer[] FESTIVOS = {1, 9, 79, 103, 104, 121, 149, 170, 177, 184, 201, 219, 233, 289, 310, 317, 342, 359};

    TextView tv1,p,tv_placa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String picoYplacaHoy;
        tv1 = (TextView) findViewById(R.id.tv1);
        p=(TextView)findViewById(R.id.p);
        tv_placa=(TextView) findViewById(R.id.tv_placa);
        SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        tv_placa.setText(prefe.getString("placa","0-1"));

        Calendar localCalendar = Calendar.getInstance();
        int diaAnio = localCalendar.get(Calendar.DAY_OF_YEAR);
        int diaSemana = localCalendar.get(Calendar.DAY_OF_WEEK);
        if(Arrays.asList(FESTIVOS).contains(diaAnio) || diaSemana == 1 || diaSemana == 7) {// si es festivo o sabado o domingo
            picoYplacaHoy="NO APLICA";
        }else{
            int j=diaAnio;
            while(j>7){
                j=localCalendar.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY?j-11:j-6;//si es viernes resta 11 y si es otro dia resta 6
                localCalendar.set(Calendar.DAY_OF_YEAR, j);// cambia la fecha a la fecha restada
            }
            picoYplacaHoy=PYP[localCalendar.get(Calendar.DAY_OF_YEAR)-2];//se resta dos porque lunes comenzo el dia 2
        }
        tv1.setText(" "+picoYplacaHoy+" ");

        if (prefe.getString("spinnerSelection", "").equals("0"))

        {
            p.setText("0-1");
        }

        if (prefe.getString("spinnerSelection", "").equals("1"))

        {
            p.setText("2-3");
        }

        if (prefe.getString("spinnerSelection", "").equals("2"))

        {
            p.setText("4-5");
        }

        if (prefe.getString("spinnerSelection", "").equals("3"))

        {
            p.setText("6-7");
        }

        if (prefe.getString("spinnerSelection", "").equals("4"))

        {
            p.setText("8-9");
        }

        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        int dia = today.monthDay;
        int mes = today.month;
        int ano = today.year;
        mes = mes + 1;
        tv1.setText("" + mes + "" + "/" + dia + "" + "/" + ano);


    }




    public void configuracion(View view) {
        Intent i = new Intent(this, configuracion.class);
        startActivity(i);
    }


    public void acercade(View view) {
        Intent i = new Intent(this, AcercaDe.class );
        startActivity(i);
    }
}









