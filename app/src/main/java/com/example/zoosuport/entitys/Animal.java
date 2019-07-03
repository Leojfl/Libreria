package com.example.zoosuport.entitys;

import android.content.ContentValues;

public class Animal {


    //    TODO  minus
    String ID;
    String CLASIFICATION;
    String ESPECIE;
    String NOMBRE;
    String SEXO;
    String FECHA_ING;
    String HABITAT;
    String ALIMENTO;
    String table;

    public Animal(String ID, String CLASIFICATION, String ESPECIE, String NOMBRE,
                  String SEXO, String FECHA_ING, String HABITAT, String ALIMENTO) {
        this.ID = ID;
        this.CLASIFICATION = CLASIFICATION;
        this.ESPECIE = ESPECIE;
        this.NOMBRE = NOMBRE;
        this.SEXO = SEXO;
        this.FECHA_ING = FECHA_ING;
        this.HABITAT = HABITAT;
        this.ALIMENTO = ALIMENTO;
        this.table = "ANIMALS ";
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCLASIFICATION() {
        return CLASIFICATION;
    }

    public void setCLASIFICATION(String CLASIFICATION) {
        this.CLASIFICATION = CLASIFICATION;
    }

    public String getESPECIE() {
        return ESPECIE;
    }

    public void setESPECIE(String ESPECIE) {
        this.ESPECIE = ESPECIE;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getSEXO() {
        return SEXO;
    }

    public void setSEXO(String SEXO) {
        this.SEXO = SEXO;
    }

    public String getFECHA_ING() {
        return FECHA_ING;
    }

    public void setFECHA_ING(String FECHA_ING) {
        this.FECHA_ING = FECHA_ING;
    }

    public String getHABITAT() {
        return HABITAT;
    }

    public void setHABITAT(String HABITAT) {
        this.HABITAT = HABITAT;
    }

    public String getALIMENTO() {
        return ALIMENTO;
    }

    public void setALIMENTO(String ALIMENTO) {
        this.ALIMENTO = ALIMENTO;
    }

    public ContentValues insert() {
        ContentValues contentValues = new ContentValues();

        contentValues.put("ID", this.ID);
        contentValues.put("CLASIFICATION", this.CLASIFICATION);
        contentValues.put("ESPECIE", this.ESPECIE);
        contentValues.put("NOMBRE", this.NOMBRE);
        contentValues.put("SEXO", this.SEXO);
        contentValues.put("FECHA_ING", this.FECHA_ING);
        contentValues.put("HABITAT", this.HABITAT);
        contentValues.put("ALIMENTO", this.ALIMENTO);

        return contentValues;
    }

    public String show() {
        String item =
            "Id=" + this.ID + "\n" +
                "CLASIFICATION=" + this.CLASIFICATION + "\n" +
                "ESPECIE=" + this.ESPECIE + "\n" +
                "NOMBRE=" + this.NOMBRE + "\n" +
                "SEXO=" + this.SEXO + "\n" +
                "FECHA_ING=" + this.FECHA_ING + "\n" +
                "HABITAT=" + this.HABITAT + "\n" +
                "ALIMENTO=" + this.ALIMENTO + "\n";
        return item;
    }
}
