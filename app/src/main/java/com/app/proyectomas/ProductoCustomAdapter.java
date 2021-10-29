package com.app.proyectomas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.app.proyectomas.entity.Producto;

import java.util.List;

public class ProductoCustomAdapter extends ArrayAdapter<Producto> {

    TextView txtNombre;
    TextView txtMarca;
    TextView txtCategoria;
    TextView txtPrecio;

    List<Producto> list;
    Context context;


    public ProductoCustomAdapter(@NonNull Context context, int resource , List<Producto> list) {
        super(context, resource,list);
        this.list = list;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if(v == null)
            v = LayoutInflater.from(context).inflate(R.layout.list_item_producto,parent,false);
        Producto producto = list.get(position);

        TextView txtCodigo = v.findViewById(R.id.txtCodigo);
        TextView txtMarca = v.findViewById(R.id.txtEmpresa);
        TextView txtCategoria = v.findViewById(R.id.txtTelefono);
        TextView txtPrecio = v.findViewById(R.id.txtDireccion);

        txtCodigo.setText(producto.getCodigo());
        txtMarca.setText((producto.getCategoria()));
        txtCategoria.setText(producto.getCategoria());
        txtPrecio.setText(String.valueOf(producto.getPrecio()));

        return v;
    }

    public void updateChanged(){
        this.notifyDataSetChanged();
    }
}
