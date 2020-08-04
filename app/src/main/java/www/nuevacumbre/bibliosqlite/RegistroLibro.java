package www.nuevacumbre.bibliosqlite;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import www.nuevacumbre.bibliosqlite.Utilidades.Utilidades;

public class RegistroLibro extends AppCompatActivity {
    EditText campoId,campoNombre,campoEditorial,campoAutor,campoIsbn,campoUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_libro);
        campoId= (EditText) findViewById(R.id.campoId);
        campoNombre= (EditText) findViewById(R.id.campoNombre);
        campoEditorial= (EditText) findViewById(R.id.campoEditorial);
        campoAutor= (EditText) findViewById(R.id.campoAutor);
        campoIsbn= (EditText) findViewById(R.id.campoIsbn);
        campoUbicacion= (EditText) findViewById(R.id.campoUbicacion);
    }

    public void onClick(View view) {
        //registrarLibros();
         registrarLibrosSql();
        //cespinoza
    }

    private void registrarLibrosSql() {
        ConexionSqliteHelper conn=new ConexionSqliteHelper(this, "bd_biblioteca", null, 1);
        //ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_libros",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        //insert into libro (id,nombre,editorial,autor,isbn,ubicacion) values (123,'Papelucho','Santillana','Marcela Paz','1225AB','A 30')

        String insert="INSERT INTO "+Utilidades.TABLA_LIBRO
                +" ( " +Utilidades.CAMPO_ID+","+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_EDITORIAL+","+Utilidades.CAMPO_AUTOR+","+Utilidades.CAMPO_ISBN+","+Utilidades.CAMPO_UBICACION+")" +
                " VALUES ("+campoId.getText().toString()+", '"+campoNombre.getText().toString()+"','"
                +campoEditorial.getText().toString()+"','"+campoAutor.getText().toString()+"','"+campoIsbn.getText().toString()+"','"+campoUbicacion.getText().toString()+"')";

        db.execSQL(insert);

        db.close();
    }


    private void registrarLibros() {
        ConexionSqliteHelper conn=new ConexionSqliteHelper(this, "bd_biblioteca", null, 1);
        //ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_libros",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_ID,campoId.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_EDITORIAL,campoEditorial.getText().toString());
        values.put(Utilidades.CAMPO_AUTOR,campoAutor.getText().toString());
        values.put(Utilidades.CAMPO_ISBN,campoIsbn.getText().toString());
        values.put(Utilidades.CAMPO_UBICACION,campoUbicacion.getText().toString());

        Long idResultante=db.insert(Utilidades.TABLA_LIBRO,Utilidades.CAMPO_ID,values);

        Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
    }
}