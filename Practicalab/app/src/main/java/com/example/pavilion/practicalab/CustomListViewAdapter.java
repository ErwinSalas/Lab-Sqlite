package com.example.pavilion.practicalab;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pavilion.practicalab.models.Orden;

import java.util.ArrayList;

public class CustomListViewAdapter extends ArrayAdapter<Orden> {

    private final Activity context;
    private ArrayList<Orden> ordenes;


    public CustomListViewAdapter(Activity context,ArrayList<Orden> ordenes) {
        super(context, R.layout.activity_custom_list_view_adapter, ordenes);
        this.context = context;
        this.ordenes = ordenes;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.activity_custom_list_view_adapter, null, true);
        TextView codigo = (TextView) rowView.findViewById(R.id.codigo);
        TextView comentario = (TextView) rowView.findViewById(R.id.comentario);
        TextView localizacion = (TextView) rowView.findViewById(R.id.localizacion);
        codigo.setText(ordenes.get(position).getCodigoOrden());
        comentario.setText(ordenes.get(position).getComentario());
        localizacion.setText(ordenes.get(position).getLocalización());
        return rowView;
    }
}
