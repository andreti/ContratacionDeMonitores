package com.umariana.contratacionmonitores.logica.dependencia;

/**
 *
 * @author Andres
 */
public class Horario {
    private int desde;
    private int hasta;
    private int idJornada;
    private int cuposDisponibles;
    private int totalCupos;
    private int id;

    public Horario(int desde, int hasta, int idJornada, int cuposDisponibles, int totalCupos,int id) {
        this.desde = desde;
        this.hasta = hasta;
        this.idJornada = idJornada;
        this.cuposDisponibles = cuposDisponibles;
        this.totalCupos = totalCupos;
        this.id=id;
    }
    public Horario(){
        
    }
    public int getDesde() {
        return desde;
    }

    public void setDesde(int desde) {
        this.desde = desde;
    }

    public int getHasta() {
        return hasta;
    }

    public void setHasta(int hasta) {
        this.hasta = hasta;
    }

    public int getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(int idJornada) {
        this.idJornada = idJornada;
    }

    public int getCuposDisponibles() {
        return cuposDisponibles;
    }

    public void setCuposDisponibles(int cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
    }

    public int getTotalCupos() {
        return totalCupos;
    }

    public void setTotalCupos(int totalCupos) {
        this.totalCupos = totalCupos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getDesde()+" - "+getHasta();
    }
    
    
    
}
