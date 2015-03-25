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
<%  
    session.setAttribute("ubicacionPage", "dependenciaView.jsp");
    
    Aspirante aspirante =(Aspirante) session.getAttribute("aspirante");
    Monitor monitor=monitor =(Monitor) session.getAttribute("monitor");
    Estudiante estudiante = (Estudiante)session.getAttribute("estudiante");
    Administrador admin = (Administrador)session.getAttribute("admin");
    String mensaje = (String)session.getAttribute("mensaje");
%>

<!DOCTYPE html5>
<html>
    <head>
        <jsp:include page="head.jsp" />
        <title>Dependencias</title>
    </head>
    <body>
       <jsp:include page="header.jsp" />
        <section>
            <div id="contenedor">      
            </div>
        </section>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
