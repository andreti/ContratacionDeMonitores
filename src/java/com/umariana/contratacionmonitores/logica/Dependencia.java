package com.umariana.contratacionmonitores.logica;

import java.io.File;

public class Dependencia 
{
        //
    //ATRIBUTOS
    //
    
        /**
	 * Es el monitor 
	 */
        private String id;
         /**
	 * Es la dependencia
	 */
        private String nombre;
	/**
	 * Es la lista de dependencias existentes en el sistema
	 */
	private String descripcion;
	/**
	 * Es la lista de monitores existentes en el sistema
	 */
	private String horario;
	
    //
    //CONSTRUCTOR
    //
    /**
    * Es el constructor de la clase Monitor
    */
    public Dependencia( String nId, String nNombre, String nDescripcion, String nHorario)
    {
        id = nId;
        nombre = nNombre;
        descripcion = nDescripcion;
        horario = nHorario;
    }

    public String darId() {
        return id;
    }

    public void cambiarId(String id) {
        this.id = id;
    }

    public String darNombre() {
        return nombre;
    }

    public void cambiarNombre(String nombre) {
        this.nombre = nombre;
    }

    public String darDescripcion() {
        return descripcion;
    }

    public void cambiarDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String darHorario() {
        return horario;
    }

    public void cambiarHorario(String horario) {
        this.horario = horario;
    }
    /**
     * Metodo que retorna la informacion de la dependencia
     * @return String 
     */
    public String toString(){
        return id+" - "+nombre+" - "+" - "+ descripcion+" - "+horario;
    }

}
