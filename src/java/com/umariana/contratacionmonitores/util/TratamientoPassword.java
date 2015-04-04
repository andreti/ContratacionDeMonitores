/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umariana.contratacionmonitores.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Andres
 */
public class TratamientoPassword {

    /**
     * Algoritmos disponibles en las librerias de Java
     */
    public static String MD2 = "MD2";
    public static String MD5 = "MD5";
    public static String SHA1 = "SHA-1";
    public static String SHA256 = "SHA-256";
    public static String SHA384 = "SHA-384";
    public static String SHA512 = "SHA-512";
    /**
     * Es la constante que utiliza el metodo de encriptar, la cual se le puede asginar los anteriores algoritmos
     */
    private static final String UTILIZAR_ALGORITMO = SHA1;

     /**
     * Convertir un arreglo de bites a tipo String, utilizando valores
     * Hezadecimales
     *
     * @param bites es el arreglo que contiene byte
     * @return cadenaString el arreglo de bites convertido en una cadena de tipo
     * String
     */
    private static String convertirAHexadecimal(byte[] bites) {
        String cadenaString = "";
        for (byte aux : bites) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) {
                cadenaString += "0";
            }
            cadenaString += Integer.toHexString(b);
        }
        return cadenaString;
    }

    /**
     * Encripta la contraseña del usuario o por el algoritmo que este definido en la constante, puede ser MD2, MD5, SHA-1, SHA-256, SHA-384, SHA-512
     * @param password contraseña a encriptar
     * @return cadena encriptada
     */
    public static String encriptar(String password) {
        byte[] digest = null;
        byte[] buffer = password.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(UTILIZAR_ALGORITMO);
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error en la encriptación: " + ex.getMessage());
        }
        return convertirAHexadecimal(digest);
    }

    static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    
    
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        /*System.out.println(sha1("test string to sha1"));
        String mensaje = "123";
        System.out.println("Mensaje = " + mensaje);
        System.out.println("MD2 = " + TratamientoPassword.encriptar(mensaje, TratamientoPassword.MD2));
        System.out.println("MD5 = " + TratamientoPassword.encriptar(mensaje, TratamientoPassword.MD5));
        System.out.println("SHA-1 = " + TratamientoPassword.encriptar(mensaje, TratamientoPassword.SHA1));
        System.out.println("SHA-256 = " + TratamientoPassword.encriptar(mensaje, TratamientoPassword.SHA256));
        System.out.println("SHA-384 = " + TratamientoPassword.encriptar(mensaje, TratamientoPassword.SHA384));
        System.out.println("SHA-512 = " + TratamientoPassword.encriptar(mensaje, TratamientoPassword.SHA512));*/
        System.out.println("SHA-1 = " + TratamientoPassword.encriptar("HOLA"));
        System.out.println("SHA-1 = " + TratamientoPassword.encriptar("hola"));
    }
}
