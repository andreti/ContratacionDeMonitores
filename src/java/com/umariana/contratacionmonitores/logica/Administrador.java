package com.umariana.contratacionmonitores.logica;

/**
 *  Es la clase administrador
 * @author CocoSoft
 */
public class Administrador {
    
    private String usuario;
    private String nombre;
    private String contraseña;

    public Administrador(String usuario, String nombre, String contraseña) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    public String darUsuario() {
        return usuario;
    }

    public void cambiarUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String darNombre() {
        return nombre;
    }

    public void cambiarNombre(String nombre) {
        this.nombre = nombre;
    }

    public String darContraseña() {
        return contraseña;
    }

    public void cambiarContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
