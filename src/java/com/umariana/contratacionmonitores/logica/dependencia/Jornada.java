package com.umariana.contratacionmonitores.logica.dependencia;

import java.util.ArrayList;

/**
 *
 * @author Andres
 */
public class Jornada {
    private String jornada;
    private int idDependencia;
    private ArrayList<Horario> horarios;
    private int id;
    private int totalCupos;
    private int totalCuposDisponibles;

    public Jornada(String jornada, int idDependencia, int id) {
        this.jornada = jornada;
        this.idDependencia = idDependencia;
        this.id=id;
        horarios= new ArrayList<>();
        totalCupos=0;
        totalCuposDisponibles=0;
    }
    
    public Jornada(){
        horarios= new ArrayList<>();
        totalCupos=0;
        totalCuposDisponibles=0;
    }
    
    public void agregarHorario(Horario horario){
        horarios.add(horario);
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public int getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(int idDependencia) {
        this.idDependencia = idDependencia;
    }

    public ArrayList<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<Horario> horarios) {
        this.horarios = horarios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalCupos() {
        actualizarAtributoCupos();
        return totalCupos;
    }

    public void setTotalCupos(int totalCupos) {
        this.totalCupos = totalCupos;
    }

    public int getTotalCuposDisponibles() {
        actualizarAtributoCupos();
        return totalCuposDisponibles;
    }

    public void setTotalCuposDisponibles(int totalCuposDisponibles) {
        this.totalCuposDisponibles = totalCuposDisponibles;
    }
    
    public void actualizarAtributoCupos(){
        totalCupos=0;
        totalCuposDisponibles=0;
        for(Horario h:horarios){
            totalCupos+=h.getTotalCupos();
            totalCuposDisponibles+=h.getCuposDisponibles();
        } 
    }

    @Override
    public String toString() {
        return "Jornada{" + "jornada=" + jornada + ", idDependencia=" + idDependencia + ", id=" + id + '}';
    }
    
    
    
}

