package www.nuevacumbre.bibliosqlite.Utilidades;

public class Utilidades {
    public static String TABLA_LIBRO = "libro";
    public static String CAMPO_ID = "id";
    public static String CAMPO_NOMBRE = "nombre";
    public static String CAMPO_EDITORIAL = "editorial";
    public static String CAMPO_AUTOR = "autor";
    public static String CAMPO_ISBN = "isbn";
    public static String CAMPO_UBICACION = "ubicacion";


    public static final  String CREAR_TABLA_LIBRO ="CREATE TABLE "+TABLA_LIBRO+" ("+CAMPO_ID+" INTEGER, "+CAMPO_NOMBRE+" TEXT, "+CAMPO_EDITORIAL+" TEXT, "+CAMPO_AUTOR+" TEXT, "+CAMPO_ISBN+" TEXT, "+CAMPO_UBICACION+" TEXT)";
}
