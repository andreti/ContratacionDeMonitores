<%-- 
    Document   : admin
    Created on : 24/09/2014, 11:21:07 AM
    Author     : CocoSoft
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
    //Monitor monitor=monitor =(Monitor) session.getAttribute("monitor");
    //Estudiante estudiante = (Estudiante)session.getAttribute("estudiante");
    Administrador admin = (Administrador)session.getAttribute("admin");
    String mensaje = (String)session.getAttribute("mensaje");
    Estudiante eliminar = (Estudiante)session.getAttribute("eliminar");
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
                        <li><a href="index.jsp" >Inicio</a></li>
                        <li><a href="#">Pruebas</a></li> 
                        <li><a href="dependencia.jsp">Dependencias</a></li>
                        <li><a href="estudiante.jsp">Estudiantes</a></li>
                        <%if(admin==null) {%>
                            <li><a href="admin.jsp" style="background:#56a2ff;height: 32px;">Entrar</a></li>
                        <%}else if(admin!=null){%>
                            <li><a href="#" style="background:#56a2ff; text-decoration: underline;height: 32px;">Salir</a></li>
                        <%}%>
                    </ul>
                </nav>
            </div>     
        </header>      
        <section>
            <div id="contenedor">
            <%if(admin==null) {%>
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
            <%}else if(admin!=null) {%>           
            <label>Administrador : <%=admin.darNombre() %></label><br>  
            <form action="ContratacionMonitoresServlet" method="POST">
                <input type="submit"  value="Cerrar Sesion">
                <input type="hidden" id="accion" name="accion" value="cerrar">
            </form><br>   
            <div>
                <%if(mensaje!=null){%>
                     <h4 style="color: black;"><%=mensaje%></h4>
                <%}%>
                <%if(eliminar!= null ){%>
                <div id="div_eliminar">
                    <form action="ContratacionMonitoresServlet" method="POST">
                        <h2>Seguro que desea eliminar el registro del estudiante:</h2>
                        <p>Nombre: <%=eliminar.darPrimerNombre() %> <%=eliminar.darPrimerApellido() %></p>
                        <p>Identificado: <%=eliminar.darIdentificacion() %></p>
                        <select id="select_eliminar" name="select_eliminar">
                        <option value="si">Si</option>
                        <option value="no">No</option>
                        </select>
                        <input type="submit" id="btn_enviar" value="Aceptar">
                        <input type="hidden" id="accion" name="accion" value="confirmarEliminar">
                    </form>
                </div>
                <%}%>
                <table align="center" border="1">
                    <tr>
                        <td>
                            <label>Buscar Estudiantes</label>
                            <form action="ContratacionMonitoresServlet" method="POST">
                                <label>Identificacion</label>
                                <input type="text" id="txt_identificacion" name="txt_identificacion">
                                <input type="submit" id="btn_enviar" value="Enviar">
                                <input type="hidden" id="accion" name="accion" value="buscar">
                            </form>
                        </td>
                    </tr>
                </table>
            </div><br>
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
            <%
            ArrayList<Aspirante> aspirantes = ContratacionMonitoresServlet.cm.darAspirantes(); %>
            <table align="center" border="1">
                <th  colspan="13"><h3>Lista de Aspirantes</h3></th>  
                <tr style="background: black; color: white;">     
                    <td>Identificación</td>
                    <td>Codigo</td>
                    <td>Nombres</td>
                    <td>Apellidos</td>
                    <td>Semestre</td>
                    <td>E.M</td>
                    <td>Promedio Acum.</td>
                    <td>Postulaciones</td>
                    <td>Res. Prueba</td>
                    <td>Res. Entrevista</td>
                    <td>Prom. Prueba</td>
                </tr>
                <%for(Aspirante asp: aspirantes){%>
                <tr>
                    <td><%=asp.darIdentificacion() %></td>
                    <td><%=asp.darCodigo() %></td>
                    <td><%=asp.darPrimerNombre() %> <%=asp.darSegundoNombre() %> </td>
                    <td><%=asp.darPrimerApellido() %> <%=asp.darSegundoApellido() %></td>                 
                    <td><%=asp.darSemestreActual() %></td>
                    <td><%=asp.darEstadoMatricula() %></td>
                    <td><%=asp.darPromedioAcumulado() %></td>
                    
                    <%if(asp.darPostulaciones().size()>0){%>
                   <td> 
                       <select>
                        <%for(Postulacion post: asp.darPostulaciones()){%>
                            <option value="<%=post.darIdDependencia() %>"><%=post.darIdDependencia() %></option>
                        <% }%>
                        </select>
                    </td>
                    <%}else{%>
                    <td>N/A</td>
                    <%}%>
                    
                    <td>0</td>
                    <td>0</td>
                    <td>0</td>
                    <form action="ContratacionMonitoresServlet" method="post">
                        <td><input type="submit" value="Eliminar"></td>
                        <input type="hidden" id="identificacion" name="identificacion" value="<%=asp.darIdentificacion() %>">
                        <input type="hidden" id="accion" name="accion" value="eliminar">
                    </form>
                    <td><a href="#">Seleccionar</a></td>
                </tr>
               <%} %>
           </table><br>
           <table align="center" border="1">
                <th  colspan="10"><h3>Lista de Monitores</h3></th>  
                <tr style="background: black; color: white;">     
                    <td>Identificación</td>
                    <td>Codigo</td>
                    <td>Nombres</td>
                    <td>Apellidos</td>
                    <td>Semestre</td>
                    <td>E.M</td>
                    <td>Promedio Acum.</td>
                    <td>Dependencia</td>
                </tr>
                <%
                ArrayList<Monitor> monitores = ContratacionMonitoresServlet.cm.darMonitores();
                for(Monitor mon: monitores){%>
                   <tr>
                    <td><%=mon.darIdentificacion() %></td>
                    <td><%=mon.darCodigo() %></td>
                    <td><%=mon.darPrimerNombre() %> <%=mon.darSegundoNombre() %> </td>
                    <td><%=mon.darPrimerApellido() %> <%=mon.darSegundoApellido() %></td>                 
                    <td><%=mon.darSemestreActual() %></td>
                    <td><%=mon.darEstadoMatricula() %></td>
                    <td><%=mon.darPromedioAcumulado() %></td>
                    <td><%=mon.darDependencia().darNombre() %></td>
                    <form action="ContratacionMonitoresServlet" method="post">
                        <td><input type="submit" value="Eliminar"></td>
                        <input type="hidden" id="identificacion" name="identificacion" value="<%=mon.darIdentificacion() %>">
                        <input type="hidden" id="accion" name="accion" value="eliminar">
                    </form>
                    <td><a href="#">Modificar</a></td>
                </tr>
               <%}%>
           </table>
            <%}else {
                response.sendRedirect("index.jsp");
            }%> 
        </div>
        </section>  
        <footer>
            <a href="#">CocoSoft</a>
        </footer>
    </body>
</html>

