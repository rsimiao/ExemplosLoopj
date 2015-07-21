package com.rsimiao.exemplosloopj;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * ExemploLoopj
 * Copyright (C) 2015 rsimiao
 * *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
public class UsuarioWS {


    private String url;

    public UsuarioWS(){
        this.url = "usuario.php";
    }


    public void buscar(final Handler handler){

        try {
            RequestParams parametros = new RequestParams();
            parametros.add("limite", "todos");

            Wscliente.post(url, parametros, new AsyncHttpResponseHandler() {
                @Override
                public void onFailure(int code, Header[] header, byte[] conteudo,
                                      Throwable arg3) {
                    //você pode colocar aqui seu tratamento de erro
                    Log.d("WS USUARIO", "DEU ERRO");
                    Message msg = new Message();
                    msg.what = 0; //defino um identificador para a msg coloco 1 para sucesso e 0 para erro.
                    handler.sendMessage(msg);
                }

                @Override
                public void onSuccess(int code, Header[] header, byte[] conteudo) {
                    try {
                        JSONObject json = new JSONObject(new String(conteudo));
                        List<Usuario> usuarios = new ArrayList<Usuario>();
                        //sua lógica para tratar o json recebido (não precisa ser desse modo jurástico)
                        if(json.has("status") && json.getString("status").equalsIgnoreCase("success")){

                            JSONArray jUsrs = json.getJSONArray("usuarios");

                            for(int i=0;i<jUsrs.length();i++){
                                Usuario u = new Usuario();
                                u.setNome(jUsrs.getJSONObject(i).getString("nome"));
                                u.setUsuario(jUsrs.getJSONObject(i).getString("usuario"));
                                u.setLogged(jUsrs.getJSONObject(i).getBoolean("islogged"));
                                usuarios.add(u);
                            }

                            Message msg = new Message();
                            msg.what = 1; //defino um identificador para a msg coloco 1 para sucesso e 0 para erro.
                            msg.obj = usuarios;
                            handler.sendMessage(msg);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
