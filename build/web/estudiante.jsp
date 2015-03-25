<%-- 
    Document   : estudiante
    Created on : 25/09/2014, 10:21:17 AM
    Author     : CocoSoft
--%>

<%@page import="com.umariana.contratacionmonitores.controladores.GestionAspirantes"%>
<%@page import="com.umariana.contratacionmonitores.controladores.GestionDependencias"%>
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
    session.setAttribute("ubicacionPage", "estudiante.jsp");
    
    Aspirante aspirante =(Aspirante) session.getAttribute("aspirante");
    Monitor monitor = monitor =(Monitor) session.getAttribute("monitor");
    Estudiante estudiante = (Estudiante)session.getAttribute("estudiante");
    Administrador admin = (Administrador)session.getAttribute("admin");
    String mensaje = (String)session.getAttribute("mensaje");
    
    
%>

<!DOCTYPE html5>
<html>
    <head>
        <jsp:include page="head.jsp" />
        <title>Contratacion Monitores</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <jsp:include page="divModal.jsp" /> 
        <section>
            <div id="contenedor" >                
                <%if(mensaje!=null){%>
                <div id="div_mensaje">
                    <label><%=mensaje%></label>
                </div>
                    <%session.removeAttribute("mensaje");
                }%>
                 <%if(aspirante==null && monitor==null && estudiante==null){%>
                   <div style="border: 1px solid black; width: 80%; height: auto; padding: 5px; display: inline-block;">  
                   </div>
                   <div style="width: 70px; height: 70px; float: right;" >
                       <label class="iconoGrande" ><input  type="image" onclick="javascript:venIngresoEstudiantes()"  id="btn_img" src="img/icon_entrar.png" title="Ingresar"></label>
                   </div>
                     
               <%}else { %>
               <form action="GestionSesionDeEstudiantes" method="post" class="icono">
                    <div style="width: 70px; height: 70px; float: right;" >
                        <input class="iconoGrande" type="image" id="btn_img" src="img/icon_salir.png" title="Salir">
                    </div>
                    <input type="hidden" id="accion" name="accion" value="cerrar">
                </form>
               <%}%>
                 <%if(aspirante!= null){%>
                 <div id="div_datos">
                     <table class="table_datos_est" id="table_datos_est">
                         <th colspan="2" style="text-align: center;" cellpadding="0" cellspacing="0">Datos del Aspirante</th>
                         <tr style="background: #8bbbfd;">
                             <td>Nombres</td>
                             <td><%=aspirante.darPrimerNombre() %> <%=aspirante.darSegundoNombre()%></td>
                         </tr>
                         <tr style="background: #bfdef8;">
                             <td>Apellidos</td>
                             <td><%=aspirante.darPrimerApellido() %> <%=aspirante.darSegundoApellido() %></td>
                         </tr>
                         <tr style="background: #8bbbfd;">
                             <td>Identicacion</td>
                             <td><%=aspirante.darIdentificacion() %></td>
                         </tr>
                         <tr style="background: #bfdef8;">
                             <td>Semestre</td>
                             <td><%=aspirante.darSemestreActual() %></td>
                         </tr>
                         <tr style="background: #8bbbfd;">
                             <td>Estado Matricula</td>
                             <td><%=aspirante.darEstadoMatricula() %></td>
                         </tr>
                         <tr style="background: #bfdef8;">
                             <td>Promedio Acumulado</td>
                             <td><%=aspirante.darPromedioAcumulado() %></td>
                         </tr>
                         <tr style="background: #8bbbfd;">
                             <td>Postulaciones</td>
                             <td>
                                 <%ArrayList<Postulacion> pst=GestionAspirantes.buscarPostulacionesAspirante(aspirante.darIdentificacion()); 
                                    if(!pst.isEmpty()){%>
                                     <div style=" padding: 5px;">
                                         <%
                                        if(pst!=null){
                                            for(Postulacion p: pst){%> 
                                            <div style="margin: 0px; margin-bottom: 3px; border-bottom: 1px solid gray;">
                                                <div style="display: inline-block; height: auto; margin-bottom: 10px; "><label style="margin-left: 20px; color: black;">► <%=p.getDependencia().darNombre() %><br><%=p.getDependencia().darJornadas().get(0).getJornada() %>, <%=p.getDependencia().darJornadas().get(0).getHorarios().get(0).toString() %></label></div>
                                                <div style="display: inline-block; float: right; height: 40px;"><a class="icono_small" href="#" onclick="javascript:venDelPostEstuBd('<%=p.getIdHorario()%>');"><img src="img/icon_delete.png" title="Quitar"/></a></div>
                                                </div>
                                            <%}           
                                        }%>
                                     </div>
                                <%}else{%>
                                 N/A
                                <%}%>
                             </td>
                         </tr>
                     </table>                 
                 </div>             
                    <%}
                    else if(monitor!= null){%>
                    <div id="div_datos">
                     <table class="table_datos_est" id="table_datos_est">
                         <th colspan="2" style="text-align: center;" cellpadding="0" cellspacing="0">Datos del Monitor</th>
                         <tr style="background: #8bbbfd;">
                             <td>Nombres</td>
                             <td><%=monitor.darPrimerNombre() %> <%=monitor.darSegundoNombre()%></td>
                         </tr>
                         <tr style="background: #bfdef8;">
                             <td>Apellidos</td>
                             <td><%=monitor.darPrimerApellido() %> <%=monitor.darSegundoApellido() %></td>
                         </tr>
                         <tr style="background: #8bbbfd;">
                             <td>Identicacion</td>
                             <td><%=monitor.darIdentificacion() %></td>
                         </tr>
                         <tr style="background: #bfdef8;">
                             <td>Semestre</td>
                             <td><%=monitor.darSemestreActual() %></td>
                         </tr>
                         <tr style="background: #8bbbfd;">
                             <td>Estado Matricula</td>
                             <td><%=monitor.darEstadoMatricula() %></td>
                         </tr>
                         <tr style="background: #bfdef8;">
                             <td>Promedio Acumulado</td>
                             <td><%=monitor.darPromedioAcumulado() %></td>
                         </tr>
                         <tr style="background: #8bbbfd;">
                             <td>Monitoria en</td>
                             <td><%=monitor.darDependencia().darNombre() %></td>
                         </tr>
                     </table>                 
                    </div>
                         
                    <%}
                    else if(estudiante!= null){%>  
                    <div id="div_datos">
                     <table class="table_datos_est" id="table_datos_est">
                         <th colspan="2" style="text-align: center;" cellpadding="0" cellspacing="0">Datos del Estudiante</th>
                         <tr style="background: #8bbbfd;">
                             <td>Nombres</td>
                             <td><%=estudiante.darPrimerNombre() %><%if(estudiante.darSegundoNombre()!=null){%> <%=estudiante.darSegundoNombre()%><%}%></td>
                         </tr>
                         <tr style="background: #bfdef8;">
                             <td>Apellidos</td>
                             <td><%=estudiante.darPrimerApellido() %><%if(estudiante.darSegundoApellido()!=null){%> <%=estudiante.darSegundoApellido() %><%}%></td>
                         </tr>
                         <tr style="background: #8bbbfd;">
                             <td>Identicacion</td>
                             <td><%=estudiante.darIdentificacion() %></td>
                         </tr>
                         <tr style="background: #bfdef8;">
                             <td>Semestre</td>
                             <td><%=estudiante.darSemestreActual() %></td>
                         </tr>
                         <tr style="background: #8bbbfd;">
                             <td>Estado Matricula</td>
                             <td><%if(estudiante.darEstadoMatricula().equals("1")){%>Matriculado<%}else{%>Inactivo<%}%></td>
                         </tr>
                         <tr style="background: #bfdef8;">
                             <td>Promedio Acumulado</td>
                             <td><%=estudiante.darPromedioAcumulado() %></td>
                         </tr>
                         <tr style=" height: 80px;">
                            <td colspan="2">
                            <%
                            if(estudiante.darEstadoMatricula().equals("1")){
                                if(estudiante.darSemestreActual()>= 3){
                                    if(estudiante.darPromedioAcumulado()>=3.5){%>
                                    <div style="text-align: center; width: 100%; margin: 10px;"><label >Postulaciones</label></div>
                                     <%ArrayList<Dependencia> dependencias=ContratacionMonitoresServlet.darComunicacionLogica().darDependencias(); 
                                        ArrayList<Postulacion> postulaciones=(ArrayList<Postulacion>)session.getAttribute("listaPostulacionesTemp");
                                        if(!dependencias.isEmpty()){%>
                                        <div style="margin-left: 50px; "><%
                                            for(Dependencia d: dependencias){%>
                                            <div style="width: 400px; border-bottom: 1px solid white; padding: 5px;  min-height:40px;"><div style=" display: inline-block; "><label style=" display: block;"><%=d.darNombre() %></label>
                                                <%
                                                boolean postulado=false;
                                                if(postulaciones!=null){
                                                    for(Postulacion p: postulaciones){
                                                        if(d.darId()==p.getDependencia().darId()){%>
                                                        <label style="margin-left: 20px; color: #ddd4d4;">Jornada: <%=p.getDependencia().darJornadas().get(0).getJornada() %> Horario: <%=p.getDependencia().darJornadas().get(0).getHorarios().get(0).getDesde() %> - <%=p.getDependencia().darJornadas().get(0).getHorarios().get(0).getDesde() %></label></div>
                                                            <div style="display: inline-block;  height: 40px; float: right;"><a class="icono_small" href="#" onclick="javascript:venDelPostEstu('<%=d.darId()%>');"><img src="img/icon_delete.png" title="Quitar"/></a></div>
                                                            </div>
                                                            <%postulado=true;
                                                            }
                                                        }           
                                                }
                                                if(!postulado){%> 
                                                    </div>
                                                    <div style="display: inline-block;  height: 40px; float: right;"><a class="icono_small" href="#" onclick="javascript:venPostEstu('<%=d.darId()%>');"><img src="img/icon_add.png" title="Postularme"/></a></div>
                                                    </div>
                                                <%}       
                                            }%>
                                        </div>
                                       <%}
                                       if(postulaciones!=null){if(!postulaciones.isEmpty()){%>
                                        <div style="text-align: center; width: 100%;  height: 70px; padding: 10px;"><label class="iconoGrande" style="width: 70px;"><input  type="image" onclick="javascript:venRegEst()"  id="btn_img" src="img/icon_registrar_estudiante.png" title="Postularse"></label></div>
                                         <%}}
                                    }else{%><h4 class="msg_error">No puedes postularte como aspirante porque su promedio de notas es menor a 3.5</h4><%}
                                }else{%><h4 class="msg_error">Para registrarte como aspirante debes estar en tercer semestre o superior</h4><%}
                            }else{%><h4 class="msg_error">Para registrarte como aspirante tu matricula debe estar activada</h4><%}%>  
                            </td>
                         </tr>
                     </table>                 
                    </div>             
                    <%}
                    else if(admin!=null) {
                        response.sendRedirect("admin.jsp");
                    } %>        
            </div>
        </section>
        <jsp:include page="footer.jsp"/>
    </body>
</html>

