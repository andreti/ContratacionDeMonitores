package com.umariana.contratacionmonitores.logica;

import java.util.Date;

/**
 * Es la clase Postulacion
 * @author Andres
 */
public class Postulacion {
    
    /**
     * Es la fecha de postulacion de un aspirante a una dependencia para monitoria
     */
    private Date fechaPostulado;
    /**
     * Es el identificador de la dependencia 
     */
    private String idDependencia;
    /**
     * Es la identificacion del aspirante al que pertenece la postulacion
     */
    private String identificacionEstudiante;
    /**
     * Es la comunicacion con la clase resultado
     */
    private Resultado resultado;
    /**
     * Es el contructor de la clase Postulacion
     * @param fechaPostulado != null
     * @param idDependencia != null && !=""
     * @param identificacionEstudiante != null && !=""
     */
    public Postulacion(String idDependencia, Date fechaPostulacion, String identificacionEstudiante) {
        this.fechaPostulado = fechaPostulacion;
        this.idDependencia = idDependencia;
        this.identificacionEstudiante = identificacionEstudiante;
    }  
    /**
     * Retorna la fecha de la postulacion
     * @return Date
     */
    public Date darFechaPostulado() {
        return fechaPostulado;
    }
    /**
     * 
     * @param fechaPostulado 
     */
    public void cambiarFechaPostulado(Date fechaPostulado) {
        this.fechaPostulado = fechaPostulado;
    }
    /**
     * 
     * @return 
     */
    public String darIdDependencia() {
        return idDependencia;
    }
    /**
     * 
     * @param idDependencia 
     */
    public void cambiarIdDependencia(String idDependencia) {
        this.idDependencia = idDependencia;
    }
    /**
     * 
     * @return 
     */
    public String darIdentificacionEstudiante() {
        return identificacionEstudiante;
    }
    /**
     * 
     * @param identificacionEstudiante 
     */
    public void cambiarIdentificacionEstudiante(String identificacionEstudiante) {
        this.identificacionEstudiante = identificacionEstudiante;
    }

    /**
     * 
     * @return 
     */
    public Resultado darResultado() {
        return resultado;
    }
    /**
     * 
     * @param resultado 
     */
    public void cambiarResultado(Resultado resultado) {
        this.resultado = resultado;
    }
    
    
    
}
