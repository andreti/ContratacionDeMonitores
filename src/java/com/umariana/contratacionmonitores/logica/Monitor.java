package com.umariana.contratacionmonitores.logica;

import java.io.File;


public class Monitor extends Estudiante
{

    /**
     * Es la dependencia donde esta realizando la monitoria
     */
    private Dependencia dependencia;
    /**
     * Es el puntaje total de las pruebas que realizo
     */
    private double puntaje_prueba;
    
    private int idHorario;
 
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
     * @param dependencia != null
     * @param puntaje_prueba > 0
     */
    public Monitor(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, int codigo, String estadoMatricula, File foto, double promedioAcumulado, int semestreActual, String identificacion, Dependencia dependencia, double puntaje_prueba) {
        super(primerNombre, segundoNombre, primerApellido, segundoApellido, codigo, estadoMatricula, foto, promedioAcumulado, semestreActual, identificacion);
        this.dependencia= dependencia;
        this.puntaje_prueba=puntaje_prueba;
    }

    public Monitor() {
        
    }
    /**
     * 
     * @return 
     */
    public Dependencia darDependencia() {
        return dependencia;
    }
    /**
     * 
     * @param dependencia 
     */
    public void cambiarDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }
    /**
     * 
     * @return 
     */
    public double darPuntaje_prueba() {
        return puntaje_prueba;
    }
    /**
     * 
     * @param puntaje_prueba 
     */
    public void cambiarPuntaje_prueba(double puntaje_prueba) {
        this.puntaje_prueba = puntaje_prueba;
    }
    
    public int darIdHorario() {
        return idHorario;
    }

    public void cambiarIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

}
