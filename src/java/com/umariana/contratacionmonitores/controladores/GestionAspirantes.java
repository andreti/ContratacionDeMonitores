/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.umariana.contratacionmonitores.controladores;

import static com.umariana.contratacionmonitores.controladores.ContratacionMonitoresServlet.instance;
import com.umariana.contratacionmonitores.excepciones.ExcepcionNoExiste;
import com.umariana.contratacionmonitores.logica.Aspirante;
import com.umariana.contratacionmonitores.logica.Postulacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "GestionAspirantes", urlPatterns = {"/GestionAspirantes"})
public class GestionAspirantes extends HttpServlet {

    private static HttpSession sesion;
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GestionAspirantes</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GestionAspirantes at " + request.getContextPath() + "</h1>");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String irA="aspirante.jsp";
        try{
        sesion=ContratacionMonitoresServlet.darSession();
        sesion= request.getSession(true);
        String accion = request.getParameter("accion");
        switch (accion){
            case "eliminarAspirante":
                String identificacion=request.getParameter("identificacion");
                instance.eliminarAspirante(identificacion);              
                break;
            case"eliminarPostulacion":
                String idHorario=request.getParameter("idHorario");
                instance.eliminarPostulacionAspirante(Integer.parseInt(idHorario));
                irA="estudiante.jsp";
                break;
            case"seleccionarAspirante":
                idHorario=request.getParameter("cbx_postulacion");
                //idHorario=request.getParameter("idHorario");
                identificacion=request.getParameter("identificacion");
                instance.seleccionarAspirante(Integer.parseInt(idHorario),identificacion);
                irA="monitor.jsp";
                break;
        }  
        } catch (ExcepcionNoExiste | SQLException ex) {
            sesion.setAttribute("mensaje", ex.getMessage());
        }
        ContratacionMonitoresServlet.setearSesion(sesion);
        response.sendRedirect(irA);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    public static Aspirante buscarAspirante(String identificacion){
        try {
            return instance.buscarAspirante(identificacion);
        } catch (SQLException ex) {
            sesion.setAttribute("mensaje", ex.getMessage());
        }
        return null;
    }
    public static ArrayList<Postulacion> buscarPostulacionesAspirante(String identificacion){
        try {
            return instance.buscarPostulacionesAspirante(identificacion);
        } catch (SQLException ex) {
            sesion.setAttribute("mensaje", ex.getMessage());
        }
        return new ArrayList<>();
    }

}
