/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umariana.contratacionmonitores.datos;

import com.umariana.contratacionmonitores.excepciones.ExcepcionNoExiste;
import com.umariana.contratacionmonitores.logica.Administrador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author SERVIDOR
 */
public class AdministradorDAO {
    
    private ResultSet rs;
    
    private final String tabla = "admin";
    
    public Administrador buscarAdministrador(String usuario, String password) throws ExcepcionNoExiste, SQLException{

        rs = ContratacionMonitoresDAO.getStBdContratacionMonitores().executeQuery(" select * from "+ tabla+" where usuario='" + usuario.trim() + "' and password ='"+password+"'");
        Administrador buscado = null;
        while (rs.next()) 
            buscado = new Administrador(usuario, rs.getString("nombre_completo"), password);
        
        rs.close();
        return buscado;
    }
    
}
