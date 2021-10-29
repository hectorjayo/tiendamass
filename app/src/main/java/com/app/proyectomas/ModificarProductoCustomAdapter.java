package com.app.proyectomas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.app.proyectomas.entity.Producto;

import java.util.List;

public class ModificarProductoCustomAdapter extends ArrayAdapter<Producto> {

    TextView txtCodigo;
    TextView txtMarca;
    TextView txtCategoria;
    TextView txtPrecio;
    Button btnModificarProducto;

    List<Producto> list;
    Context context;
    Producto producto;
    int productoId;

    public ModificarProductoCustomAdapter(@NonNull Context context, int resource , List<Producto> list) {
        super(context, resource,list);
        this.list = list;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if(v == null)
            v = LayoutInflater.from(context).inflate(R.layout.list_item_modificar_producto,parent,false);
        producto = list.get(position);

        System.out.println("PRODUCTO ID =>"+String.valueOf(producto.getId()));

        txtCodigo = v.findViewById(R.id.txtCodigo);
        txtMarca = v.findViewById(R.id.txtEmpresa);
        txtCategoria = v.findViewById(R.id.txtTelefono);
        txtPrecio = v.findViewById(R.id.txtDireccion);
        btnModificarProducto = v.findViewById(R.id.btnModificarProducto);


        txtCodigo.setText(producto.getCodigo());
        txtMarca.setText((producto.getMarca()));
        txtCategoria.setText(producto.getCategoria());
        txtPrecio.setText(String.valueOf(producto.getPrecio()));
        productoId = producto.getId();

        btnModificarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeModificarProductoActivity(list.get(position).getId());
            }
        });
        return v;
    }


    public void changeModificarProductoActivity(int productoId){

        Intent intent = new Intent(context, ModificarProductoActivity.class);
        Bundle b = new Bundle();
        b.putInt("productoId", productoId); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        context.startActivity(intent);
    }

    public void updateChanged(){
        this.notifyDataSetChanged();
    }
}
