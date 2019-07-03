package com.example.zoosuport.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.util.Log;

import com.example.zoosuport.entitys.Animal;

import java.util.ArrayList;
import java.util.List;

public class SqlLite {

    private Sql sql;
    private SQLiteDatabase db;

    public SqlLite(Context context) {
        this.sql = new Sql(context);
        this.db = sql.getWritableDatabase();
    }

    public SqlLite() {

    }


    public void opeen() {
        Log.i("sqllite", "se abre " + sql.getDatabaseName());

    }

    public void cerrar() {
        Log.i("sqllite", "se cierra " + sql.getDatabaseName());
        sql.close();
    }

    public boolean addRegister(ContentValues cv, String table) {
        return (db.insert(table, null, cv) != -1) ? true : false;
    }

    public Cursor getRegistro(String table) {
        return db.rawQuery("SELECT * FROM " + table, null);
    }

    public List<Animal> getAnimals(Cursor cursor) {
        List<Animal> listAnimal = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                //TODO the variables DO NOT START With capital letters

                listAnimal.add(converteAnimal(cursor));
            } while (cursor.moveToNext());
        }
        return listAnimal;
    }

    public Animal converteAnimal(Cursor cursor) {
        Animal animal = null;
        if(cursor !=null){

            String id = cursor.getInt(0) + "";
            String clasificacion = cursor.getString(1) + "";
            String Especie = cursor.getString(2) + "";
            String Nombre = cursor.getString(3) + "";
            String Sexo = cursor.getString(4) + "";
            String Fecha_de_Ingreso = cursor.getString(5) + "";
            String Habitat = cursor.getString(6) + "";
            String Alimento = cursor.getString(7) + "";
            animal = new Animal(id, clasificacion,Especie, Nombre, Sexo, Fecha_de_Ingreso, Habitat, Alimento);

        }
        return animal;
    }

    public ArrayList<String> getPro(Cursor cursor) {
        ArrayList<String> listData = new ArrayList<>();
        String item = "";
        if (cursor.moveToFirst()) {
            do {
                item += "ID: [" + cursor.getInt(0) + "]\r\n";
                item += "Clasificación: [" + cursor.getInt(0) + "]\r\n";
                item += "Especie: [" + cursor.getInt(0) + "]\r\n";
                item += "Nombre: [" + cursor.getInt(0) + "]\r\n";
                item += "Sexo: [" + cursor.getInt(0) + "]\r\n";
                item += "Fecha de Ingreso: [" + cursor.getInt(0) + "]\r\n";
                item += "Habitat: [" + cursor.getInt(0) + "]\r\n";
                item += "Alimento: [" + cursor.getInt(0) + "]\r\n";
                listData.add(item);
                item = "";
            } while (cursor.moveToNext());
        }
        return listData;

    }

    public ArrayList<String> getId(Cursor cursor) {
        ArrayList<String> listData = new ArrayList<>();
        String item = "";

        if (cursor.moveToFirst()) {
            do {

                item += "ID: [" + cursor.getInt(0) + "]\r\n";
                listData.add(item);
            } while (cursor.moveToNext());
        }
        return listData;
    }

    public String upDate(
        String id, String clas, String esp, String nom, String sex, String date, String habit,
        String food) {

        ContentValues cv = new ContentValues();
        cv.put("ID", id);
        cv.put("CLASIFICATION", clas);
        cv.put("ESPECIE", esp);
        cv.put("NOMBRE", nom);
        cv.put("SEXO", sex);
        cv.put("FECHA_ING", date);
        cv.put("HABITAT", habit);
        cv.put("ALIMENTO", food);

        int cant = db.update("ANIMALS", cv, "ID=" + id, null);


        return (cant == 1) ? "Modificado" : "Modificado";
    }

    public Cursor getCant(String id, String table) {
        return db.rawQuery("SELECT * FROM " + table + "  WHERE ID = " + id, null);

    }

    public int eliminar(Editable id, String table) {
        return db.delete(table, "ID=" + id, null);

    }

}



