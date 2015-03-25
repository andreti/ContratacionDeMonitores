package com.umariana.contratacionmonitores.controladores;

import static com.umariana.contratacionmonitores.controladores.ContratacionMonitoresServlet.instance;
import com.umariana.contratacionmonitores.excepciones.ConnectionException;
import com.umariana.contratacionmonitores.excepciones.ExcepcionYaExiste;
import com.umariana.contratacionmonitores.logica.Dependencia;
import com.umariana.contratacionmonitores.logica.dependencia.Horario;
import com.umariana.contratacionmonitores.logica.dependencia.Jornada;
import java.io.IOException;
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
@WebServlet(name = "GestionDependencias", urlPatterns = {"/GestionDependencias"})
public class GestionDependencias extends HttpServlet {

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
           switch (accion){
               case"regDep":
                   String nom = request.getParameter("txt_nombre");
                   String des = request.getParameter("txt_descripcion");
                   Dependencia dependencia = new Dependencia();                
                   dependencia.cambiarNombre(nom.toUpperCase().trim());
                   dependencia.cambiarDescripcion(des.trim());
                   if(instance.existeDependencia(nom.toUpperCase().trim())){
                       sesion.setAttribute("depExiste", dependencia);
                       sesion.setAttribute("mensaje", "La dependencia ya existe.");
                       sesion.removeAttribute("regDep2");
                       sesion.removeAttribute("regDep1");
                       
                   }
                   else{
                       sesion.removeAttribute("depExiste");
                       sesion.removeAttribute("regDep1");
                       sesion.setAttribute("regDep2", dependencia);
                   }
                   response.sendRedirect("dependencia.jsp");
                   break;
               case"volverRegDep":
                   Dependencia regDep1 =(Dependencia)sesion.getAttribute("regDep2");
                   sesion.removeAttribute("regDep2");
                   sesion.setAttribute("regDep1", regDep1);
                   response.sendRedirect("dependencia.jsp");
                   break;
               case"agregarHorario":
                   String atributo="";
                   Dependencia actual =(Dependencia)sesion.getAttribute("regDep2");
                   if(actual!=null)
                        atributo="regDep2";
                   else{
                       actual =(Dependencia)sesion.getAttribute("actualizarDep");
                       atributo="actualizarDep";
                   }
                   
                   String jornadaString=request.getParameter("slc_jornada");
                   if(jornadaString.equals("manana")){
                       jornadaString="Mañana";
                   }
                   int horaInicio=Integer.parseInt(request.getParameter("txtHoraInicio"));
                   int horaFin=Integer.parseInt(request.getParameter("txtHoraFin"));
                   if(horaFin<=horaInicio)
                       sesion.setAttribute("mensaje", "Verifica la hora de inicio y de fin.");
                   else{
                        int cupos=Integer.parseInt(request.getParameter("txtCupos"));
                        Jornada jornada= new Jornada(jornadaString, 0, 0);
                        Horario horario = new Horario(horaInicio, horaFin,0,cupos,cupos, 0);
                        jornada.agregarHorario(horario);
                        actual.agregarJornada(jornada);
                        sesion.setAttribute(atributo, actual);
                   }
                   response.sendRedirect("dependencia.jsp");
                   break;
               case"finalizarRegDep":
                   Dependencia registrar =(Dependencia)sesion.getAttribute("regDep2");
                   instance.agregarDependencia(registrar);
                   sesion.removeAttribute("depExiste");
                   sesion.removeAttribute("regDep1");
                   sesion.removeAttribute("regDep2");
                   response.sendRedirect("dependencia.jsp");
                   break;
//               case "eliminarDepPaso1":
//                   int idDependencia=Integer.parseInt(request.getParameter("idDependencia"));
//                   Dependencia delete = instance.buscarDependencia(idDependencia);
//                   sesion.setAttribute("eliminarDep", delete);
//                   sesion.removeAttribute("depExiste");
//                   sesion.removeAttribute("regDep1");
//                   sesion.removeAttribute("regDep2");
//                   response.sendRedirect("dependencia.jsp");
//                   break;                 
               case"aceptarDelDep":
                   int idDependencia=Integer.parseInt(request.getParameter("idDependencia"));
                   //delete=(Dependencia)sesion.getAttribute("eliminarDep");
                   instance.eliminarDependencia(idDependencia);
                   sesion.removeAttribute("eliminarDep");   
                   sesion.removeAttribute("depExiste");
                   sesion.removeAttribute("regDep1");
                   sesion.removeAttribute("regDep2");
                   response.sendRedirect("dependencia.jsp");
                   break;
               case"cancelar":
                   sesion.removeAttribute("eliminarDep");   
                   sesion.removeAttribute("actualizarDep");   
                   sesion.removeAttribute("depExiste");
                   sesion.removeAttribute("regDep1");
                   sesion.removeAttribute("regDep2");
                   sesion.removeAttribute("actualizarDepPaso");
                   response.sendRedirect("dependencia.jsp");
                   break;    
               case"actualizarD":
                   idDependencia=Integer.parseInt(request.getParameter("idDependencia"));
                   Dependencia update = instance.buscarDependencia(idDependencia);
                   sesion.setAttribute("actualizarDep", update);
                   sesion.setAttribute("actualizarDepPaso", "paso1");
                   sesion.removeAttribute("eliminarDep");   
                   sesion.removeAttribute("depExiste");
                   sesion.removeAttribute("regDep1");
                   sesion.removeAttribute("regDep2");
                   response.sendRedirect("dependencia.jsp");
                   break;
               case"volverPaso1":
                   sesion.setAttribute("actualizarDepPaso", "paso1");
                   response.sendRedirect("dependencia.jsp");
                   break;
               case"actualizarPaso1":
                   
                   update =(Dependencia)sesion.getAttribute("actualizarDep");
                   nom = request.getParameter("txt_nombre").toUpperCase().trim();
                   des= request.getParameter("txt_descripcion");
                   if(instance.existeDependencia(nom) && !update.darNombre().equalsIgnoreCase(nom)){
                        sesion.setAttribute("mensaje", "El nombre de la dependencia ya existe.");
                   }else{
                       update.cambiarNombre(nom);   
                       sesion.setAttribute("actualizarDepPaso", "paso2");
                   }
                   update.cambiarDescripcion(des);
                   sesion.setAttribute("actualizarDep", update);
                   response.sendRedirect("dependencia.jsp");
                   break;
               case"actualizarDPaso2":
                   sesion.setAttribute("actualizarDepPaso", "paso3");
                   response.sendRedirect("dependencia.jsp");
                   break;
               case"actualizarDPaso3":
                   sesion.setAttribute("actualizarDepPaso", "paso2");
                   response.sendRedirect("dependencia.jsp");
                   break;    
               case"eliminarHorarioDep":
                   update =(Dependencia)sesion.getAttribute("actualizarDep");
                   String idHorario=request.getParameter("idHorario");
                   update.eliminarHorario(idHorario);
                   sesion.setAttribute("actualizarDep", update);
                   sesion.setAttribute("actualizarDepPaso", "paso2");
                   response.sendRedirect("dependencia.jsp");
                   break;
               case"finalizarActDep":
                   update =(Dependencia)sesion.getAttribute("actualizarDep");
                   instance.modificarDependencia(update);                  
                   sesion.removeAttribute("actualizarDep");
                   sesion.removeAttribute("actualizarDepPaso");
                   response.sendRedirect("dependencia.jsp");
                   break;                  
           }
           ContratacionMonitoresServlet.setearSesion(sesion);
           //response.sendRedirect("dependencia.jsp");
       } catch (SQLException | ExcepcionYaExiste | ConnectionException ex) {
           if(ex.getMessage().startsWith("ERROR: update o delete"))
               sesion.setAttribute("mensajeSQL","Error: Hay monitores registrados en la dependencia!!");
           else{
               sesion.setAttribute("mensaje","Error: "+ex.getMessage());
           }
           response.sendRedirect("dependencia.jsp");
       }
    }
    
    /**
     * Retorna la lista de horarios de una jornada en especifico
     * Pasando como parametro el id de la jornada
     * @param idJornada
     * @return ArrayList<Horario> horarios
     */
    public static ArrayList<Horario> darHorariosJornada(String idJornada){
       try {
           return instance.darHorariosJornada(Integer.parseInt(idJornada));
       } catch (SQLException ex) {
           Logger.getLogger(GestionDependencias.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println("Error: "+GestionDependencias.class.getName()+" - "+ex.getMessage());
       }
       return new ArrayList<>();
    }
    public static Dependencia buscarDependencia(String idDependencia){
       return instance.buscarDependencia(Integer.parseInt(idDependencia));     
    }
    public static Horario buscarHorario(String idHorario){
       try {
           return instance.buscarHorario(Integer.parseInt(idHorario));
       } catch (SQLException ex) {
           Logger.getLogger(GestionDependencias.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
    }
    public static Dependencia buscarDependenciaPorHorario(String idHorario){
       try {
           return instance.setearDependencia(Integer.parseInt(idHorario));
       } catch (SQLException ex) {
           Logger.getLogger(GestionDependencias.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
    }

} 
