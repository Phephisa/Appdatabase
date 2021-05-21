package com.example.app3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bt1, bt2, save;
    EditText edt1, edt2;
    public static int hour, month, year, minute, day;
    private DBHandler dbHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        save = findViewById(R.id.save);
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);

        dbHandler = new DBHandler(MainActivity.this);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void ButtonClicked(View v)
    {
        switch(v.getId()) {
            case R.id.bt1:

                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                //launch date picker
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayofMonth) {
                                edt1.setText(dayofMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();


            case R.id.bt2:
                Calendar b = Calendar.getInstance();
                hour = b.get(Calendar.HOUR_OF_DAY);
                minute = b.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {
                        edt2.setText(hour + ":" + minute);
                    }
                }, hour, minute, false);
                timePickerDialog.show();
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getting text from string values
                String date = edt1.getText().toString();
                String time = edt2.getText().toString();

                //check i ffields are empty o rnot
                if (date.isEmpty() && time.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter values", Toast.LENGTH_SHORT).show();
                return;
            }
                dbHandler.addDateTime(date, time);
                //add then toast and clear
                Toast.makeText(MainActivity.this, "Data has been added", Toast.LENGTH_SHORT).show();
                edt1.setText("");
                edt2.setText("");

            }
        });


        //
    }
}

