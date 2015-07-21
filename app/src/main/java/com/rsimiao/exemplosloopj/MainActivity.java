package com.rsimiao.exemplosloopj;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private Button btnBuscar ;
    private ListView lvUsuarios ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         btnBuscar = (Button) this.findViewById(R.id.buscar);
         lvUsuarios = (ListView) this.findViewById(R.id.lista);


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buscar();

            }
        });


    }

    private void buscar() {

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                if(msg.what == 1){
                    List<Usuario> usuarios = (ArrayList<Usuario>) msg.obj;
                    ListaUsuarioAdapter adapter = new ListaUsuarioAdapter(MainActivity.this,usuarios);
                    lvUsuarios.setAdapter(adapter);

                }else{
                    Toast.makeText(MainActivity.this,"Erro ao preencher",Toast.LENGTH_LONG).show();

                }

            }
        };


        UsuarioWS ws = new UsuarioWS();
        ws.buscar(handler);




    }














}
