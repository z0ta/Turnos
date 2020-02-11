package com.uisrael.turnos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class registroMedico extends AppCompatActivity {
    EditText txtidmed,txtnommed,txtapemed,txtcimed,txtdirmed,txtnacmed,txttelfmed,txtidespe;
    Button btnagregarmed,btnbusmed,btnedimed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_medico);

        txtidmed=(EditText)findViewById(R.id.txtidmed);
        txtnommed=(EditText)findViewById(R.id.txtnommed);
        txtapemed=(EditText)findViewById(R.id.txtapemed);
        txtcimed=(EditText)findViewById(R.id.txtcimed);
        txtdirmed=(EditText)findViewById(R.id.txtdirmed);
        txtnacmed=(EditText)findViewById(R.id.txtnacmed);
        txttelfmed=(EditText)findViewById(R.id.txttelfmed);
        txtidespe=(EditText)findViewById(R.id.txtidespe);

        btnagregarmed=(Button) findViewById(R.id.btnagregarmed);
        btnbusmed=(Button) findViewById(R.id.btnbusmed);
        btnedimed=(Button) findViewById(R.id.btnedimed);

        btnedimed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarusu("http://192.168.1.48/Turnos/editarMed.php");
            }
        });

        btnbusmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 new ConsultarDatos().execute("http://192.168.1.48/Turnos/consultaMed.php?idmed="+txtidmed.getText().toString());

            }
        });

        btnagregarmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               new CargarDatos().execute("http://192.168.1.48/Turnos/registroMed.php?nombremed="+txtnommed.getText().toString()+"&apellidomed="+txtapemed.getText().toString()+"&cimed="+txtcimed.getText().toString()+"&fechanacmed="+txtnacmed.getText().toString()+"&direccionmed="+txtdirmed.getText().toString()+"&telfmed="+txttelfmed.getText().toString()+"&idespe="+txtidespe.getText().toString());

            }
        });




    }
    private class CargarDatos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getApplicationContext(), "Se almacenaron los datos correctamente", Toast.LENGTH_LONG).show();
            limpiarFormulario();
        }
    }


    private class ConsultarDatos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            JSONArray ja = null;
            try {
                ja = new JSONArray(result);
                txtnommed.setText(ja.getString(1));
                txtapemed.setText(ja.getString(2));
                txtcimed.setText(ja.getString(3));
                txtdirmed.setText(ja.getString(4));
                txtnacmed.setText(ja.getString(5));
                txttelfmed.setText(ja.getString(6));
                txtidespe.setText(ja.getString(7));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private String downloadUrl(String myurl) throws IOException {
        Log.i("URL",""+myurl);
        myurl = myurl.replace(" ","%20");
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

    private void editarusu(String URL){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"operacion exitosa",Toast.LENGTH_SHORT).show();
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("idmed", txtidmed.getText().toString());
                parametros.put("nombremed",txtnommed.getText().toString());
                parametros.put("apellidomec",txtapemed.getText().toString());
                parametros.put("cimed",txtcimed.getText().toString());
                parametros.put("fechanacmed",txtnacmed.getText().toString());
                parametros.put("direccionmed",txtdirmed.getText().toString());
                parametros.put("telfmed",txttelfmed.getText().toString());
                parametros.put("idespe",txtidmed.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    private void eliminarServicio(String URL){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"Paciente eliminada",Toast.LENGTH_SHORT).show();
                limpiarFormulario();
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("idmed",txtidmed.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    private void limpiarFormulario() {
        txtnommed.setText("");
        txtapemed.setText("");
        txtcimed.setText("");
        txtnacmed.setText("");
        txtdirmed.setText("");
        txttelfmed.setText("");
        txtidespe.setText("");

    }
}
