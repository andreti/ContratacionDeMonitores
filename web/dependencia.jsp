<%-- 
    Document   : dependencia
    Created on : 28/09/2014, 10:50:23 PM
    Author     : Andres
--%>

<%@page import="com.umariana.contratacionmonitores.controladores.ContratacionMonitoresServlet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.umariana.contratacionmonitores.logica.Dependencia"%>
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
                        <li><a href="index.jsp">Inicio</a></li>
                        <li><a href="#">Pruebas</a></li> 
                        <li><a href="dependencia.jsp" style="background:#56a2ff; height: 32px;">Dependencias</a></li>
                        <li><a href="estudiante.jsp">Estudiantes</a></li>
                        <li><a href="admin.jsp">Entrar</a></li>
                    </ul>
                </nav>
            </div>     
        </header>      
        <section>
            <div id="contenedor">
                <table align="center" border="1">
                    <th  colspan="10"><h3>Lista de Dependencias</h3></th> 
                <tr style="background: black; color: white;">                   
                        <td>Codigo</td>
                        <td>Nombre</td>
                        <td>Descripcion</td>
                        <td>Horario</td>
                        <td>Cupos</td>
                    </tr>
                    <tr>
                        <form action="ContratacionMonitoresServlet" method="POST">
                            <td> <input type="text" id="txt_codigo" name="txt_codigo" placeholder="Codigo" required></td>
                            <td><input type="text" id="txt_nombre" name="txt_nombre" placeholder="Nombre" required></td>
                            <td><input type="text" id="txt_descripcion" name="txt_descripcion" placeholder="Descripcion" required></td>
                            <td><select id="slc_jornadaD" name="slc_jornadaD">
                                    <option value="mañana">Mañana</option>
                                    <option value="tarde">Tarde</option>
                                    <option value="diurno">Diurno</option>
                                </select></td>
                            <td><input type="number" id="txt_cupos" name="txt_cupos" placeholder="No de Cupos" required></td>
                            <td><input type="submit" id="btn_enviar" value="Agregar">
                                <input type="hidden" id="accion" name="accion" value="agregarDependencia">
                            </td>                   
                        </form>
                    </tr>
                    <%
                    ArrayList<Dependencia> dependencias = ContratacionMonitoresServlet.cm.darDependencias();
                    for(Dependencia dep: dependencias){%>
                       <tr>
                        <td><%=dep.darId()%></td>
                        <td><%=dep.darNombre()%></td>
                        <td><%=dep.darDescripcion()%></td>
                        <td><%=dep.darHorario()%></td>  
                        <td><%=dep.darCupos()%></td>
                        <form action="ContratacionMonitoresServlet" method="post">
                            <td><input type="submit" value="Eliminar"></td>
                            <input type="hidden" id="idDependencia" name="idDependencia" value="<%=dep.darId()%>">
                            <input type="hidden" id="accion" name="accion" value="eliminarDependencia">
                        </form>
                        <td><a href="#">Modificar</a></td>
                    </tr>
                   <%}%>
                </table><br>
            </div>
        </section>
    </body>
</html>
