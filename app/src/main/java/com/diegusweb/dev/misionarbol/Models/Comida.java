package com.diegusweb.dev.misionarbol.Models;

import com.diegusweb.dev.misionarbol.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 03/01/2017.
 */

public class Comida {

    private int id;
    private float precio;
    private String nombre;
    private int idDrawable;

    public Comida(int id, float precio, String nombre, int idDrawable) {
        this.id = id;
        this.precio = precio;
        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public static final List<Comida> COMIDAS_POPULARES = new ArrayList<Comida>();
    public static final List<Comida> BEBIDAS = new ArrayList<>();
    public static final List<Comida> POSTRES = new ArrayList<>();
    public static final List<Comida> PLATILLOS = new ArrayList<>();

    static {
        COMIDAS_POPULARES.add(new Comida(1, 5, "Camarones Tismados", R.drawable.camarones));
        COMIDAS_POPULARES.add(new Comida(2, 3.2f, "Rosca Herbárea", R.drawable.rosca));
        COMIDAS_POPULARES.add(new Comida(3, 12f, "Sushi Extremo", R.drawable.sushi));
        COMIDAS_POPULARES.add(new Comida(4, 9, "Sandwich Deli", R.drawable.sandwich));
        //COMIDAS_POPULARES.add(new Comida(34f, "Lomo De Cerdo Austral", R.drawable.lomo_cerdo));
    }

}