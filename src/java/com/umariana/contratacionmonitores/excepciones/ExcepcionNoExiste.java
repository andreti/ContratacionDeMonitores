/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umariana.contratacionmonitores.excepciones;

/**
 *
 * @author SERVIDOR
 */
public class ExcepcionNoExiste extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionNoExiste</code> without detail
     * message.
     */
    public ExcepcionNoExiste() {
    }

    /**
     * Constructs an instance of <code>ExcepcionNoExiste</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionNoExiste(String msg) {
        super(msg);
    }
}
