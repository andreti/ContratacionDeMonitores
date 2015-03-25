<%-- 
    Document   : aspirante
    Created on : 29/09/2014, 09:07:00 AM
    Author     : CocoSoft
--%>

<%@page import="com.umariana.contratacionmonitores.controladores.GestionAspirantes"%>
<%@page import="com.umariana.contratacionmonitores.logica.Postulacion"%>
<%@page import="com.umariana.contratacionmonitores.controladores.ContratacionMonitoresServlet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.umariana.contratacionmonitores.logica.Monitor"%>
<%@page import="com.umariana.contratacionmonitores.logica.Administrador"%>
<%@page import="com.umariana.contratacionmonitores.logica.Estudiante"%>
<%@page import="com.umariana.contratacionmonitores.logica.Aspirante"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  
    session.setAttribute("ubicacionPage", "aspirante.jsp");
    
    Administrador admin = (Administrador)session.getAttribute("admin");
    String mensaje = (String)session.getAttribute("mensaje");

    session.removeAttribute("eliminarMonitor");     
    session.removeAttribute("eliminarDependencia");
    session.removeAttribute("eliminarString");
%>
<!DOCTYPE html5>
<html>
    <head>
        <jsp:include page="head.jsp" />
        <title>Aspirantes</title>
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
               <%ArrayList<Aspirante> aspirantes = ContratacionMonitoresServlet.darComunicacionLogica().darAspirantes(); %>
                <table>
                    <th  colspan="10"><h3>Lista de Aspirantes</h3></th>  
                    <tr id="tr_rows">     
                        <td>Identificación</td>
                        <td>Nombres</td>
                        <td>Apellidos</td>
                        <td>Semestre</td>
                        <td>Promedio Acum.</td>
                        <td>Postulaciones</td>
                        <td colspan="4"></td>
                    </tr>
                    <%
               if(!aspirantes.isEmpty()){
                    int color=0;
                    for(Aspirante asp: aspirantes){%>
                    <tr class="tr_list" <%if(color==0){%> style="background: white;" <%}else{%> style="background: #aad4ff;"<%}%>>
                        <td><%=asp.darIdentificacion() %></td>
                        <td><%=asp.darPrimerNombre()%><%if(asp.darSegundoNombre()!=null){%> <%=asp.darSegundoNombre() %><%}%></td>
                        <td><%=asp.darPrimerApellido() %><%if(asp.darSegundoApellido()!=null){%> <%=asp.darSegundoApellido() %><%}%></td>                 
                        <td><%=asp.darSemestreActual() %></td>
                        <td><%=asp.darPromedioAcumulado()%></td>  
                        <%ArrayList<Postulacion> pst=GestionAspirantes.buscarPostulacionesAspirante(asp.darIdentificacion()); 
                        if(!pst.isEmpty()){%>
                        <td><a href="#"><%=pst.size() %></a></td>  
                        <%}else{%>
                        <td>N/A</td>
                        <%}%>
                        <td style="width: 40px; height: 40px;"><a class="icono_small" href="#" onclick="javascript:venRevPru('<%=asp.darIdentificacion()%>');"><img src="img/icon_pruebas.png" title="Revizar pruebas"/></a></td>
                        <td style="width: 40px; height: 40px;"><a class="icono_small"  href="#" onclick="javascript:venDelAsp('<%=asp.darIdentificacion()%>');"><img src="img/icon_delete.png" title="Eliminar"/></a></td>
                        <td style="width: 40px; height: 40px;"><a class="icono_small post"  href="#" onclick="javascript:venActAsp('<%=asp.darIdentificacion()%>');"><img src="img/icon_update.png" title="Actualizar"/></a></td>
                        <td style="width: 40px; height: 40px;"><a class="icono_small"  href="#" onclick="javascript:venSelAsp('<%=asp.darIdentificacion()%>');"><img src="img/icon_select.png" title="Escoger"/></a></td> 
                    </tr>
                   <% if(color==0){color=1;}else{color=0;}
                    }%>
                    <tr id="tr_final" ><td colspan="6"></td><td colspan="4">N° Aspirantes <%=aspirantes.size() %></td></tr>
               <%}
               else{%>
               <tr><td colspan="10" style="text-align: center;">No hay registros de aspirantes en el sistema</td></tr>
                <%}%>
               </table> 
            </div>
         </section>
         <jsp:include page="footer.jsp"/>      
         <%} else{ response.sendRedirect("admin.jsp"); }%>
        
    </body>
</html>
