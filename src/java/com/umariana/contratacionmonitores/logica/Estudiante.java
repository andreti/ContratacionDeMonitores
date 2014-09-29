/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.umariana.contratacionmonitores.logica;

import java.io.File;

/**
 *
 * @author Andres
 */
public class Estudiante {
    //
    //ATRIBUTOS
    // 
    /**
     * Es el primer nombre del monitor 
     */
    private String primerNombre;
    /**
     * Es el segundo noombre del monitor 
     */
    private String segundoNombre;
     /**
     * Es el primer apellido del monitor 
     */
    private String primerApellido;
     /**
     * Es el segundo apellido del monitor 
     */
    private String segundoApellido;
    /**
     * Es el codigo del monitor
     */
    private int codigo;
    /**
     * Es el estado de la matricula del monitor
     */
    private String estadoMatricula;
    /**
     * Es la foto del monitor
     */
    private File foto;
    /**
     * Es el promedio acumulado del monitor
     */
    private double promedioAcumulado;
    /**
     * Es el semestre actual que cursa el monitor
     */
    private int semestreActual;
    /**
     * Es la identificacion del monitor
     */
    private String identificacion;
    //
    //CONSTRUCTOR
    //
    /**
     * Es el constructor de la clase Monitor
     * @param primerNombre != null && != ""
     * @param segundoNombre
     * @param primerApellido != null && != ""
     * @param segundoApellido
     * @param codigo != null && != ""
     * @param estadoMatricula != null && != ""
     * @param foto 
     * @param promedioAcumulado > 0  && <= 5
     * @param semestreActual > 0  && <= 10
     * @param identificacion != null && != ""
     */
    public Estudiante( String primerNombre,String segundoNombre , String primerApellido, String segundoApellido, int codigo, String estadoMatricula, File foto, double promedioAcumulado, int semestreActual, String identificacion)
    {
        this.primerNombre = primerNombre;
        this.segundoNombre=segundoNombre;
        this.primerApellido=primerApellido;
        this.segundoApellido=segundoApellido;
        this.codigo = codigo;
        this.estadoMatricula = estadoMatricula;
        this.foto = foto;
        this.promedioAcumulado = promedioAcumulado;
        this.semestreActual = semestreActual;
        this.identificacion = identificacion;

    }
    /**
     * Metodo que retorna el primer nombre del monitor
     * @return primerNombre
     */
    public String darPrimerNombre() {
        return primerNombre;
    }
    /**
     * Metodo que cambia el primer nombre por el que entra por parametro
     * @param primerNombre != null && != ""
     */
    public void cambiarPrimerNombre(String primerNombre) {
        this.primerNombre=primerNombre;
    }
    public String darSegundoNombre() {
        return segundoNombre;
    }

    public void cambiarSegundoNombre(String segundoNombre) {
        this.segundoNombre=segundoNombre;
    }

    public String darPrimerApellido() {
        return primerApellido;
    }

    public void cambiarPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }
    
    public String darSegundoApellido() {
        return segundoApellido;
    }

    public void cambiarSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public int darCodigo() {
        return codigo;
    }

    public void cambiarCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String darEstadoMatricula() {
        return estadoMatricula;
    }

    public void cambiarEstadoMatricula(String estadoMatricula) {
        this.estadoMatricula = estadoMatricula;
    }

    public File darFoto() {
        return foto;
    }

    public void cambiarFoto(File foto) {
        this.foto = foto;
    }

    public double darPromedioAcumulado() {
        return promedioAcumulado;
    }

    public void cambiarPromedioAcumulado(double promedioAcumulado) {
        this.promedioAcumulado = promedioAcumulado;
    }

    public int darSemestreActual() {
        return semestreActual;
    }

    public void cambiarSemestreActual(int semestreActual) {
        this.semestreActual = semestreActual;
    }

    public String darIdentificacion() {
        return identificacion;
    }

    public void cambiarIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    @Override
    public String toString() {
        return "Estudiante{" + "primerNombre=" + primerNombre + ", segundoNombre=" + segundoNombre + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + ", codigo=" + codigo + ", estadoMatricula=" + estadoMatricula + ", foto=" + foto + ", promedioAcumulado=" + promedioAcumulado + ", semestreActual=" + semestreActual + ", identificacion=" + identificacion + '}';
    }
    
    public String toStringDatosPrincipales(){
        return "";
    }

}
