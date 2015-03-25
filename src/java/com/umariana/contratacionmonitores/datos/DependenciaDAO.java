package com.umariana.contratacionmonitores.datos;

import com.umariana.contratacionmonitores.logica.Dependencia;
import java.net.ConnectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andres
 */
public class DependenciaDAO {
    
    private ResultSet rs;
    private final String tabla="dependencia";
    
    public int resgistrarDependenciaEnBD(Dependencia dependencia) throws  SQLException  {
      
            rs = ContratacionMonitoresDAO.getStBdContratacionMonitores().executeQuery("insert into "+tabla+" (nombre,descripcion )values ('"+dependencia.darNombre()+"','"+dependencia.darDescripcion()+"') returning id");
            int idDependencia=0;
            while(rs.next()){
                idDependencia=rs.getInt("id");
                return idDependencia;
            }
            rs.close();
            return idDependencia;
    }
    
    public void eliminarDependencia(int codigo) throws SQLException {
            ContratacionMonitoresDAO.getStBdContratacionMonitores().executeUpdate("delete from "+tabla+" where id ="+codigo);
    }

    void actualizarDependencia(Dependencia dependencia) throws SQLException  {
        ContratacionMonitoresDAO.getStBdContratacionMonitores().executeUpdate("update "+tabla+" set nombre ='"+dependencia.darNombre()+"',descripcion='"+dependencia.darDescripcion()+"' where id ="+dependencia.darId());       
        
    }
    
    boolean existeNombreDependencia(int id, String nombre) throws SQLException{
        rs=ContratacionMonitoresDAO.getStBdContratacionMonitores().
                executeQuery("select * from "+tabla+" where id <>"+id+" and nombre ='"+nombre+"'");       
        while (rs.next())
        { 
            return true;
        }
        return false;
    }

    boolean existeDependencia(String nombre) throws SQLException {
        rs = ContratacionMonitoresDAO.getStBdContratacionMonitores().executeQuery("select nombre from "+tabla+" where nombre ='"+nombre+"'");
        while(rs.next()){
            return true;
        }
        return false;
    }

    ArrayList<Dependencia> listarDependencias() throws SQLException {
        ArrayList<Dependencia> dependencias= new ArrayList<>();
        rs = ContratacionMonitoresDAO.getStBdContratacionMonitores().executeQuery("select * from "+tabla);
        
        while(rs.next()){
            Dependencia d=new Dependencia();
            d.cambiarId(rs.getInt("id"));
            d.cambiarNombre(rs.getString("nombre"));
            d.cambiarDescripcion(rs.getString("descripcion"));
            dependencias.add(d);
        }
        return dependencias;
    }

    Dependencia buscarDependencia(String nombre) throws SQLException {
       Dependencia buscada = null;
        rs = ContratacionMonitoresDAO.getStBdContratacionMonitores().executeQuery("select * from "+tabla+" where nombre ='"+nombre+"'");
        while(rs.next()){
            buscada=new Dependencia();
            buscada.cambiarId(rs.getInt("id"));
            buscada.cambiarNombre(rs.getString("nombre"));
            buscada.cambiarDescripcion(rs.getString("descripcion"));
        }
        return buscada;
    }
    Dependencia buscarDependencia(int idDependencia) throws SQLException {
       Dependencia buscada = null;
        rs = ContratacionMonitoresDAO.getStBdContratacionMonitores().executeQuery("select * from "+tabla+" where id ='"+idDependencia+"'");
        while(rs.next()){
            buscada=new Dependencia();
            buscada.cambiarId(rs.getInt("id"));
            buscada.cambiarNombre(rs.getString("nombre"));
            buscada.cambiarDescripcion(rs.getString("descripcion"));
        }
        return buscada;
    }
}
