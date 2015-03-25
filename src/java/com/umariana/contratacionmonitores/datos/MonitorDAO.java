package com.umariana.contratacionmonitores.datos;
import com.umariana.contratacionmonitores.logica.Monitor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CocoSoft
 */
public class MonitorDAO {

    private ResultSet rs;

    private final String tabla = "estudiante_monitor";

    Monitor buscarMonitor(String identificacion) throws SQLException {

        rs = ContratacionMonitoresDAO.getStBdContratacionMonitores().
                executeQuery(" select * from " + tabla + " where identificacion ='" + identificacion.trim() + "'");
        Monitor buscado = null;
        while (rs.next()) {
            buscado = new Monitor();
            buscado.cambiarIdentificacion(identificacion);
            buscado.cambiarPrimerNombre(rs.getString("nombre1"));
            buscado.cambiarSegundoNombre(rs.getString("nombre2"));
            buscado.cambiarPrimerApellido(rs.getString("apellido1"));
            buscado.cambiarSegundoApellido(rs.getString("apellido2"));
            buscado.cambiarSemestreActual(Integer.parseInt(rs.getString("semestre_actual")));
            buscado.cambiarEstadoMatricula(rs.getString("estado_matricula"));
            buscado.cambiarPromedioAcumulado(Double.parseDouble(rs.getString("promedio_acumulado")));
            buscado.cambiarPuntaje_prueba(Integer.parseInt(rs.getString("puntaje_prueba")));
            buscado.cambiarIdHorario(Integer.parseInt(rs.getString("id_horario")));
            buscado.cambiarFoto(null);
        }
        rs.close();
        return buscado;
    }

    Monitor resgistrarMonitorEnBD(Monitor monitor) throws SQLException {

        ContratacionMonitoresDAO.getStBdContratacionMonitores().executeUpdate("insert into " + tabla + " (identificacion,nombre1,nombre2,apellido1,apellido2,estado_matricula,promedio_acumulado,semestre_actual,puntaje_prueba,id_dependencia,id_horario )"
                + "values ('" + monitor.darIdentificacion() + "','" + monitor.darPrimerNombre() + "','" + monitor.darSegundoNombre() + "','" + monitor.darPrimerApellido()
                + "','" + monitor.darSegundoApellido() + "','" + monitor.darEstadoMatricula() + "','" + monitor.darPromedioAcumulado() + "','" + monitor.darSemestreActual() + "'," + monitor.darPuntaje_prueba() + "," + monitor.darDependencia().darId() + "," + monitor.darIdHorario()+ ")");
        return monitor;
    }

    void eliminarMonitor(String identificacion) throws SQLException {
        ContratacionMonitoresDAO.getStBdContratacionMonitores().executeUpdate("delete from "+tabla+" where identificacion ="+identificacion.trim());
    }

    void actualizarMonitor(Monitor monitor) throws SQLException {
        ContratacionMonitoresDAO.getStBdContratacionMonitores().
                executeUpdate("update "+tabla+" set nombre1='"+monitor.darPrimerNombre()+"', apellido1='"+ monitor.darPrimerApellido()+"' where identificacion='"+monitor.darIdentificacion()+"'");
    }

    ArrayList<Monitor> darMonitores() throws SQLException {
        ArrayList<Monitor> monitores= new ArrayList<>();
        rs = ContratacionMonitoresDAO.getStBdContratacionMonitores().executeQuery(" select * from " + tabla);
        
        while (rs.next()) {
            Monitor buscado = new Monitor();
            buscado.cambiarIdentificacion(rs.getString("identificacion"));
            buscado.cambiarPrimerNombre(rs.getString("nombre1"));
            buscado.cambiarSegundoNombre(rs.getString("nombre2"));
            buscado.cambiarPrimerApellido(rs.getString("apellido1"));
            buscado.cambiarSegundoApellido(rs.getString("apellido2"));
            buscado.cambiarSemestreActual(Integer.parseInt(rs.getString("semestre_actual")));
            buscado.cambiarEstadoMatricula(rs.getString("estado_matricula"));
            buscado.cambiarPromedioAcumulado(Double.parseDouble(rs.getString("promedio_acumulado")));
            buscado.cambiarPromedioAcumulado(Integer.parseInt(rs.getString("puntaje_prueba")));
            buscado.cambiarIdHorario(Integer.parseInt(rs.getString("id_horario")));
            buscado.cambiarFoto(null);
            monitores.add(buscado);
        }
        rs.close();
        return monitores;
    }
}
