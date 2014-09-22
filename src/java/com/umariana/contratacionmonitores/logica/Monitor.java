package com.umariana.contratacionmonitores.logica;

import java.io.File;


public class Monitor extends Estudiante
{

    /**
     * Es la dependencia donde esta realizando la monitoria
     */
    private Dependencia dependencia;
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
     */
    public Monitor(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, int codigo, String estadoMatricula, File foto, double promedioAcumulado, int semestreActual, String identificacion, Dependencia dependencia) {
        super(primerNombre, segundoNombre, primerApellido, segundoApellido, codigo, estadoMatricula, foto, promedioAcumulado, semestreActual, identificacion);
        this.dependencia= dependencia;
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
   
    

}
