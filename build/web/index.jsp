<%--
    Document   : index
    Created on : 11/09/2014, 09:20:24 PM
    Author     : Cocosoft
--%>
<%
Object instance=ContratacionMonitoresServlet.darComunicacionLogica();
ContratacionMonitoresServlet.conectarBD();

if(instance==null){
    ContratacionMonitoresServlet cm = new ContratacionMonitoresServlet();
}
%>


<%@page import="com.umariana.contratacionmonitores.logica.Dependencia"%>
<%@page import="com.umariana.contratacionmonitores.logica.Postulacion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.umariana.contratacionmonitores.controladores.ContratacionMonitoresServlet"%>
<%@page import="com.umariana.contratacionmonitores.logica.Administrador"%>
<%@page import="com.umariana.contratacionmonitores.logica.Estudiante"%>
<%@page import="com.umariana.contratacionmonitores.logica.Monitor"%>
<%@page import="com.umariana.contratacionmonitores.logica.Aspirante"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%  
    session.setAttribute("ubicacionPage", "index.jsp");
    //Aspirante aspirante =(Aspirante) session.getAttribute("aspirante");
    //Monitor monitor=monitor =(Monitor) session.getAttribute("monitor");
    //Estudiante estudiante = (Estudiante)session.getAttribute("estudiante");
    //Administrador admin = (Administrador)session.getAttribute("admin");
    //String mensaje = (String)session.getAttribute("mensaje");
%>

<!DOCTYPE html5>
<html>
    <head>
        <jsp:include page="head.jsp" />
        <title>Contratacion Monitores</title>
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
