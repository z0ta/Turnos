package com.uisrael.turnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {

    ImageButton ibpac,ibmed,ibespe,ibhorario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ibpac = (ImageButton) findViewById(R.id.ibpac);
        ibmed = (ImageButton) findViewById(R.id.ibmed);
        ibespe = (ImageButton) findViewById(R.id.ibespe);
        ibhorario = (ImageButton) findViewById(R.id.ibhorario);

        ibhorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int4 = new Intent(Menu.this,registroHorarios.class);
                startActivity(int4);
            }
        });

        ibespe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int3 = new Intent(Menu.this,registroEspecialidades.class);
                startActivity(int3);
            }
        });

        ibmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(Menu.this,registroMedico.class);
                startActivity(int2);
            }
        });
        ibpac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(Menu.this,registroPac.class);
                startActivity(int1);
            }
        });




    }


}
