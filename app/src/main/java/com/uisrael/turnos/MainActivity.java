package com.uisrael.turnos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btningresar,btnregistrar;
    EditText txtcorreo,txtpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtcorreo=(EditText)findViewById(R.id.txtcorreo);
        txtpassword=(EditText)findViewById(R.id.txtpassword);
        btningresar=(Button) findViewById(R.id.btningresar);
        btnregistrar=(Button) findViewById(R.id.btnregistrar) ;

        btningresar.setOnClickListener(this);


    }

    public void onClick2(View v) {

                            Intent i = new Intent(getApplicationContext(),registroUsu.class);
                            startActivity(i);

    }


    @Override
    public void onClick(View v) {
        Thread tr = new Thread(){
            @Override
            public void run(){
                final String resultado = enviarDatosGET(txtcorreo.getText().toString(),txtpassword.getText().toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int r=obtDatosJson(resultado);
                        if(r>0){
                            Intent i = new Intent(getApplicationContext(),Menu.class);
                            i.putExtra("correousu",txtcorreo.getText().toString());
                            startActivity(i);

                        }else{
                            Toast.makeText(getApplicationContext(),"usaurio o pass incorrectos",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        };
        tr.start();
    }
    public String enviarDatosGET(String CORREO,String PASS){
        URL url=null;
        String linea="";
        int respuesta=0;
        StringBuilder result = null;

        try {
            url=new URL("http://130.1.35.203/Turnos/valida.php?correo="+CORREO+"&pass="+PASS);
            HttpURLConnection conection =(HttpURLConnection)url.openConnection();
            respuesta= conection.getResponseCode();

            result = new StringBuilder();

            if(respuesta==HttpURLConnection.HTTP_OK){
                InputStream in=new BufferedInputStream(conection.getInputStream());
                BufferedReader reader =new BufferedReader(new InputStreamReader(in));
                while((linea=reader.readLine())!=null){
                    result.append(linea);
                }
            }

        }catch (Exception e){}

        return result.toString();


    }

    public int obtDatosJson(String response){
        int res=0;

        try {
            JSONArray json = new JSONArray(response);
            if(json.length()>0){
                res=1;
            }
        }catch (Exception e){}
        return res;
    }

}
