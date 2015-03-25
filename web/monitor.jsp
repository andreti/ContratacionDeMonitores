<%--
    Document   : monitor
    Created on : 29/09/2014, 09:07:12 AM
    Author     : CocoSoft
--%>
<%@page import="com.umariana.contratacionmonitores.controladores.ContratacionMonitoresServlet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.umariana.contratacionmonitores.logica.Monitor"%>
<%@page import="com.umariana.contratacionmonitores.logica.Administrador"%>
<%@page import="com.umariana.contratacionmonitores.logica.Estudiante"%>
<%@page import="com.umariana.contratacionmonitores.logica.Aspirante"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  
    session.setAttribute("ubicacionPage", "monitor.jsp");
    
    //Aspirante aspirante =(Aspirante) session.getAttribute("aspirante");
    //Monitor monitor=monitor =(Monitor) session.getAttribute("monitor");
    //Estudiante estudiante = (Estudiante)session.getAttribute("estudiante");
    Administrador admin = (Administrador)session.getAttribute("admin");
    String mensaje = (String)session.getAttribute("mensaje");
    //Estudiante eliminar = (Estudiante)session.getAttribute("eliminar");
    //Monitor eliminarMonitor = (Monitor)session.getAttribute("eliminarMonitor");
    session.removeAttribute("eliminarDependencia");     
    session.removeAttribute("eliminarAspirante");
    session.removeAttribute("eliminarString");
%>
<!DOCTYPE html5>
<html>
    <head>
        <jsp:include page="head.jsp" />
        <title>Monitores</title>
    </head>
    <body>
        <jsp:include page="header.jsp" /> 
        <jsp:include page="divModal.jsp" /> 
        <%if(admin!=null) {%> 
         <section>
            <div id="contenedor">
                
                <%if(mensaje!=null){%>
                     <h4 style="color: black;"><%=mensaje%></h4>
                    <%session.removeAttribute("mensaje");
                }%> 
               <%ArrayList<Monitor> monitores = ContratacionMonitoresServlet.darComunicacionLogica().darMonitores(); %>
                <table>
                    <th  colspan="9"><h3>Lista de Monitores</h3></th>  
                    <tr id="tr_rows">     
                        <td>Identificación</td>
                        <td>Nombres</td>
                        <td>Apellidos</td>
                        <td>Semestre</td>
                        <td>Dependencia</td>
                        <td colspan="4"></td>
                    </tr>
                    <%
               if(!monitores.isEmpty()){
                    int color=0;
                    for(Monitor mon: monitores){%>
                    <tr class="tr_list" <%if(color==0){%> style="background: white;" <%}else{%> style="background: #aad4ff;" <%}%>>
                        <td><%=mon.darIdentificacion() %></td>
                        <td><%=mon.darPrimerNombre()%><%if(mon.darSegundoNombre()!=null){%> <%=mon.darSegundoNombre() %><%}%></td>
                        <td><%=mon.darPrimerApellido() %><%if(mon.darSegundoApellido()!=null){%> <%=mon.darSegundoApellido() %><%}%></td>                 
                        <td><%=mon.darSemestreActual() %></td> 
                        <td style="width: 40px;"><a class="a_enlace" href="#" onclick="javascript:venVerDepMon('<%=mon.darIdentificacion()%>');"><%=mon.darDependencia().darNombre() %></a></td>
                        <td style="width: 40px;" ><a class="icono_small" href="#" onclick="javascript:venVerResMon('<%=mon.darIdentificacion()%>');"><img src="img/icon_pruebas.png" title="Revizar pruebas"/></a></td>
                        <td style="width: 40px;"><a class="icono_small"  href="#" onclick="javascript:venDelMon('<%=mon.darIdentificacion()%>');"><img src="img/icon_delete.png" title="Eliminar"/></a></td>
                        <td style="width: 40px;"><a class="icono_small post"  href="#" onclick="javascript:venActAsp('<%=mon.darIdentificacion()%>');"><img src="img/icon_update.png" title="Actualizar"/></a></td>
                        <td style="width: 40px;"><a class="icono_small post"  href="#" onclick="javascript:venSelAsp('<%=mon.darIdentificacion()%>');"><img src="img/icon_config.png" title="Configuracion"/></a></td> 
                    </tr>
                   <% if(color==0){color=1;}else{color=0;}
                    }%>
                    <tr id="tr_final"><td colspan="5"></td><td colspan="4">N° Monitores <%=monitores.size() %></td></tr>
               <%}
               else{%>
               <tr><td colspan="10" style="text-align: center;">No hay registros de monitores en el sistema</td></tr>
                <%}%>
               </table> 
            </div>
         </section>
         <jsp:include page="footer.jsp"/>      
         <%} else{ response.sendRedirect("admin.jsp"); }%>
    </body>
</html>
