package com.umariana.contratacionmonitores.datos;

import com.umariana.contratacionmonitores.excepciones.ExcepcionYaExiste;
import com.umariana.contratacionmonitores.logica.Aspirante;
import com.umariana.contratacionmonitores.logica.Estudiante;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CocoSoft
 */
public class AspiranteDAO {
    private ResultSet rs;
    private String tabla= "estudiante_aspirante";

    public Aspirante resgistrarAspiranteEnBD(Estudiante estudiante) throws ExcepcionYaExiste, SQLException  {
      
            ContratacionMonitoresDAO.getStBdContratacionMonitores().executeUpdate("insert into "+tabla+" (identificacion,nombre1,nombre2,apellido1,apellido2,estado_matricula,promedio_acumulado,semestre_actual,puntaje_prueba,puntaje_entrevista,puntaje_total )"
                    + "values ('"+estudiante.darIdentificacion().trim()+"','"+estudiante.darPrimerNombre()+"','"+estudiante.darSegundoNombre()+"','"+estudiante.darPrimerApellido()
                    +"','"+estudiante.darSegundoApellido()+"','"+estudiante.darEstadoMatricula()+"','"+estudiante.darPromedioAcumulado()+"','"+estudiante.darSemestreActual()+"',"+0+","+0+","+0+")");
            
            Aspirante aspirante = new Aspirante(estudiante.darPrimerNombre(),estudiante.darSegundoNombre(),estudiante.darPrimerApellido(), estudiante.darSegundoApellido(), estudiante.darCodigo(), estudiante.darEstadoMatricula(), estudiante.darFoto(), estudiante.darPromedioAcumulado(), estudiante.darSemestreActual(), estudiante.darIdentificacion());
            
            return aspirante;
            //throw  new ExcepcionYaExiste("El estudiante "+estudiante.darPrimerNombre()+" "+estudiante.darPrimerApellido() +" con identificacion "+estudiante.darIdentificacion() +". Ya se se encuentra registrado como aspirante. Error "+ex.getMessage());

    }
    
    public void eliminarAspirante(String identificacion) throws SQLException{
        ContratacionMonitoresDAO.getStBdContratacionMonitores().executeUpdate("delete from "+tabla+" where identificacion ="+identificacion.trim());
    }

    public Aspirante buscarAspirante(String identificacion) throws SQLException {
        rs=ContratacionMonitoresDAO.getStBdContratacionMonitores().
                executeQuery(" select * from "+tabla+" where identificacion ='"+identificacion.trim()+"'");       
        Aspirante buscado = null;
        while (rs.next())
        { 
            buscado = new Aspirante();
            buscado.cambiarIdentificacion(identificacion);
            buscado.cambiarPrimerNombre(rs.getString("nombre1"));
            buscado.cambiarSegundoNombre(rs.getString("nombre2"));
            buscado.cambiarPrimerApellido(rs.getString("apellido1"));
            buscado.cambiarSegundoApellido(rs.getString("apellido2"));
            buscado.cambiarSemestreActual(Integer.parseInt(rs.getString("semestre_actual")));
            buscado.cambiarEstadoMatricula(rs.getString("estado_matricula"));
            buscado.cambiarPromedioAcumulado(Double.parseDouble(rs.getString("promedio_acumulado")));	
            buscado.cambiarPromedioAcumulado(Integer.parseInt(rs.getString("puntaje_prueba")));
            buscado.cambiarPromedioAcumulado(Integer.parseInt(rs.getString("puntaje_entrevista")));	
            buscado.cambiarPromedioAcumulado(Double.parseDouble(rs.getString("puntaje_total")));
            buscado.cambiarFoto(null);
        }
        rs.close();
        return buscado;
    }
    public ArrayList<Aspirante> darAspirantes() throws SQLException {
        ArrayList<Aspirante> aspirantes=new ArrayList<Aspirante>();
        rs=ContratacionMonitoresDAO.getStBdContratacionMonitores().executeQuery(" select * from "+tabla);       
        while (rs.next())
        { 
            Aspirante buscado = new Aspirante();
            buscado.cambiarIdentificacion(rs.getString("identificacion"));
            buscado.cambiarPrimerNombre(rs.getString("nombre1"));
            buscado.cambiarSegundoNombre(rs.getString("nombre2"));
            buscado.cambiarPrimerApellido(rs.getString("apellido1"));
            buscado.cambiarSegundoApellido(rs.getString("apellido2"));
            buscado.cambiarSemestreActual(Integer.parseInt(rs.getString("semestre_actual")));
            buscado.cambiarEstadoMatricula(rs.getString("estado_matricula"));
            buscado.cambiarPromedioAcumulado(Double.parseDouble(rs.getString("promedio_acumulado")));	
            buscado.cambiarPromedioAcumulado(Integer.parseInt(rs.getString("puntaje_prueba")));
            buscado.cambiarPromedioAcumulado(Integer.parseInt(rs.getString("puntaje_entrevista")));	
            buscado.cambiarPromedioAcumulado(Double.parseDouble(rs.getString("puntaje_total")));
            buscado.cambiarFoto(null);
            aspirantes.add(buscado);
        }
        rs.close();
        return aspirantes;
    }

    void actualizarAspirante(Aspirante aspirante) throws SQLException {
        ContratacionMonitoresDAO.getStBdContratacionMonitores().
                executeUpdate("update "+tabla+" set nombre1='"+aspirante.darPrimerNombre()+"', apellido1='"+ aspirante.darPrimerApellido()+"' where identificacion='"+aspirante.darIdentificacion()+"'");
    }
    
}
