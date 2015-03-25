package com.umariana.contratacionmonitores.datos;

import com.umariana.contratacionmonitores.logica.Postulacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Andres
 */
public class PostulacionDAO {
    private ResultSet rs;
    private String tabla= "postulacion";
    void registrarPostulacion(Postulacion p) throws SQLException {
        ContratacionMonitoresDAO.getStBdContratacionMonitores().executeUpdate("insert into "+tabla+" (id_horario,identificacion_estudiante)values ('"+p.getDependencia().darJornadas().get(0).getHorarios().get(0).getId()+"',"+p.getIdentificacionEstudiante()+")");    
    }

    ArrayList<Postulacion> buscarPostulaciones(String identificacion) throws SQLException {
        rs=ContratacionMonitoresDAO.getStBdContratacionMonitores().
                executeQuery(" select * from "+tabla+" where identificacion_estudiante ='"+identificacion+"'");       
        ArrayList<Postulacion> postulaciones= new ArrayList<>();
        while (rs.next())
        { 
            Postulacion p = new Postulacion();
            p.setIdHorario(rs.getInt("id_horario"));
            p.setIdentificacionEstudiante(rs.getString("identificacion_estudiante"));
            postulaciones.add(p);
        }
        rs.close();
        return postulaciones; 
    }

    void eliminarPostulacion(int idHorario) throws SQLException {
        ContratacionMonitoresDAO.getStBdContratacionMonitores().executeUpdate("delete from "+tabla+" where id_horario ="+idHorario);
    }

    
}
