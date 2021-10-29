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

public class ModificarProductoActivity extends AppCompatActivity {

    ProductoSQLiteManager productoSQLiteManager;

    EditText editTxtCodigo;
    EditText editTxtMarca;
    EditText editTxtCategoria;
    EditText editTxtPrecio;

    Button btnRegistrarProducto;
    Button btnSalirOfRegistrarProducto;

    int productoId = -1;
    Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_producto);

        Bundle b = getIntent().getExtras();

        if(b != null)
            productoId = b.getInt("productoId");

        Toast.makeText(this,String.valueOf(productoId),Toast.LENGTH_SHORT).show();

        productoSQLiteManager = new ProductoSQLiteManager(this);

        producto = productoSQLiteManager.getById(productoId);

        editTxtCodigo = findViewById(R.id.editTxtCodigo);
        editTxtMarca = findViewById(R.id.editTxtEmpresa);
        editTxtCategoria = findViewById(R.id.editTxtTelefono);
        editTxtPrecio = findViewById(R.id.editTxtDireccion);

        btnRegistrarProducto = findViewById(R.id.btnRegistrarProducto);
        btnSalirOfRegistrarProducto = findViewById(R.id.btnSalirOfRegistrarProducto);

        btnRegistrarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar();
            }
        });

        btnSalirOfRegistrarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMenuActivity();
            }
        });

        editTxtCodigo.setText(producto.getCodigo());
        editTxtMarca.setText(producto.getMarca());
        editTxtCategoria.setText(producto.getCategoria());
        editTxtPrecio.setText(String.valueOf(producto.getPrecio()));
    }

    public void modificar(){

        if(editTxtCodigo.getText().toString().equals("")){
            Toast.makeText(this,"EL CAMPO CÓDIGO ESTÁ VACIO.",Toast.LENGTH_SHORT).show();
        }else if(editTxtMarca.getText().toString().equals("")){
            Toast.makeText(this,"EL CAMPO MARCA ESTÁ VACIO.",Toast.LENGTH_SHORT).show();
        }else if(editTxtCategoria.getText().toString().equals("")){
            Toast.makeText(this,"EL CAMPO CATEGORIA ESTÁ VACIO.",Toast.LENGTH_SHORT).show();
        }else if(editTxtPrecio.getText().toString().equals("")){
            Toast.makeText(this,"EL CAMPO PRECIO ESTÁ VACIO.",Toast.LENGTH_SHORT).show();
        }else{
            productoSQLiteManager.update(new Producto(
                    productoId,
                    editTxtCodigo.getText().toString(),
                    editTxtMarca.getText().toString(),
                    editTxtCategoria.getText().toString(),
                    Double.parseDouble(editTxtPrecio.getText().toString())
            ));
            Toast.makeText(this,"SE MODIFICO EL PRODUCTO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
            changeMenuActivity();
        }
    }

    public void changeMenuActivity(){
        Intent intent = new Intent(this, ListarModificarProductoActivity.class);
        startActivity(intent);
    }
}