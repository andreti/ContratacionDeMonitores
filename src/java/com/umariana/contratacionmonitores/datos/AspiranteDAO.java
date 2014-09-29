package com.umariana.contratacionmonitores.datos;

import com.umariana.contratacionmonitores.logica.Aspirante;
import com.umariana.contratacionmonitores.logica.Estudiante;

/**
 *
 * @author CocoSoft
 */
public class AspiranteDAO {

    public Aspirante resgistrarAspiranteEnBD(Estudiante estudiante) {
        
        //Registrar estudiante en base de datos
        
        System.out.println("Aspirante Registrado correctamente!! -->>  /n"+estudiante.toString());
        
        Aspirante aspirante = new Aspirante(estudiante.darPrimerNombre(),estudiante.darSegundoNombre(),estudiante.darPrimerApellido(), estudiante.darSegundoApellido(), estudiante.darCodigo(), estudiante.darEstadoMatricula(), estudiante.darFoto(), estudiante.darPromedioAcumulado(), estudiante.darSemestreActual(), estudiante.darIdentificacion());
        
        return aspirante;
    }
    
    public void eliminarAspirante(String identificacion){
        
    }
    
}
