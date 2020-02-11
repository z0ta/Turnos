package com.uisrael.turnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {

    ImageButton ibpac, ibmed, ibespe, ibhorario, ibturnos, ibfb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ibpac = (ImageButton) findViewById(R.id.ibpac);
        ibmed = (ImageButton) findViewById(R.id.ibmed);
        ibespe = (ImageButton) findViewById(R.id.ibespe);
        ibturnos = (ImageButton) findViewById(R.id.ibturnos);
        ibhorario = (ImageButton) findViewById(R.id.ibhorario);
        ibfb = (ImageButton) findViewById(R.id.ibfb);

        ibfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(browserIntent);

            }
        });

        ibturnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int5 = new Intent(Menu.this, registroTurnos.class);
                startActivity(int5);
            }
        });

        ibhorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int4 = new Intent(Menu.this, registroHorarios.class);
                startActivity(int4);
            }
        });

        ibespe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int3 = new Intent(Menu.this, registroEspecialidades.class);
                startActivity(int3);
            }
        });

        ibmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(Menu.this, registroMedico.class);
                startActivity(int2);
            }
        });
        ibpac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(Menu.this, registroPac.class);
                startActivity(int1);
            }
        });


    }


}




