package com.example.zoosuport.dataBase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class Sql extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    int newVersion=1;
    int oldVersion=0;
    private static final String database= "zoo";
    private static final int VERSION = 1;
    private final String tprod="" +
        "CREATE TABLE ANIMALS (" +
        "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
        "CLASIFICATION TEXT NOT NULL," +
        "ESPECIE TEXT NOT NULL, " +
        "NOMBRE TEXT NOT NULL," +
        "SEXO TEXT NOT NULL," +
        "FECHA_ING TEXT NOT NULL, " +
        "HABITAT TEXT NOT NULL," +
        "ALIMENTO TEXT NOT NULL" +
        ")";

    public Sql(Context context){
        super(context,database,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("sql","Sí entra");
        db.execSQL(tprod);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int newVersion, int oldVersion) {

        if(newVersion>oldVersion){
            db.execSQL("DROP TABLE IF EXISTS ANIMALS");
        }

    }
}
