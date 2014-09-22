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
public class ExcepcionYaExiste extends Exception {

    /**
     * Creates a new instance of <code>ExcepcionYaExiste</code> without detail
     * message.
     */
    public ExcepcionYaExiste() {
    }

    /**
     * Constructs an instance of <code>ExcepcionYaExiste</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionYaExiste(String msg) {
        super(msg);
    }
}
