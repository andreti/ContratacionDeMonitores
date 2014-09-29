/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.umariana.contratacionmonitores.datos;

import com.umariana.contratacionmonitores.logica.Estudiante;
import java.util.ArrayList;

/**
 *
 * @author Andres
 */
public class EstudianteUniversidadDAO {

    public Estudiante buscarEstudiante(String identificacion) {
        ArrayList<Estudiante> estudiantes = new ArrayList();
        
        Estudiante estudiante1= new Estudiante("primerN", "segundoN", "pirmerA", "segundoA", 1, "estadoMaatricula", null, 5.0, 8, "201");
        Estudiante estudiante2= new Estudiante("primerN", "segundoN", "pirmerA", "segundoA", 1, "estadoMaatricula", null, 5.0, 8, "202");
        Estudiante estudiante3= new Estudiante("primerN", "segundoN", "pirmerA", "segundoA", 1, "estadoMaatricula", null, 5.0, 8, "203");
        
        estudiantes.add(estudiante1);
        estudiantes.add(estudiante2);
        estudiantes.add(estudiante3);
        
        for(Estudiante est: estudiantes){
            if(est.darIdentificacion().equals(identificacion))
                return est;
        }
        
        return null;
    }
    
}
