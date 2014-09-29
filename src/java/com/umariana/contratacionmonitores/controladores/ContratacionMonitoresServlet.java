/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umariana.contratacionmonitores.controladores;

import com.umariana.contratacionmonitores.excepciones.ExcepcionNoExiste;
import com.umariana.contratacionmonitores.excepciones.ExcepcionYaExiste;
import com.umariana.contratacionmonitores.logica.Administrador;
import com.umariana.contratacionmonitores.logica.Aspirante;
import com.umariana.contratacionmonitores.logica.ContratacionMonitores;
import com.umariana.contratacionmonitores.logica.Estudiante;
import com.umariana.contratacionmonitores.logica.Monitor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
    
        public static ContratacionMonitores cm = new ContratacionMonitores();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String identificacion = request.getParameter("txt_identificacion");
        System.out.println("identificacion: "+identificacion);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ContratacionMonitoresServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Contratacion de Monitores </h1>");
            out.println("<h2> Identificacion Estudiante :"+request.getParameter("txt_identificacion")+"</h2>");
            out.println("<h2> Identificacion Estudiante :"+request.getParameter("accion")+"</h2>");
            out.println("<h2> Identificacion Estudiante :"+request.getParameter("select_registrar")+"</h2>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
                    case "ingreso":
                        String identificacion = request.getParameter("txt_identificacion");
                        ingreso(identificacion);
                        break;
                    case "admin":
                        String usuario = request.getParameter("txt_usuario");
                        String password = request.getParameter("txt_contrasena");
                        ingresarAdmin(usuario,password);
                        break;
                    case "cerrar":
                        cerrarSesion();
                        break;
                        
                    case "reg_estu":
                        String registrar=request.getParameter("select_registrar");
                        if(registrar.equals("si")){
                            Estudiante estudiante = (Estudiante)sesionGlobal.getAttribute("estudiante");
                            registrarAspirante(estudiante.darIdentificacion());
                        }
                        else
                            cerrarSesion();
                        break;
                    case "eliminar":
                        identificacion=request.getParameter("identificacion");
                        Estudiante estudianteSistema = cm.buscarEstudianteSistema(identificacion);
                        sesionGlobal.setAttribute("eliminar", estudianteSistema);
                        //eliminarEstudiante(identificacion);
                        break;
                    case "confirmarEliminar":
                        String elimi=request.getParameter("select_eliminar");
                        if(elimi.equals("si")){
                            Estudiante eliminar = (Estudiante) sesionGlobal.getAttribute("eliminar");
                            eliminarEstudiante(eliminar.darIdentificacion());
                            sesionGlobal.removeAttribute("eliminar");
                        }
                        else
                            sesionGlobal.removeAttribute("eliminar");
                        break;
                    case "eliminarDependencia":
                        
                        break;
                    case "confirmarEliminarD":
                        
                        break;
                    case"agregarDependencia":
                        String cod = request.getParameter("txt_codigo");
                        String nom = request.getParameter("txt_nombre");
                        String des = request.getParameter("txt_descripcion");
                        String jor = request.getParameter("slc_jornadaD");
                        int cup = Integer.parseInt(request.getParameter("txt_cupos"));                   
                        cm.agregarDependencia(cod, nom, des, jor, cup);                       
                        break;
                        
                }
                response.sendRedirect("index.jsp");
            } catch (ExcepcionNoExiste ex) {
                sesionGlobal.setAttribute("mensaje", ex.getMessage());      
                response.sendRedirect("index.jsp");
            } catch (Exception ex) {
                sesionGlobal.setAttribute("mensaje", ex.getMessage()); 
                response.sendRedirect("index.jsp");
            }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    //@Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    public void ingreso(String identificacion){
        try {
            cerrarSesion();
            int n = cm.ingreso(identificacion);
            if(n==1){
                Aspirante aspirante = cm.buscarAspirante(identificacion);
                System.out.println("------------------------------------");
                System.out.println("El ingreso del ASPIRANTE :");
                System.out.println(aspirante.toString());
                System.out.println("------------------------------------");
                usuarioActual="aspirante";
                //sesionGlobal.removeAttribute("mensaje");
                sesionGlobal.setAttribute("aspirante", aspirante);
            } 
            else if(n==2){
                Monitor monitor = cm.buscarMonitor(identificacion);
                usuarioActual="monitor";
                //sesionGlobal.removeAttribute("mensaje");
                sesionGlobal.setAttribute("monitor", monitor);
            }
            else if(n==3){
                Estudiante estudiante = cm.buscarEstudianteUniversidad(identificacion);
                usuarioActual="estudiante";
                sesionGlobal.setAttribute("estudiante", estudiante);  
                sesionGlobal.removeAttribute("mensaje");
            }
            else if(n==4){
                sesionGlobal.setAttribute("mensaje", "La identificación : +"+identificacion+" no corresponde a ningun estudiante de la Universidad");
            }
            
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException | ExcepcionNoExiste ex) {
            sesionGlobal.setAttribute("mensaje", ex.getMessage());         
        }
    }
    
    
    public void registrarAspirante(String identificacion){
            try {
                cerrarSesion();
                Aspirante aspirante =cm.registrarAspirante2(identificacion);
                sesionGlobal.setAttribute("mensaje","Se registro Correctamente el estudiante con identificacion : "+aspirante.darIdentificacion());
                sesionGlobal.setAttribute("aspirante", aspirante);
                usuarioActual="aspirante";
                
            } catch (ExcepcionYaExiste | ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
                System.out.println(ex.getMessage());
            }
    }
    public void cerrarSesion(){
        sesionGlobal.removeAttribute(usuarioActual);
        sesionGlobal.removeAttribute("eliminar");
        sesionGlobal.removeAttribute("mensaje");
        
    }
    
    public HttpSession darSession(){
        return sesionGlobal;
    }

    private void ingresarAdmin(String usuario, String password) {
            try {
                Administrador admin= cm.ingresoAdmin(usuario,password);
                cerrarSesion();
                usuarioActual="admin";    
                sesionGlobal.setAttribute("admin", admin);
            } catch (ExcepcionNoExiste ex) {
                sesionGlobal.setAttribute("mensaje", ex.getMessage());
            }
    }

    private void eliminarEstudiante(String identificacion) throws Exception {
        cm.eliminarEstudianteSistema(identificacion);
    }
}
