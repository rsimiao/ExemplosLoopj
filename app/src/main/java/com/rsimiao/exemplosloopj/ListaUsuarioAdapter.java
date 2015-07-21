package com.rsimiao.exemplosloopj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

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
public class ListaUsuarioAdapter extends ArrayAdapter<Usuario> {

    private static class ViewHolder{
        TextView usuario;
        TextView nome;
        ToggleButton logged;
    }

    private ViewHolder lv;
    private Context context;

    public ListaUsuarioAdapter(Context context,List<Usuario> usuarios) {
        super(context, 0,usuarios);
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_menu_usuario,null);
            this.lv = new ViewHolder();
            this.lv.usuario = (TextView) convertView.findViewById(R.id.txtusuario);
            this.lv.nome = (TextView) convertView.findViewById(R.id.txtnome);
            this.lv.logged = (ToggleButton) convertView.findViewById(R.id.tgbligadesliga);

            convertView.setTag(this.lv);
        }else{

            this.lv = (ViewHolder) convertView.getTag();

        }

        this.lv.usuario.setText(getItem(position).getUsuario());
        this.lv.nome.setText(getItem(position).getNome());
        this.lv.logged.setChecked(getItem(position).isLogged());


        return convertView;
    }
}
