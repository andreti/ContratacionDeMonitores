<%-- 
    Document   : dependencias
    Created on : 29/09/2014, 09:21:18 AM
    Author     : SERVIDOR
--%>

<%@page import="com.umariana.contratacionmonitores.logica.dependencia.Jornada"%>
<%@page import="com.umariana.contratacionmonitores.logica.Dependencia"%>
<%@page import="com.umariana.contratacionmonitores.logica.Dependencia"%>
<%@page import="com.umariana.contratacionmonitores.controladores.ContratacionMonitoresServlet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.umariana.contratacionmonitores.logica.Monitor"%>
<%@page import="com.umariana.contratacionmonitores.logica.Administrador"%>
<%@page import="com.umariana.contratacionmonitores.logica.Estudiante"%>
<%@page import="com.umariana.contratacionmonitores.logica.Aspirante"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  
    
    session.setAttribute("ubicacionPage", "dependencia.jsp");
    
    Administrador admin = (Administrador)session.getAttribute("admin");
    String mensaje = (String)session.getAttribute("mensajeSQL");
    session.removeAttribute("eliminarMonitor");     
    session.removeAttribute("eliminarAspirante");     
    session.removeAttribute("eliminarString");

%>
<!DOCTYPE html5>
<html>
    <head>      
        <jsp:include page="head.jsp" />
        <title>Dependencias</title>
    </head>
    <body>
        <jsp:include page="header.jsp" /> 
        <jsp:include page="divModal.jsp" /> 
         <section>
            <%if(admin!=null){%> 
             <%if(mensaje!=null){%>
                <h4 style=" background: white;color: red; font-size: 13px;"><%=mensaje%></h4>
                        <%session.removeAttribute("mensajeSQL");
                }%>
            <div id="contenedor">           
                <table>
                    <th  colspan="7">Lista de Dependencias</th> 
                    <tr id="tr_rows" >                   
                        <td>Nombre</td>
                        <td>Descripcion</td>
                        <td style="padding-right: 15px;">C.T</td>
                        <td style="padding-right: 15px;">C.D</td>
                        <td colspan="3"><a class="icono" id="regDep" href="javascript:venRegDep();"><img src="img/icon_add.png" title="Nueva"/></a></td>
                    </tr>
                    <%
                    ArrayList<Dependencia> dependencias = ContratacionMonitoresServlet.darComunicacionLogica().darDependencias();
                    int color=0;
                    for(Dependencia dep: dependencias){%>
                    <tr class="tr_list"  <%if(color==0){%> style="background: white;" <%}else{%>style="background: #aad4ff;"<%}%>>
                        <td id="td_padd" style="text-align: left;"><%=dep.darNombre()%></td>
                        <td style="text-align: left;"><%=dep.darDescripcion()%></td>
                        <td><%=dep.getTotalCupos() %></td>
                        <td><%=dep.getTotalCuposDisponibles() %></td>     
                        <td style="width: 40px;" class="icono"><input type="image" onclick="javascript:venVerDep('<%=dep.darId()%>');"  id="btn_img" src="img/icon_see.png" title="Ver"></td>    
                        <td style="width: 40px;" class="icono"><input type="image" onclick="javascript:venDeleteDep('<%=dep.darId()%>');"  id="btn_img" src="img/icon_delete.png" title="Eliminar"></td>    
                        <form action="GestionDependencias" method="post">
                            <td style="width: 40px;" class="icono post" id="td_padd"><input type="image"  id="btn_img"  src="img/icon_update.png" title="Editar"></td>
                            <input type="hidden" id="idDependencia" name="idDependencia" value="<%=dep.darId()%>">
                            <input type="hidden" id="accion" name="accion" value="actualizarD">
                        </form>
                    </tr>
                   <% if(color==0){color=1;}else{color=0;}
                    }%>
                    <tr id="tr_final">
                        <td colspan="4">C.T : Cupos Totales<br>C.D : Cupos Disponibles</td>
                        <td colspan="3">N° Dependencias <%=dependencias.size() %></td>
                    </tr>
                </table>  
            </div>
         </section>
         <jsp:include page="footer.jsp"/>           
         <%} else{ response.sendRedirect("admin.jsp"); }%>
<!--       VERIFICA SI EL ADMIN ESTA EN UN PROCESO DE GESTION DE UNA DEPENDENCIA  -->
       <%
            Dependencia existeD=(Dependencia)session.getAttribute("depExiste");            
            Dependencia regDep2=(Dependencia)session.getAttribute("regDep2");
            Dependencia regDep1=(Dependencia)session.getAttribute("regDep1");
            Dependencia actualizarDep=(Dependencia)session.getAttribute("actualizarDep");

            if(existeD!=null || regDep1!=null){%>
                <script> 
                    venRegDep();
                </script>
            <%}        
           if(regDep2!=null){%>
               <script>           
                    venContRegDep();
             </script>
           <%}
           
           if(actualizarDep!=null){%>
                <script> 
                    venUpdateDep();
                </script>
           <%}
           %>
    </body>
</html>
