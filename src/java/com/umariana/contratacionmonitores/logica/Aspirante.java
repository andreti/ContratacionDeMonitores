package com.umariana.contratacionmonitores.logica;

import com.umariana.contratacionmonitores.excepciones.ExcepcionNoExiste;
import com.umariana.contratacionmonitores.excepciones.ExcepcionYaExiste;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Aspirante extends Estudiante{
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
    public void quitarPostulacion(String idDependencia) throws ExcepcionNoExiste
    {    
        if(buscarPostulacion(idDependencia)!=null)
            postulaciones.remove(idDependencia);
        else
            throw  new ExcepcionNoExiste("La postulacion que desea eliminar no existe!!");
    } 
    /**
     * 
     * @param agregarPostulacion
     * @throws ExcepcionYaExiste 
     */
    public void agregarPostulacion(String idDependencia, Date fechaPostulacion, String identificaiconAspirante) throws ExcepcionYaExiste
    {
        if(buscarPostulacion(idDependencia)==null){
            Postulacion nuevaPostulacion= new Postulacion(idDependencia, fechaPostulacion, identificaiconAspirante);
            postulaciones.add(nuevaPostulacion);
        }
        else
            throw  new ExcepcionYaExiste("Ya se ha postulado a esta dependencia anteriormente!!");
    }
    /**
     * 
     * @param idPostulacion
     * @return 
     */
    public Postulacion buscarPostulacion(String idDependencia){
        
        for(Postulacion buscarPostulacion: postulaciones ){
            if(buscarPostulacion.darIdDependencia().equals(idDependencia))
                return buscarPostulacion;
        }
        return null;
    }
    
}
