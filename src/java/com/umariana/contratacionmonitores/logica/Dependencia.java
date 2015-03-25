package com.umariana.contratacionmonitores.logica;

import com.umariana.contratacionmonitores.logica.dependencia.Horario;
import com.umariana.contratacionmonitores.logica.dependencia.Jornada;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;

public class Dependencia 
{
        //
    //ATRIBUTOS
    //
    
        /**
	 * Es el monitor 
	 */
        private int id;
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
        /**
         * Son los cupos disponibles que tiene la dependencia
         */
        private int totalCuposDisponibles;
         /**
         * Son los cupos que tiene la dependencia
         */
        private int totalCupos;
        /**
         * 
         */
        private List<Jornada> jornadas;
	
    //
    //CONSTRUCTOR
    //
    /**
    * Es el constructor de la clase Monitor
    */
    public Dependencia( int nId, String nNombre, String nDescripcion, String nHorario, int cupos)
    {
        id = nId;
        nombre = nNombre;
        descripcion = nDescripcion;
        horario = nHorario;
        this.totalCupos = cupos;
        jornadas= new ArrayList<>();
    }
    
    public Dependencia(String nombre,String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public Dependencia(){
        jornadas= new ArrayList<>();
    }

    public int darId() {
        return id;
    }

    public void cambiarId(int id) {
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

    
    public int getTotalCuposDisponibles() {
        actualizarAtributoCupos();
        return totalCuposDisponibles;
    }

    public void setTotalCuposDisponibles(int totalCuposDisponibles) {
        this.totalCuposDisponibles = totalCuposDisponibles;
    }

    public int getTotalCupos() {
        actualizarAtributoCupos();
        return totalCupos;
    }

    public void setTotalCupos(int totalCupos) {
        this.totalCupos = totalCupos;
    }

    /**
     * 
     * @return 
     */
    public List<Jornada> darJornadas() {
        return jornadas;
    }
    /**
     * 
     * @param jornadas 
     */
    public void cambiarJornadas(List<Jornada> jornadas) {
        this.jornadas = jornadas;
    }

    @Override
    public String toString() {
        return "Dependencia{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", jornadas=" + jornadas + '}';
    }

    public void agregarJornada(Jornada jornada) {
        boolean salir=false;
        for(int i=0;i<jornadas.size() && !salir;i++){
            Jornada j= jornadas.get(i);
            if(j.getJornada().equalsIgnoreCase(jornada.getJornada())){
                salir=true;
                for(Horario h:jornada.getHorarios()){
                    j.agregarHorario(h);
                }
            }
        }
        if(!salir)
            jornadas.add(jornada);
    }

    public void actualizarAtributoCupos() {
        totalCupos=0;
        totalCuposDisponibles=0;
        for(Jornada j:jornadas){
            totalCupos+=j.getTotalCupos();
            totalCuposDisponibles+=j.getTotalCuposDisponibles();
        } 
    }
    
    public void eliminarJornada(String idJornada){
        for(Jornada j:jornadas){         
            if(j.getId()==Integer.parseInt(idJornada)){
                jornadas.remove(j);
            }
        }
    }

    public void eliminarHorario(String idHorario) {
        for(int n=0;n<jornadas.size();n++){
            Jornada j=jornadas.get(n);
            for(int i=0;i<j.getHorarios().size();i++){
                Horario h=j.getHorarios().get(i);
                if(h.getId()==Integer.parseInt(idHorario)){
                    j.getHorarios().remove(h);
                    i=j.getHorarios().size();
                }
            }
            if(j.getHorarios().isEmpty()){
                jornadas.remove(j);
                n=jornadas.size();
            }
        }
    }
    
    
    
}
