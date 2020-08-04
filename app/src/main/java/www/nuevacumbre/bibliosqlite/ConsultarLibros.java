package www.nuevacumbre.bibliosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import www.nuevacumbre.bibliosqlite.Utilidades.Utilidades;

public class ConsultarLibros extends AppCompatActivity {

    EditText campoId,campoNombre,campoEditorial,campoAutor,campoIsbn,campoUbicacion;

    ConexionSqliteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_libros);

        conn=new ConexionSqliteHelper(getApplicationContext(),"bd_biblioteca",null,1);

        campoId= (EditText) findViewById(R.id.documentoId);
        campoNombre= (EditText) findViewById(R.id.campoNombreConsulta);
        campoEditorial= (EditText) findViewById(R.id.campoEditorialConsulta);
        campoAutor= (EditText) findViewById(R.id.campoAutorConsulta);
        campoIsbn= (EditText) findViewById(R.id.campoIsbnConsulta);
        campoUbicacion= (EditText) findViewById(R.id.campoUbicacionConsulta);
    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnConsultar:
                // consultar();       // metodo con display en toast
                               consultarSql();
                break;
            case R.id.btnActualizar: actualizarUsuario();
                break;
            case R.id.btnEliminar: eliminarUsuario();
                break;
        }

    }

    private void eliminarUsuario() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={campoId.getText().toString()};

        db.delete(Utilidades.TABLA_LIBRO,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Ya se Eliminó el libro",Toast.LENGTH_LONG).show();
        campoId.setText("");
        limpiar();
        db.close();
    }

    private void actualizarUsuario() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={campoId.getText().toString()};
        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_EDITORIAL,campoEditorial.getText().toString());
        values.put(Utilidades.CAMPO_AUTOR,campoAutor.getText().toString());
        values.put(Utilidades.CAMPO_ISBN,campoIsbn.getText().toString());
        values.put(Utilidades.CAMPO_UBICACION,campoUbicacion.getText().toString());

        db.update(Utilidades.TABLA_LIBRO,values,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Ya se actualizó el libro",Toast.LENGTH_LONG).show();
        db.close();

    }

    private void consultarSql() {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={campoId.getText().toString()};

        try {
            //select nombre,telefono from usuario where codigo=?
            Cursor cursor=db.rawQuery("SELECT "+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_EDITORIAL+","+Utilidades.CAMPO_AUTOR+","+Utilidades.CAMPO_ISBN+","+Utilidades.CAMPO_UBICACION+
                    " FROM "+Utilidades.TABLA_LIBRO+" WHERE "+Utilidades.CAMPO_ID+"=? ",parametros);

            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));
            campoEditorial.setText(cursor.getString(1));
            campoAutor.setText(cursor.getString(2));
            campoIsbn.setText(cursor.getString(3));
            campoUbicacion.setText(cursor.getString(4));


        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }

    }

    private void consultar() {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={campoId.getText().toString()};
        String[] campos={Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_EDITORIAL,Utilidades.CAMPO_AUTOR,Utilidades.CAMPO_ISBN,Utilidades.CAMPO_UBICACION};

        try {
            Cursor cursor =db.query(Utilidades.TABLA_LIBRO,campos,Utilidades.CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));
            campoEditorial.setText(cursor.getString(1));
            campoAutor.setText(cursor.getString(2));
            campoIsbn.setText(cursor.getString(3));
            campoUbicacion.setText(cursor.getString(4));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El Código de libro no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }

    }

    private void limpiar() {
        campoNombre.setText("");
        campoEditorial.setText("");
        campoAutor.setText("");
        campoIsbn.setText("");
        campoUbicacion.setText("");
    }
}