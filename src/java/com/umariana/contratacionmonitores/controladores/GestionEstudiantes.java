/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.umariana.contratacionmonitores.controladores;

import static com.umariana.contratacionmonitores.controladores.ContratacionMonitoresServlet.instance;
import com.umariana.contratacionmonitores.excepciones.ExcepcionYaExiste;
import com.umariana.contratacionmonitores.logica.Aspirante;
import com.umariana.contratacionmonitores.logica.Dependencia;
import com.umariana.contratacionmonitores.logica.Estudiante;
import com.umariana.contratacionmonitores.logica.Postulacion;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "GestionEstudiantes", urlPatterns = {"/GestionEstudiantes"})
public class GestionEstudiantes extends HttpServlet {
    
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        try {
            sesion=ContratacionMonitoresServlet.darSession();
            sesion= request.getSession(true);
            String accion = request.getParameter("accion");
            switch(accion){
                case "regEst":
                    Estudiante estudiante = (Estudiante)sesion.getAttribute("estudiante");
                    ArrayList<Postulacion> postulaciones=(ArrayList<Postulacion>)sesion.getAttribute("listaPostulacionesTemp");
                    limpiarAtributosSesion();
                    Aspirante aspirante =new Aspirante();                  
                    aspirante =instance.registrarAspirante2(estudiante.darIdentificacion(),postulaciones);
                    sesion.setAttribute("mensaje","Registro exitoso!!");
                    sesion.setAttribute("aspirante", aspirante);    
                    break;     
                case"agregarPostulacion":
                    postulaciones=(ArrayList<Postulacion>)sesion.getAttribute("listaPostulacionesTemp");
                    if(postulaciones==null)
                        postulaciones= new ArrayList<>();
                    
                    String idHorario=request.getParameter("slc_horario");   
                    Postulacion p = new Postulacion();
                    Dependencia d=instance.setearDependencia(Integer.parseInt(idHorario));
                    p.setDependencia(d);
                    postulaciones.add(p);
                    sesion.setAttribute("listaPostulacionesTemp", postulaciones);
                    break;
                case"eliminarPostulacion":
                    postulaciones=(ArrayList<Postulacion>)sesion.getAttribute("listaPostulacionesTemp");
                    if(postulaciones==null)
                        postulaciones= new ArrayList<>();
                    String idDependencia=request.getParameter("idDependencia");
                    int n=0;
                    for(int i=0;i<postulaciones.size() && n==0;i++){
                        Postulacion p2= postulaciones.get(i);
                        if(p2.getDependencia().darId()==Integer.parseInt(idDependencia)){
                            postulaciones.remove(p2);
                            n=1;
                        }
                    }
                    sesion.setAttribute("listaPostulacionesTemp", postulaciones);
                    break;
            }  
        } catch (ExcepcionYaExiste | ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
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


}
