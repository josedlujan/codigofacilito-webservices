package webservice.proyecto.cf.com.cfproyecto;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.tech.NfcBarcode;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import webservice.proyecto.cf.com.cfproyecto.Adapters.MyAdapter;
import webservice.proyecto.cf.com.cfproyecto.Adapters.UsuariosAdapter;
import webservice.proyecto.cf.com.cfproyecto.POJO.Usuario;
import webservice.proyecto.cf.com.cfproyecto.Parsers.UsuarioJSONParser;
import webservice.proyecto.cf.com.cfproyecto.Parsers.UsuarioXMLParser;

public class MainActivity extends AppCompatActivity {
    Button boton;
    TextView textView;
    ProgressBar progressBar;
   // List<MyTask> tasks;
    //List<Usuario> usuarioList;

    //ListView listView;
    //MyAdapter adapter;

    //RecyclerView recyclerView;
    //UsuariosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = (Button) findViewById(R.id.boton);
        textView = (TextView) findViewById(R.id.textview);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        //recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(linearLayoutManager);


        //textView.setMovementMethod(new ScrollingMovementMethod());

        //paralela
        //tasks = new ArrayList<>();
        //

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnLine()) {
                    // pedirtDatos("http://maloschistes.com/maloschistes.com/jose/usuarios.xml");
                    // pedirtDatos("http://maloschistes.com/maloschistes.com/jose/webservice.php");
                    pedirtDatos("http://maloschistes.com/maloschistes.com/jose/webservicesend.php");
                } else {
                    Toast.makeText(getApplicationContext(), "Sin conexion", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    /* for(int i = 0; i <= 200; i++){
                    cargarDatos("Número "+ i);
                }
                cargarDatos("**FIN**");



              //  MyTask task = new MyTask();
               // task.execute("A","B","C");


                //programacion paralela
               // MyTask task = new MyTask();
               // task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"A","B","C");


            }
        });

    }*/

    public void cargarDatos(String datos){
        textView.append(datos + "\n");
    }
       //
       /* if(usuarioList != null){
            for (Usuario usuario: usuarioList) {
                textView.append(usuario.getTwitter() + "\n");
            }
        }*/
      //  adapter = new UsuariosAdapter(getApplicationContext(),usuarioList);
      //  recyclerView.setAdapter(adapter);
       // recyclerView.setHasFixedSize(true);


    public void pedirtDatos(String uri){
        MyTask task = new MyTask();
        RequestPackage requestPackage = new RequestPackage();
        requestPackage.setMethod("POST");
        requestPackage.setUri(uri);
        requestPackage.setParam("nombre","valor1");
        requestPackage.setParam("animal","valor2");
        requestPackage.setParam("id","valor3");



        task.execute(requestPackage);
    }

    public boolean isOnLine(){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }else{
            return false;
        }


    }

    private class MyTask extends AsyncTask<RequestPackage, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // cargarDatos("Inicio de carga");
            progressBar.setVisibility(View.VISIBLE);

            //paralela
            /*if (tasks.size() == 0) {
                progressBar.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
            */
            //////
        }

        @Override
        protected String doInBackground(RequestPackage... params) {
            /*for(int i = 0; i <= 10; i++){
                publishProgress("Número "+ i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
            String content = HttpManager.getData(params[0]);


            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);







            if(result == null){
                Toast.makeText(MainActivity.this,"No se pudo conectar",Toast.LENGTH_SHORT).show();
               // progressBar.setVisibility(View.INVISIBLE);
                return;
            }

           // usuarioList = UsuarioJSONParser.parse(result);
            cargarDatos(result);
            progressBar.setVisibility(View.INVISIBLE);

            //paralelo
            /*tasks.remove(this);
            if (tasks.size() == 0) {
                progressBar.setVisibility(View.INVISIBLE);
            }*/
            ///

        }


        @Override
        protected void onProgressUpdate(String... values) {
          //  cargarDatos(values[0]);
        }
    }
}
