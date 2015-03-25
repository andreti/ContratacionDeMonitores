package com.umariana.contratacionmonitores.logica;

public class Resultado {
    /**
     * Es el id del resultado
     */
    private String id;
    /**
     * Es la identificacion del estudiante al que pertenece el resultado
     */
    private String idEstudiante;
    /**
     * Es la calificacion de la prueba
     */
    private int resultadoPrueba;
    /**
     * Es la calificacion de la entrevista
     */
    private int resultadoEntrevista;
    /**
     * Es el promedio de la prueba y la entrevista
     */
    private double promedioPruebas;

    /**
     * Es el contructor de la clase Resultado
     * @param id != null && !=""
     * @param idEstudiante != null && !=""
     * @param resultadoPrueba != null && !=""
     * @param resultadoEntrevista  != null && !=""
     */
    public Resultado(String id, String idEstudiante, int resultadoPrueba, int resultadoEntrevista) {
        
        this.id = id;
        this.idEstudiante = idEstudiante;
        this.resultadoPrueba = resultadoPrueba;
        this.resultadoEntrevista = resultadoEntrevista;
        
    }

    /**
     * Retorna el id del resultado
     * @return String
     */
    public String darId() {
        return id;
    }

    /**
     * Cambia el id del resultado por el que entra por parametro
     * @param id != null && !=""
     */
    public void cambiarId(String id) {
        this.id = id;
    }
    /**
     * Retorna la identificacion del estudiante
     * @return String
     */
    public String darIdEstudiante() {
        return idEstudiante;
    }
    /**
     * Cambia la identificacion del estudiante por el que entra por parametro
     * @param idEstudiante != null && !=""
     */
    public void cambiarIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }
    /**
     * Retorna el resultado de la prueba
     * @return int
     */
    public int darResultadoPrueba() {
        return resultadoPrueba;
    }
    /**
     * Cambia el resultado de la prueba por el que entra por parametro
     * @param resultadoPrueba >=0 && <= 100
     */
    public void cambiarResultadoPrueba(int resultadoPrueba) {
        this.resultadoPrueba = resultadoPrueba;
    }
    /**
     * Retorna el resultado de la entrevista
     * @return int
     */
    public int darResultadoEntrevista() {
        return resultadoEntrevista;
    }
    /**
     * Cambia el resultado de la entrevista por el que entra por parametro
     * @param resultadoEntrevista >=0 && <= 100
     */
    public void cambiarResultadoEntrevista(int resultadoEntrevista) {
        this.resultadoEntrevista = resultadoEntrevista;
    }
    /**
     * Retorna el promedio de la prueba y la entrevista
     * @return double
     */
    public double darPromedioPruebas() {
        
        promedioPruebas = (resultadoEntrevista+resultadoPrueba)/2;       
        return promedioPruebas;
    }   
}
