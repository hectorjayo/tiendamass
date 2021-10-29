package com.app.proyectomas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.proyectomas.entity.Producto;
import com.app.proyectomas.local.Producto.ProductoSQLiteManager;

public class RegistrarProductosActivity extends AppCompatActivity {

    ProductoSQLiteManager productoSQLiteManager;

    EditText editTxtCodigo;
    EditText editTxtMarca;
    EditText editTxtCategoria;
    EditText editTxtPrecio;

    Button btnRegistrarProducto;
    Button btnSalirOfRegistrarProducto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_productos);

        productoSQLiteManager = new ProductoSQLiteManager(this);

        editTxtCodigo = findViewById(R.id.editTxtCodigo);
        editTxtMarca = findViewById(R.id.editTxtEmpresa);
        editTxtCategoria = findViewById(R.id.editTxtTelefono);
        editTxtPrecio = findViewById(R.id.editTxtDireccion);

        btnRegistrarProducto = findViewById(R.id.btnRegistrarProducto);
        btnSalirOfRegistrarProducto = findViewById(R.id.btnSalirOfRegistrarProducto);

        btnRegistrarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });

        btnSalirOfRegistrarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMenuActivity();
            }
        });


    }


    public void registrar(){


        if(editTxtCodigo.getText().toString().equals("")){
            Toast.makeText(this,"EL CAMPO CÓDIGO ESTÁ VACIO.",Toast.LENGTH_SHORT).show();
        }else if(editTxtMarca.getText().toString().equals("")){
            Toast.makeText(this,"EL CAMPO MARCA ESTÁ VACIO.",Toast.LENGTH_SHORT).show();
        }else if(editTxtCategoria.getText().toString().equals("")){
            Toast.makeText(this,"EL CAMPO CATEGORIA ESTÁ VACIO.",Toast.LENGTH_SHORT).show();
        }else if(editTxtPrecio.getText().toString().equals("")){
            Toast.makeText(this,"EL CAMPO PRECIO ESTÁ VACIO.",Toast.LENGTH_SHORT).show();
        }else{
            productoSQLiteManager.save(new Producto(
                    editTxtCodigo.getText().toString(),
                    editTxtMarca.getText().toString(),
                    editTxtCategoria.getText().toString(),
                    Double.parseDouble(editTxtPrecio.getText().toString())
            ));
            Toast.makeText(this,"SE REGISTRO EL PRODUCTO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
            changeMenuActivity();
        }
    }

    public void changeMenuActivity(){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}