package com.solrom.edgar.appdecontactos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //crear variables de los elementos del formulario
    private DatePicker datePicker;
    private TextInputEditText nombreUsuario;
    private TextInputEditText telefonoUsuario;
    private TextInputEditText emailUsuario;
    private TextInputEditText descripcionUsuario;
    private Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializar variables lo que esté escrito en el formulario
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        nombreUsuario = (TextInputEditText) findViewById(R.id.edit_nombre);
        telefonoUsuario = (TextInputEditText) findViewById(R.id.edit_telefono);
        emailUsuario = (TextInputEditText) findViewById(R.id.edit_email);
        descripcionUsuario = (TextInputEditText) findViewById(R.id.edit_descripcion);
        btnSiguiente = (Button) findViewById(R.id.siguiente);

        //crear listener en el boton de siguiente
        btnSiguiente.setOnClickListener(this);

        //revisar si los datos fueron recolectados al llamar esta actividad desde la actividad de confirmar_datos(con el boton)
        Bundle parametros = getIntent().getExtras();
        if (parametros != null) {
            InicializarViews(parametros);
        }

    }

    private void InicializarViews(Bundle data) {

        //inicializar el nombre
        nombreUsuario.setText(data.getString(getResources().getString(R.string.pnombre)));
        //inicializar fecha de nacimiento
        String fechaNacimiento = data.getString(getResources().getString(R.string.pfecha));
        SetDatePicker(fechaNacimiento);
        //inicializar telefono
        telefonoUsuario.setText(data.getString(getResources().getString(R.string.ptelefono)));
        //inicializar email
        emailUsuario.setText(data.getString(getResources().getString(R.string.pemail)));
        //inicializar descripcion
        descripcionUsuario.setText(data.getString(getResources().getString(R.string.pdescripcion)));

    }

    private void SetDatePicker(String fecha) {
        String[] laFecha = fecha.split("/");
        int day = Integer.parseInt(laFecha[0]);
        int year = Integer.parseInt(laFecha[2]);
        int month = 0;
        //va por el array de los meses que se hizo en strings.xml
        String[] months = getResources().getStringArray(R.array.nombremes);
        //encontrar el index en el array
        for (int i = 0; i < months.length; i++) {
            if (months[i].equals(laFecha[1])) {
                month = i;
                break;
            }
        }

        datePicker.init(year, month, day, null);
    }

    private String ConstruirFecha() {
        // leer el view del datepicker
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        // cambiar el mes a string en vez de integer
        String mesString = GetMonth(month);

        // concatenar la fecha
        return day + "/" + mesString + "/" + year;
    }

    private String GetMonth(int month) {
        String[] meses = getResources().getStringArray(R.array.nombremes);
        return meses[month];
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.siguiente) {
            IrAConfirmarDatos();
        }
    }

    private void IrAConfirmarDatos(){

        //crear un intent que lleve a la activity de confirmar_datos
        Intent intentConfirmarDatosActivity = new Intent(this, confirmar_datos.class);
        //poner los datos en el intent:
        //el nombre
        intentConfirmarDatosActivity.putExtra(getResources().getString(R.string.pnombre), nombreUsuario.getText().toString());
        //la fecha de nacimiento
        String fechanacimiento = ConstruirFecha();
        intentConfirmarDatosActivity.putExtra(getResources().getString(R.string.pfecha), fechanacimiento);
        //el teléfono
        intentConfirmarDatosActivity.putExtra(getResources().getString(R.string.ptelefono), telefonoUsuario.getText().toString());
        //el email
        intentConfirmarDatosActivity.putExtra(getResources().getString(R.string.pemail), emailUsuario.getText().toString());
        //la descripción
        intentConfirmarDatosActivity.putExtra(getResources().getString(R.string.pdescripcion), descripcionUsuario.getText().toString());
        //iniciar la actividad
        startActivity(intentConfirmarDatosActivity);
        //dar por acabada la actividad
        finish();
    }


}

