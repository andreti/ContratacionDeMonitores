<%--
    Document   : index
    Created on : 11/09/2014, 09:20:24 PM
    Author     : Cocosoft
--%>

<%@page import="com.umariana.contratacionmonitores.logica.Dependencia"%>
<%@page import="com.umariana.contratacionmonitores.logica.Postulacion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.umariana.contratacionmonitores.controladores.ContratacionMonitoresServlet"%>
<%@page import="com.umariana.contratacionmonitores.logica.Administrador"%>
<%@page import="com.umariana.contratacionmonitores.logica.Estudiante"%>
<%@page import="com.umariana.contratacionmonitores.logica.Monitor"%>
<%@page import="com.umariana.contratacionmonitores.logica.Aspirante"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%  Aspirante aspirante =(Aspirante) session.getAttribute("aspirante");
    Monitor monitor=monitor =(Monitor) session.getAttribute("monitor");
    Estudiante estudiante = (Estudiante)session.getAttribute("estudiante");
    Administrador admin = (Administrador)session.getAttribute("admin");
    String mensaje = (String)session.getAttribute("mensaje");
%>

<!DOCTYPE html5>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/estilos.css" type="text/css" media="all">
        <title>Contratacion Monitores</title>
    </head>
    <body>
        <header>
            <div id="cabecera">
                <div>
                    <a href="index.jsp"><img src="img/logo.png"></a>
                </div>
                <div>
                    <h1>Contratacion de Monitores</h1>
                </div>
            </div>
            <div id="barraNavegacion">
                <nav id="barraNavPrincipal">
                    <ul>
                        <li><a href="index.jsp" style="background:#56a2ff; height: 32px;">Inicio</a></li>
                        <li><a href="#">Pruebas</a></li> 
                        <li><a href="dependencia.jsp">Dependencias</a></li>
                        <li><a href="estudiante.jsp">Estudiantes</a></li>
                        <li><a href="admin.jsp">Entrar</a></li>
                    </ul>
                </nav>
            </div>     
        </header>      
        <section>
            <div id="contenedor">
                <% if(aspirante==null && monitor==null && estudiante==null && admin==null){%>
            <%if(mensaje!=null){%>
                 <h4 style="color: black;"><%=mensaje%></h4>
            <%}%>
           
                <div>
                    <table border="1">
                        <tr>
                            <td>
                                <label>Ingreso Para Estudiantes</label>
                                <form action="ContratacionMonitoresServlet" method="POST">
                                    <label>Identificacion</label>
                                    <input type="text" id="txt_identificacion" name="txt_identificacion">
                                    <input type="submit" id="btn_enviar" value="Enviar">
                                    <input type="hidden" id="accion" name="accion" value="ingreso">
                                </form>
                            </td>
                        </tr>
                    </table>
                </div>      
            <br>
            <br>            
                <div>
                    <table border="1">
                        <tr>
                            <td>
                                <label>Ingreso Para Administrador</label>
                                <form action="ContratacionMonitoresServlet" method="POST">
                                    <label>Usuario</label>
                                    <input type="text" id="txt_usuario" name="txt_usuario">
                                    <label>Contraseña</label>
                                    <input type="password" id="txt_contrasena" name="txt_contrasena">
                                    <input type="submit" id="btn_enviar" value="Entrar">
                                    <input type="hidden" id="accion" name="accion" value="admin">                       
                                </form>
                            </td>
                        </tr>
                    </table>
                </div>   
            
        <%}       
        if(aspirante!= null){
            response.sendRedirect("estudiante.jsp");
        }
        else if(monitor!= null){
             response.sendRedirect("estudiante.jsp");
        }
        else if(estudiante!= null){
             response.sendRedirect("estudiante.jsp");
        }
        else if(admin!=null) {
            response.sendRedirect("admin.jsp");
        } %>
            </div>
        </section>
        <footer>
            <a href="#">CocoSoft</a>
        </footer>
    </body>
</html>
