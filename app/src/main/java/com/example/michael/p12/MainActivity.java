package com.example.michael.p12;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainAcivity";
    private TextView eDate, tInfo;
    private DatePickerDialog.OnDateSetListener mDate;
    private String usuario, password, mail, rpassword, sexo, hobbies = "", ciudad, fecha;
    private EditText eUsuario, ePassword, eRpassword, eMail;
    private RadioButton rMasculino, rFemenino;
    private CheckBox cGaming, cDormir, cComer, cLeer;
    private Spinner sCiudades;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eUsuario = (EditText) findViewById(R.id.eUsuario);
        ePassword = (EditText) findViewById(R.id.ePassword);
        eRpassword = (EditText) findViewById(R.id.eRpassword);
        tInfo = (TextView) findViewById(R.id.tInfo);
        eMail = (EditText) findViewById(R.id.eMail);
        rMasculino = (RadioButton) findViewById(R.id.rMasculino);
        rFemenino = (RadioButton) findViewById(R.id.rFemenino);
        cGaming = (CheckBox) findViewById(R.id.cGaming);
        cDormir = (CheckBox) findViewById(R.id.cDormir);
        cComer = (CheckBox) findViewById(R.id.cComer);
        cLeer = (CheckBox) findViewById(R.id.cLeer);
        sCiudades = (Spinner) findViewById(R.id.sCiudades);
        eDate = (TextView) findViewById(R.id.eDate);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ciudades, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sCiudades.setAdapter(adapter);

        sCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ciudad = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        eDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int año = cal.get(Calendar.YEAR);
                int mes = cal.get(Calendar.MONTH);
                int dia = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDate, año, mes, dia);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int año, int mes, int dia) {
                mes = mes + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + mes + "/" + dia + "/" + año);

                String fecha = " " + mes + "/" + dia + "/" + año;
                eDate.setText(fecha);
            }
        };
    }


    public void Aceptar(View view) {
        usuario = eUsuario.getText().toString();
        password = ePassword.getText().toString();
        rpassword = eRpassword.getText().toString();
        mail = eMail.getText().toString();
        fecha = eDate.getText().toString();
        hobbies = "";

        if (cGaming.isChecked()) {
            hobbies += "Gaming ";
        }
        if (cDormir.isChecked()) {
            hobbies += "Dormir ";
        }
        if (cComer.isChecked()) {
            hobbies += "Comer ";
        }
        if (cLeer.isChecked()) {
            hobbies += "Leer ";
        }

        if (rMasculino.isChecked()) {
            sexo = "Masculino";
        } else {
            sexo = "Femenino";
        }



        if (!TextUtils.isEmpty(usuario) && !TextUtils.isEmpty(password)&&
            !TextUtils.isEmpty(rpassword) && !TextUtils.isEmpty(mail) &&
            !TextUtils.isEmpty(fecha) && !TextUtils.isEmpty(ciudad) && !TextUtils.isEmpty(hobbies) && !TextUtils.isEmpty(sexo)) {

            if (password.equals(rpassword)) {


                tInfo.setText("Usuario: " + usuario + " \nPassword: " + password + " \nE-mail: " + mail + " \nFecha de nacimiento : " + fecha +
                        " \nSexo: " + sexo + " \nhobbies: " + hobbies + " \nCiudad: " + ciudad);
            } else {
                tInfo.setText("Porfavor verifique que los password cocincidan !");
                ePassword.setText("");
                eRpassword.setText("");
                ePassword.setError("Digite nuevamente");

            }

        }

        else {tInfo.setText("Faltan campos por llenar");

        if(TextUtils.isEmpty(usuario)){eUsuario.setError("Digite el dato ");}
        if(TextUtils.isEmpty(password)){ePassword.setError("Digite el dato ");}
        if(TextUtils.isEmpty(rpassword)){eRpassword.setError("Digite el dato ");}
        if(TextUtils.isEmpty(mail)){eMail.setError("Digite el dato ");}
        if(TextUtils.isEmpty(fecha)){eDate.setError("Digite el dato ");}
        if(TextUtils.isEmpty(hobbies)){tInfo.setText("Faltan campos por llenar" + "\nSeleccione algun hobbie");}

        }




    }
}



