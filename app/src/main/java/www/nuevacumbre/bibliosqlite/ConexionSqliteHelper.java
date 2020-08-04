package www.nuevacumbre.bibliosqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import www.nuevacumbre.bibliosqlite.Utilidades.Utilidades;

public class ConexionSqliteHelper extends SQLiteOpenHelper {



    public ConexionSqliteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // SQLiteDatabase db = null;
        db.execSQL(Utilidades.CREAR_TABLA_LIBRO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS libros");
        onCreate(db);
    }
}
