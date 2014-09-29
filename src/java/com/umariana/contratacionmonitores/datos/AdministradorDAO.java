/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umariana.contratacionmonitores.datos;

import com.umariana.contratacionmonitores.excepciones.ExcepcionNoExiste;
import com.umariana.contratacionmonitores.logica.Administrador;

/**
 *
 * @author SERVIDOR
 */
public class AdministradorDAO {
    
    public Administrador buscarAdministrador(String usuario, String password) throws ExcepcionNoExiste{
        Administrador admin = new Administrador( "admin","andres", "123");
        if(admin.darUsuario().equalsIgnoreCase(usuario) && admin.darContraseña().equals(password)){
            return admin;
        }
        else
            throw  new ExcepcionNoExiste("Datos incorrectos!!");
    }
    
}
