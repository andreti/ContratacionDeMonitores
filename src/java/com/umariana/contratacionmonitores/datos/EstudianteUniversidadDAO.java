package com.umariana.contratacionmonitores.datos;

import com.umariana.contratacionmonitores.logica.Estudiante;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Andres
 */
public class EstudianteUniversidadDAO {
    
    
    private ResultSet rs=null;

    public  Estudiante buscarEstudiante(String identificacion) throws SQLException {
        
        rs=ContratacionMonitoresDAO.getStBdUniversidad().
                executeQuery(" select * from estudiante where identificacion ='"+identificacion.trim()+"'");       
        Estudiante buscado = null;
        while (rs.next())
        { 
            buscado = new Estudiante();
            buscado.cambiarIdentificacion(identificacion);
            buscado.cambiarPrimerNombre(rs.getString("nombre1"));
            buscado.cambiarSegundoNombre(rs.getString("nombre2"));
            buscado.cambiarPrimerApellido(rs.getString("apellido1"));
            buscado.cambiarSegundoApellido(rs.getString("apellido2"));
            buscado.cambiarSemestreActual(Integer.parseInt(rs.getString("semestre_actual")));
            buscado.cambiarEstadoMatricula(rs.getString("estado_matricula"));
            buscado.cambiarPromedioAcumulado(Double.parseDouble(rs.getString("promedio_acumulado")));	
            buscado.cambiarFoto(null);
        }
        rs.close();
        
        
        
        /*ArrayList<Estudiante> estudiantes = new ArrayList();
        
        Estudiante estudiante1= new Estudiante("primerN", "segundoN", "pirmerA", "segundoA", 1, "estadoMaatricula", null, 1.0, 8, "201");
        Estudiante estudiante2= new Estudiante("primerN", "segundoN", "pirmerA", "segundoA", 2, "estadoMaatricula", null, 5.0, 1, "202");
        Estudiante estudiante3= new Estudiante("primerN", "segundoN", "pirmerA", "segundoA", 3, "estadoMaatricula", null, 5.0, 8, "203");
        Estudiante estudiante4= new Estudiante("primerN", "segundoN", "pirmerA", "segundoA", 4, "estadoMaatricula", null, 2.0, 2, "204");
        
        estudiantes.add(estudiante1);
        estudiantes.add(estudiante2);
        estudiantes.add(estudiante3);
        estudiantes.add(estudiante4);
        
        for(Estudiante est: estudiantes){
            if(est.darIdentificacion().equals(identificacion))
                return est;
        }*/
        
        return buscado;
    }
    
    
    public void registrarEstudiante(Estudiante nuevo) throws SQLException{
        ContratacionMonitoresDAO.getStBdUniversidad().execute("insert into estudiante (identificacion,nombre1,nombre2,apellido1,apellido2,estado_matricula,promedio_acumulado,semestre_actual )"
                + "values ("+nuevo.darIdentificacion()+",'"+nuevo.darPrimerNombre()+"','"+nuevo.darSegundoNombre()+"','"+nuevo.darPrimerApellido()
                +"','"+nuevo.darSegundoApellido()+"','"+nuevo.darEstadoMatricula()+"',"+nuevo.darPromedioAcumulado()+","+nuevo.darSemestreActual()+")");
    }
 
}
