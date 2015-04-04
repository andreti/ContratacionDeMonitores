package com.umariana.contratacionmonitores.controladores;

import com.umariana.contratacionmonitores.excepciones.ExcepcionNoExiste;
import com.umariana.contratacionmonitores.logica.Administrador;
import com.umariana.contratacionmonitores.util.TratamientoPassword;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "GestionAdministrador", urlPatterns = {"/GestionAdministrador"})
public class GestionAdministrador extends HttpServlet {

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
        HttpSession sesion = ContratacionMonitoresServlet.darSession();
        sesion = request.getSession(true);
        String accion = request.getParameter("accion");
        try {
            switch (accion) {
                case "entrar":
                    sesion.removeAttribute("mensaje");
                    String usuario = request.getParameter("txt_usuario");
                    String password = request.getParameter("txt_contrasena");
                    password = TratamientoPassword.encriptar(password);
                    Administrador admin = ContratacionMonitoresServlet.darComunicacionLogica().ingresoAdmin(usuario, password);
                    if (admin != null) {
                        sesion.setAttribute("admin", admin);
                    } else {
                        sesion.setAttribute("mensaje", "Usuario y/o contraseña incorrecta!!");
                    }
                    break;
                case "cerrar":
                    sesion.removeAttribute("admin");
                    break;
                case "actualizar":
                    break;
                case "registrar":
                    sesion.removeAttribute("mensaje");
                    String nombre = request.getParameter("txt_nombre_completo");
                    usuario = request.getParameter("txt_nick");
                    password = request.getParameter("txt_pass1");
                    password = TratamientoPassword.encriptar(password);
                    admin = new Administrador(usuario, nombre, password);
                    ContratacionMonitoresServlet.darComunicacionLogica().registrarAdministrador(admin);
                    sesion.setAttribute("mensaje", "Registro exitoso");
                    break;
                case "validarNick":
                    System.out.println(request.getParameter("nick"));
                    //validarNickAdmin(request, response);
                    break;

            }
            ContratacionMonitoresServlet.setearSesion(sesion);
            if (!accion.equals("validarNick")) {
                response.sendRedirect("admin.jsp");
            } else {
                response.setContentType("text/plain;charset=UTF-8");
                PrintWriter out = response.getWriter();
                boolean existe = ContratacionMonitoresServlet.darComunicacionLogica().validarNickAdministrador(request.getParameter("nick"));
                int resultado = existe ? 1 : 0;
                out.println(resultado);
                out.close();
            }
        } catch (ExcepcionNoExiste | SQLException ex) {
            sesion.setAttribute("mensaje", ex.getMessage());
        }

    }

    protected void validarNickAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        boolean existe = ContratacionMonitoresServlet.darComunicacionLogica().validarNickAdministrador(request.getParameter("nick"));
        int resultado = existe ? 1 : 0;
        out.println(resultado);
        out.close();
    }
}
