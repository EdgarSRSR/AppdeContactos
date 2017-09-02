package com.solrom.edgar.appdecontactos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.solrom.edgar.appdecontactos.R;

public class confirmar_datos extends AppCompatActivity implements View.OnClickListener {

    //aquí se van a crear la variables de los elementos de la interfaz o sea del activity_confirmar_datos.xml:
    TextView tvnombre;
    TextView tvfecha;
    TextView tvTelefono;
    TextView tvemail;
    TextView tvdescripcion;
    Button btnconfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);

        //establece las rferencias de los text views para que se pueda actualizar el contenido
        InicializarViews();
        //obtiene el bundle para extraer los parametros
        Bundle parametros = getIntent().getExtras();
        //actualiza los datos de la interfaz con los datos obtenidos al iniciar la actividad
        InitializeViewElements(parametros);



        /*
        Intent intent = getIntent();
        String messagenombre = intent.getStringExtra(MainActivity.Extra_Nombre);
        String messagetelefono = intent.getStringExtra(MainActivity.Extra_Telefono);

        TextView tvNombre = (TextView) findViewById(R.id.edit_nombre);
        tvNombre.setTextSize(30);
        tvNombre.setText(messagenombre);

        TextView textViewtel = new TextView(this);
        textViewtel.setTextSize(30);
        textViewtel.setText(messagetelefono);
        textViewtel.setId('t');

        //TextView tvtelefono = (TextView) findViewById(R.id.edit_telefono);
        //tvtelefono.setTextSize(30);
        //tvtelefono.setText(messagetelefono);

        RelativeLayout.LayoutParams paramsnombre = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsnombre.addRule(RelativeLayout.BELOW, R.id.confirmacion);

        RelativeLayout.LayoutParams paramstelefono = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsnombre.addRule(RelativeLayout.BELOW, R.id.edit_nombre);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_confirmar_datos);
        layout.addView(tvNombre, paramsnombre);
        layout.addView(tvtelefono, paramstelefono);
        */

    }

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.editardatos){
            IrAMainActivity();
        }
    }

    private void InitializeViewElements(Bundle data){
        //actualizar nombre
        tvnombre.setText(data.getString(getResources().getString(R.string.pnombre)));
        //actualizar fecha de nacimiento
        tvfecha.setText(data.getString(getResources().getString(R.string.pfecha)));
        //actualizar teléfono
        tvTelefono.setText(data.getString(getResources().getString(R.string.ptelefono)));
        //actualizar email
        tvemail.setText(data.getString(getResources().getString(R.string.pemail)));
        //actualizar descripción de contactos
        tvdescripcion.setText(data.getString(getResources().getString(R.string.pdescripcion)));
    }

    public void IrAMainActivity(){
        Intent irAMainActivity = new Intent(this, MainActivity.class);
        //colocar todos los extras en el intent para que la main activity su pueda inicializar
        //el nombre
        irAMainActivity.putExtra(getResources().getString(R.string.pnombre), tvnombre.getText().toString());
        //la fecha de nacimiento
        irAMainActivity.putExtra(getResources().getString(R.string.pfecha), tvfecha.getText().toString());
        //el teléfono
        irAMainActivity.putExtra(getResources().getString(R.string.ptelefono), tvTelefono.getText().toString());
        //el email
        irAMainActivity.putExtra(getResources().getString(R.string.pemail), tvemail.getText().toString());
        //la descripción
        irAMainActivity.putExtra(getResources().getString(R.string.pdescripcion), tvdescripcion.getText().toString());
        //iniciar la actividad
        startActivity(irAMainActivity);
        //dar por terminada la actividad
        finish();

    }

    private void InicializarViews(){
        tvnombre = (TextView) findViewById(R.id.tvnombre);
        tvfecha = (TextView) findViewById(R.id.tvfecha);
        tvTelefono = (TextView) findViewById(R.id.tvtelefono);
        tvemail = (TextView) findViewById(R.id.tvemail);
        tvdescripcion = (TextView) findViewById(R.id.tvdescripcion);
        btnconfirmar = (Button) findViewById(R.id.editardatos);
        btnconfirmar.setOnClickListener(this);
    }
}
