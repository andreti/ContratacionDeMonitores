<%-- 
    Document   : header
    Created on : 25/10/2014, 07:47:05 PM
    Author     : Andres
--%>
<%@page import="com.umariana.contratacionmonitores.logica.Administrador"%>
<%@page import="com.umariana.contratacionmonitores.logica.Estudiante"%>
<%@page import="com.umariana.contratacionmonitores.logica.Monitor"%>
<%@page import="com.umariana.contratacionmonitores.logica.Aspirante"%>
<%  //Aspirante aspirante =(Aspirante) session.getAttribute("aspirante");
    //Monitor monitor=monitor =(Monitor) session.getAttribute("monitor");
    //Estudiante estudiante = (Estudiante)session.getAttribute("estudiante");
    Administrador admin = (Administrador)session.getAttribute("admin");
    //String mensaje = (String)session.getAttribute("mensaje");
    String ubicacion = (String)session.getAttribute("ubicacionPage");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/estilos.css" type="text/css" media="all">
<link rel="shortcut icon" href="img/favicon.png" />
<header>
    <div id="cabecera" >
        <div>
            <a href="index.jsp"><img src="img/logo.png"></a>
        </div>
        <div>
            <h1 style="font-family: sans-serif;">Contratacion de Monitores</h1>
        </div>
        <div id="div_redes_sociales" style="position: absolute; width: 500px; height: 40px; display: inline-block; margin-left: 50px; margin-top: 105px;"> 
            <a href="#"> <img style="width: 30px; height: 30px; margin: 0px;" src="img/icon_facebook.png"></a>
            <a href="#"> <img style="width: 30px; height: 30px;  margin: 0px;" src="img/icon_gmail.png"></a>
            <a href="#"> <img style="width: 30px; height: 30px;  margin: 0px;" src="img/icon_googlemas.png"></a>
            <a href="#"> <img style="width: 30px; height: 30px;  margin: 0px;" src="img/icon_twitter.png"></a>
            <a href="#"> <img style="width: 30px; height: 30px;  margin: 0px;" src="img/icon_youtube.png"></a>
        </div>
    </div>
    <div id="barraNavegacion">
        <nav id="barraNavPrincipal">
            <ul>
                <li><a href="index.jsp" <%if(ubicacion.equals("index.jsp")){%> style="background:#56a2ff; height: 32px;"<%}%>>Inicio</a></li>
                <li><a href="pruebas.jsp" <%if(ubicacion.equals("pruebas.jsp")){%> style="background:#56a2ff; height: 32px;"<%}%>>Pruebas</a></li> 
                <%if(admin==null){%>
                <li><a href="dependenciaView.jsp"<%if(ubicacion.equals("dependenciaView.jsp")){%> style="background:#56a2ff; height: 32px;"<%}%>>Dependencias</a></li>
                <li><a href="estudiante.jsp" <%if(ubicacion.equals("estudiante.jsp")){%> style="background:#56a2ff; height: 32px;"<%}%>>Estudiantes</a></li>
                <%}%>
                <li><a href="admin.jsp" <%if(ubicacion.equals("admin.jsp")){%> style="background:#56a2ff; height: 32px;"<%}%>><%if(admin==null) {%>Entrar<%} else{%>Admin<%}%></a></li>  
            </ul>  
        </nav>         
    </div>   
     <%if(admin!=null){ %>             
    <div id="barraSecundaria">
        <nav id="barraNavSecundaria">
            <ul>
                <li><a href="dependencia.jsp" <%if(ubicacion.equals("dependencia.jsp")){%> style="background:#56a2ff; height: 32px;"<%}%>>Dependencias</a></li>
                <li><a href="aspirante.jsp" <%if(ubicacion.equals("aspirante.jsp")){%> style="background:#56a2ff; height: 32px;"<%}%>>Aspirantes</a></li> 
                <li><a href="monitor.jsp" <%if(ubicacion.equals("monitor.jsp")){%> style="background:#56a2ff; height: 32px;"<%}%>>Monitores</a></li>                       
            </ul>
        </nav>
   </div>      
     <%}%>     
</header>   
