package com.example.zoosuport.dataBase;

import com.example.zoosuport.entitys.Animal;

import java.util.ArrayList;
import java.util.List;

public class ListStaticAnimals {


    public  final List<Animal> animals = new ArrayList<Animal>() {{
            add(new Animal(
                "1",
                "Bonito",
                "Felino",
                "Leon ",
                "Macho",
                "2019-03-10",
                "Selva",
                "Carne"));
        }};
}
