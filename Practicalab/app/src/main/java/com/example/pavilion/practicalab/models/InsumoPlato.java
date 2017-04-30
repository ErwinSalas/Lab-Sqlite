package com.example.pavilion.practicalab.models;

/**
 * Created by Pavilion on 28/4/2017.
 */
public class InsumoPlato {
    private int códigoInsumo;
    private int códigoPlato ;

    public InsumoPlato(int códigoInsumo, int códigoPlato) {
        this.códigoInsumo = códigoInsumo;
        this.códigoPlato = códigoPlato;
    }

    public int getCódigoInsumo() {
        return códigoInsumo;
    }

    public void setCódigoInsumo(int códigoInsumo) {
        this.códigoInsumo = códigoInsumo;
    }

    public int getCódigoPlato() {
        return códigoPlato;
    }

    public void setCódigoPlato(int códigoPlato) {
        this.códigoPlato = códigoPlato;
    }
}
