package com.umariana.contratacionmonitores.logica;

import com.umariana.contratacionmonitores.excepciones.ExcepcionNoExiste;
import java.io.File;
import java.util.ArrayList;

public class Aspirante extends Estudiante{
    private double puntajePruebas;
    
    /**
     * Es la lista de postulaciones a las que se ha inscrito el estudiante
     */
    private ArrayList<Postulacion> postulaciones;
    /**
     * Es el constructor de la clase Aspirante
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
    public Aspirante(String primerNombre,String segundoNombre , String primerApellido, String segundoApellido, int codigo, String estadoMatricula, File foto, double promedioAcumulado, int semestreActual, String identificacion)
    {
        super(primerNombre, segundoNombre, primerApellido, segundoApellido, codigo, estadoMatricula, foto, promedioAcumulado, semestreActual, identificacion);
        postulaciones = new ArrayList<Postulacion>();
        puntajePruebas=0;
    }  
    public Aspirante() {
        postulaciones = new ArrayList<Postulacion>();
        puntajePruebas=0;
    }
    /**
     * 
     * @return 
     */
    public ArrayList<Postulacion> darPostulaciones() 
    {
        return postulaciones;
    }
    /**
     * 
     * @param postulaciones 
     */
    public void cambiarPostulaciones(ArrayList<Postulacion> postulaciones) 
    {
        this.postulaciones = postulaciones;
    }
    /**
     * 
     * @param eliminarPostulacion
     * @throws ExcepcionNoExiste 
     */
    public void quitarPostulacion(int idDependencia) throws ExcepcionNoExiste
    {    
        if(buscarPostulacion(idDependencia)!=null)
            postulaciones.remove(idDependencia);
        else
            throw  new ExcepcionNoExiste("La postulacion que desea eliminar no existe!!");
    } 
    /**
     * 
     * @param idDependencia
     * @return 
     */
    public Postulacion buscarPostulacion(int idDependencia){
        
        for(Postulacion buscarPostulacion: postulaciones ){
            if(buscarPostulacion.getDependencia().darId()==idDependencia)
                return buscarPostulacion;
        }
        return null;
    }   

    public void agregarPostulacion(Postulacion p) {
        postulaciones.add(p);
    }

    public double getPuntajePruebas() {
        return puntajePruebas;
    }

    public void setPuntajePruebas(double puntajePruebas) {
        this.puntajePruebas = puntajePruebas;
    }
    
    
}
