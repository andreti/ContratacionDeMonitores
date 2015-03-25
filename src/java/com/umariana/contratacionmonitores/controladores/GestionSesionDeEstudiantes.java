package com.umariana.contratacionmonitores.controladores;

import com.umariana.contratacionmonitores.excepciones.ExcepcionNoExiste;
import com.umariana.contratacionmonitores.logica.Aspirante;
import com.umariana.contratacionmonitores.logica.Estudiante;
import com.umariana.contratacionmonitores.logica.Monitor;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andres
 */
@WebServlet(name = "GestionSesionDeEstudiantes", urlPatterns = {"/GestionSesionDeEstudiantes"})
public class GestionSesionDeEstudiantes extends HttpServlet {

    
    private static HttpSession sesion;
    
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sesion=ContratacionMonitoresServlet.darSession();
        sesion= request.getSession(true);
        String accion = request.getParameter("accion");
        try 
        {
            switch(accion){
                case "ingreso":
                    limpiarAtributosSesion();
                    String identificacion = request.getParameter("txt_identificacion");
                    buscarIngreso(identificacion);   
                    break;                   
                case "cerrar":
                    limpiarAtributosSesion();
                    break; 
            }
        } 
        catch (ExcepcionNoExiste | SQLException ex) 
        {
            sesion.setAttribute("mensaje", ex.getMessage());
        } 
        ContratacionMonitoresServlet.setearSesion(sesion);
        response.sendRedirect("estudiante.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sesion=ContratacionMonitoresServlet.darSession();
        sesion= request.getSession(true);
        String accion = request.getParameter("accion");
        try 
        {
            switch(accion){
                
                case "ingreso":
                    limpiarAtributosSesion();
                    String identificacion = request.getParameter("txt_identificacion");
                    buscarIngreso(identificacion);   
                    break;     
                    
                case "cerrar":
                    limpiarAtributosSesion();
                    break; 
                    
                
            }
        } 
        catch (ExcepcionNoExiste | SQLException ex) {
            sesion.setAttribute("mensaje", ex.getMessage());
        }
        ContratacionMonitoresServlet.setearSesion(sesion);
        response.sendRedirect("estudiante.jsp");
    }

    public void limpiarAtributosSesion()
    {
        //sesion.removeAttribute("admin");
        sesion.removeAttribute("eliminar");
        sesion.removeAttribute("mensaje");
        sesion.removeAttribute("aspirante");
        sesion.removeAttribute("monitor");
        sesion.removeAttribute("estudiante");
        sesion.removeAttribute("listaPostulacionesTemp");
    }
    
    public void buscarIngreso(String identificacion) throws ExcepcionNoExiste, SQLException{
        Aspirante aspirante = ContratacionMonitoresServlet.darComunicacionLogica().buscarAspirante(identificacion);
        boolean seguir= true;
        if(aspirante!=null){
           sesion.setAttribute("aspirante", aspirante);
           seguir=false;
        }   
        if(seguir){
            Monitor monitor = ContratacionMonitoresServlet.darComunicacionLogica().buscarMonitor(identificacion);
            if(monitor!=null){
                sesion.setAttribute("monitor", monitor);
                seguir=false;
            }      
        }
        if(seguir){
            Estudiante estudiante = ContratacionMonitoresServlet.darComunicacionLogica().buscarEstudianteUniversidad(identificacion);
            if(estudiante!=null){
                sesion.setAttribute("estudiante", estudiante);  
            }      
        } 
    }
}
