package com.umariana.contratacionmonitores.logica;

import java.util.Date;

/**
 * Es la clase Postulacion
 * @author Andres
 */
public class Postulacion {
    
    private Dependencia dependencia;
    /**
     * Es la identificacion del aspirante al que pertenece la postulacion
     */
    private String identificacionEstudiante;
    /**
     * Es la comunicacion con la clase resultado
     */
    private Resultado resultado;
    
    private int idHorario;

    

    public Postulacion(){
        
    }
    public String darIdentificacionEstudiante() {
        return identificacionEstudiante;
    }
    public void cambiarIdentificacionEstudiante(String identificacionEstudiante) {
        this.identificacionEstudiante = identificacionEstudiante;
    }
    public Resultado darResultado() {
        return resultado;
    }
    public void cambiarResultado(Resultado resultado) {
        this.resultado = resultado;
    }
    public String getIdentificacionEstudiante() {
        return identificacionEstudiante;
    }
    public void setIdentificacionEstudiante(String identificacionEstudiante) {
        this.identificacionEstudiante = identificacionEstudiante;
    }
     public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }   
    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }
}
