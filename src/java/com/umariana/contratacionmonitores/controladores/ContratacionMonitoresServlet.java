/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umariana.contratacionmonitores.controladores;

import com.umariana.contratacionmonitores.excepciones.ConnectionException;
import com.umariana.contratacionmonitores.excepciones.ExcepcionNoExiste;
import com.umariana.contratacionmonitores.excepciones.ExcepcionYaExiste;
import com.umariana.contratacionmonitores.logica.Aspirante;
import com.umariana.contratacionmonitores.logica.ContratacionMonitores;
import com.umariana.contratacionmonitores.logica.Dependencia;
import com.umariana.contratacionmonitores.logica.Estudiante;
import com.umariana.contratacionmonitores.logica.Monitor;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SERVIDOR
 */
public class ContratacionMonitoresServlet extends HttpServlet {
    
    /**
     * Es la sesion global de toda la aplicacion
     */
    private static HttpSession sesionGlobal;
    private static String usuarioActual;
    public  static ContratacionMonitores instance = new ContratacionMonitores();

    /**
     * Retorna la sesion de la aplicacion
     * @return  HttpSession
     */
    public static HttpSession darSession(){
         return sesionGlobal;
    }
    /**
     * Retorna la comunicacion con el paquete logica
     * @return  ContratacionMonitores
     */
    public static ContratacionMonitores darComunicacionLogica(){
        return instance;
    }
    public static void setearSesion(HttpSession sesionActualizada){
        sesionGlobal=sesionActualizada;
    }
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        
    }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)     throws ServletException, IOException {       
            try {
                String accion = request.getParameter("accion");
                sesionGlobal=request.getSession(true);
                switch (accion){
                    case"regDep":
                        
                        break;
                    case "buscarPorAdmin":
                        String identificacion = request.getParameter("txt_identificacion");
                        Object [] valores= instance.ingreso(identificacion);
                        int n = Integer.parseInt((String) valores[0]);
                        if(n==1){
                            Aspirante aspirante = (Aspirante)valores[1];
                            sesionGlobal.setAttribute("aspiranteBuscado", aspirante);
                        }
                        else if(n==2){
                            Monitor monitor = (Monitor)valores[1];
                            sesionGlobal.setAttribute("MonitorBuscado", monitor);
                        }
                        else if(n==3){
                            Estudiante estudiante = (Estudiante)valores[1];
                            sesionGlobal.setAttribute("estudianteBuscado", estudiante);
                        }                     
                        break;
                    /*case "ingreso":
                        identificacion = request.getParameter("txt_identificacion");
                        ingreso(identificacion);
                        break;*/
                    /*case "entrar":
                        String usuario = request.getParameter("txt_usuario");
                        String password = request.getParameter("txt_contrasena");
                        ingresarAdmin(usuario,password);
                        sesionGlobal.setAttribute("redireccionar", "admin.jsp");
                        break;*/
                    /*case "cerrar":
                        cerrarSesion();
                        break;*/
                        
                    /*case "reg_estu":
                        Estudiante estudiante = (Estudiante)sesionGlobal.getAttribute("estudianteBuscado");
                        if(estudiante==null){
                            estudiante = (Estudiante)sesionGlobal.getAttribute("estudiante");
                        }
                        String registrar=request.getParameter("select_registrar");
                        if(registrar.equals("si")){               
                            registrarAspirante(estudiante.darIdentificacion());
                        }
                        else
                            cerrarSesion();
                        break;        */   
                    case "eliminarD":
                        int idDependencia=Integer.parseInt(request.getParameter("idDependencia"));
                        Dependencia dependencia = instance.buscarDependencia(idDependencia);
                        removerAtributos();
                        sesionGlobal.setAttribute("eliminarDependencia", dependencia);
                        sesionGlobal.setAttribute("eliminarString", "dependencia");
                        break;
                    case "eliminarA":
                        identificacion=request.getParameter("identificacion");
                        Aspirante eliminarAspirante=instance.buscarAspirante(identificacion);
                        removerAtributos();
                        sesionGlobal.setAttribute("eliminarAspirante", eliminarAspirante);
                        sesionGlobal.setAttribute("eliminarString", "aspirante");
                        break;
                        
                    case "eliminarM":
                        identificacion=request.getParameter("identificacion");
                        Monitor eliminarMonitor=instance.buscarMonitor(identificacion);
                        removerAtributos();
                        sesionGlobal.setAttribute("eliminarMonitor", eliminarMonitor);
                        sesionGlobal.setAttribute("eliminarString", "monitor");
                        break;   
                    case "confirmarEliminar":                          
                        String elimi=request.getParameter("select_eliminar");
                        if(elimi.equals("si"))
                        {
                            Monitor em=(Monitor)sesionGlobal.getAttribute("eliminarMonitor");
                            Aspirante ea=(Aspirante)sesionGlobal.getAttribute("eliminarAspirante");
                            Dependencia ed= (Dependencia)sesionGlobal.getAttribute("eliminarDependencia");
                            
                            if(em!=null)
                                instance.eliminarMonitor(em.darIdentificacion());
                            else if(ea!=null)
                                instance.eliminarAspirante(ea.darIdentificacion());                           
                            else if(ed!=null)
                                instance.eliminarDependencia(ed.darId()); 
                            removerAtributos();
                        }
                        else{                       
                            removerAtributos();
                        }
                        break;                  
                    case"agregarDependencia":
                        int cod = Integer.parseInt(request.getParameter("txt_codigo"));
                        String nom = request.getParameter("txt_nombre");
                        String des = request.getParameter("txt_descripcion");
                        String jor = request.getParameter("slc_jornadaD");                       
                        int cup = Integer.parseInt(request.getParameter("txt_cupos")); 
                        sesionGlobal.setAttribute("redireccionar", "dependencia.jsp");
                        instance.agregarDependencia(cod, nom, des);         
                        break;
                        
                }
                if(usuarioActual!=null){
                    switch(usuarioActual){
                        case "admin":
                            String redireccionar =(String) sesionGlobal.getAttribute("redireccionar");
                            if(redireccionar!=null)
                            {
                                sesionGlobal.removeAttribute("redireccionar");
                                response.sendRedirect(redireccionar);
                                
                            }
                            else{
                                String eliminar=(String) sesionGlobal.getAttribute("eliminarString");
                                if(eliminar!=null)
                                {
                                    if(eliminar.equals("monitor")){
                                        response.sendRedirect("monitor.jsp");
                                        sesionGlobal.setAttribute("redireccionar", "monitor.jsp");
                                    }
                                    else if(eliminar.equals("aspirante")){
                                        response.sendRedirect("aspirante.jsp");
                                        sesionGlobal.setAttribute("redireccionar", "aspirante.jsp");
                                    }
                                    else if(eliminar.equals("dependencia")){
                                        response.sendRedirect("dependencia.jsp");
                                        sesionGlobal.setAttribute("redireccionar", "dependencia.jsp");
                                     }
                                }
                                else
                                    response.sendRedirect("admin.jsp");
                            }
                            break;
                        case "aspirante":
                            response.sendRedirect("estudiante.jsp");
                            break;
                        case "monitor":
                            response.sendRedirect("estudiante.jsp");
                            break;
                        case "estudiante":
                            response.sendRedirect("estudiante.jsp");
                            break;
                        case "":
                            response.sendRedirect("index.jsp");
                            break;
                        case "null":
                            response.sendRedirect("index.jsp");
                            break;  
                    }
                }
                else{
                    String redireccionar =(String) sesionGlobal.getAttribute("redireccionar");
                    if(redireccionar!=null)
                    {
                        sesionGlobal.removeAttribute("redireccionar");                
                        response.sendRedirect(redireccionar);  
                    }
                    else
                    {               
                        response.sendRedirect("index.jsp");
                    }
                }
            } catch (ExcepcionNoExiste | ExcepcionYaExiste  ex) {
                String redireccionar =(String) sesionGlobal.getAttribute("redireccionar");
                if(redireccionar!=null)
                {
                    sesionGlobal.removeAttribute("redireccionar");
                    sesionGlobal.setAttribute("mensaje", ex.getMessage());     
                    response.sendRedirect(redireccionar);  
                }
                else
                {
                    sesionGlobal.setAttribute("mensaje", ex.getMessage());      
                    response.sendRedirect("index.jsp");
                }
            } catch (SQLException | ConnectionException ex) {
            Logger.getLogger(ContratacionMonitoresServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void removerAtributos(){
        sesionGlobal.removeAttribute("eliminarMonitor");      
        sesionGlobal.removeAttribute("eliminarAspirante");          
        sesionGlobal.removeAttribute("eliminarDependencia");     
        sesionGlobal.removeAttribute("eliminarString");
    }
    
    /*public void ingreso(String identificacion){
        try {
            cerrarSesion();
            Object[] valores = instance.ingreso(identificacion);
            int n = Integer.parseInt((String) valores[0]);
            if(n==1){
                Aspirante aspirante = (Aspirante)valores[1];
                usuarioActual="aspirante";
                sesionGlobal.setAttribute("aspirante", aspirante);
            } 
            else if(n==2){
                Monitor monitor = (Monitor)valores[1];
                usuarioActual="monitor";
                sesionGlobal.setAttribute("monitor", monitor);
            }
            else if(n==3){
                Estudiante estudiante = (Estudiante)valores[1];
                usuarioActual="estudiante";
                sesionGlobal.setAttribute("estudiante", estudiante);  
                sesionGlobal.removeAttribute("mensaje");
            }
            else if(n==4){
                sesionGlobal.setAttribute("mensaje", "La identificación : +"+identificacion+" no corresponde a ningun estudiante de la Universidad");
            }
            
        } catch ( ExcepcionNoExiste ex) {
            sesionGlobal.setAttribute("mensaje", ex.getMessage());         
        }
    }*/
    
    
    /*public void registrarAspirante(String identificacion){
            try {
                cerrarSesion();
                Aspirante aspirante =instance.registrarAspirante2(identificacion);
                sesionGlobal.setAttribute("mensaje","Se registro Correctamente el estudiante con identificacion : "+aspirante.darIdentificacion());
                sesionGlobal.setAttribute("aspirante", aspirante);
                usuarioActual="aspirante";
                
            } catch (ExcepcionYaExiste | ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
                sesionGlobal.setAttribute("mensaje", ex.getMessage());
            }
    }*/
    public void cerrarSesion(){
        sesionGlobal.removeAttribute(usuarioActual);
        sesionGlobal.removeAttribute("eliminar");
        sesionGlobal.removeAttribute("mensaje");
        sesionGlobal.removeAttribute("aspiranteBuscado");
        sesionGlobal.removeAttribute("MonitorBuscado");
        sesionGlobal.removeAttribute("estudianteBuscado");
    }
    
    public static void conectarBD(){
        try {
            instance.conectar();
        } catch (ConnectionException ex) {
            Logger.getLogger(ContratacionMonitoresServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    /*private void ingresarAdmin(String usuario, String password) {
            try {
                Administrador admin= instance.ingresoAdmin(usuario,password);
                cerrarSesion();
                usuarioActual="admin";    
                sesionGlobal.setAttribute("admin", admin);
            } catch (ExcepcionNoExiste ex) {
                sesionGlobal.setAttribute("mensaje", ex.getMessage());
            }
    }*/
}
