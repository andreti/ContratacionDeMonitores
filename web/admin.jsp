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

<%
    session.setAttribute("ubicacionPage", "admin.jsp");
    Administrador admin = (Administrador) session.getAttribute("admin");
    String mensaje = (String) session.getAttribute("mensaje");
    Estudiante eliminar = (Estudiante) session.getAttribute("eliminar");
%>
<!DOCTYPE html5>
<html>
    <head>
        <jsp:include page="head.jsp" />
        <script src="js/validaciones.js"></script>
        <title>Administrador</title>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <section>
            <div id="contenedor">
                <%if (mensaje != null) {%>
                <div id="div_mensaje">
                    <label><%=mensaje%></label>
                </div>
                <%}%>    
                <%if (admin == null) {%>
                <div>
                    <table>
                        <tr>
                            <td>
                                <form action="GestionAdministrador" method="POST" autocomplete="off" >
                                    <div style="width: 350px;  text-align: center; background: #bfdef8; padding: 10px; margin: 0 auto 0;"> 
                                        <label style=" margin: 0 auto 0;">Ingreso Para Administrador</label>
                                        <table>
                                            <tr style="height: 10px;">
                                                <td >Usuario</td>
                                                <td><input type="text" id="txt_usuario" name="txt_usuario" required></td>
                                            </tr>
                                            <tr style="height: 10px;">
                                                <td>Contraseña</td>
                                                <td><input type="password" id="txt_contrasena" name="txt_contrasena" required></td>
                                                <td rowspan="2" style="vertical-align: top;"><input style="width: 30px; height: 30px; " class="icono"  type="image" id="btn_img" src="img/icon_entrar_admin.png" title="Ingresar"> </td>
                                            </tr>
                                        </table>
                                    </div>
                                    <input type="hidden" id="accion" name="accion" value="entrar">                      
                                </form>
                            </td>
                        </tr>
                    </table>
                </div>  
                <%} else if (admin != null) {%>           
                <label>Administrador : <%=admin.darNombre()%></label><br>  
                <a href="#" onclick="animarFormulario();">Nuevo Administrador</a>
                <div id="divFormularioAdmin">
                    <form action="GestionAdministrador" method="post" autocomplete="off">
                        <input type="text" id="txt_nombre_completo" name="txt_nombre_completo" placeholder="Nombre completo" maxlength="100" class="requerid" required> <br/>
                        <input type="text" id="txt_nick" name="txt_nick" maxlength="20" placeholder="Usuario o Nick" onblur="validarNick()" class="requerid" required><br/>
                        <label class="msgForms" id="mensajeNick"></label>
                        <input type="password" id="txt_pass1" name="txt_pass1" maxlength="20" placeholder="Contraseña" class="requerid passwordForm" onkeydown="disableCopyPaste(this);" onkeyup="disableCopyPaste(this)" onkeypress="disableCopyPaste(this)" ondrop="noArrastrar(event)" required><br/>
                        <input type="password" id="txt_pass2" name="txt_pass2" maxlength="20" placeholder="Repite tu contraseña" class="requerid passwordForm" onkeydown="disableCopyPaste(this);" onkeypress="disableCopyPaste(this)" onkeyup="disableCopyPaste(this)" ondrop="noArrastrar(event)"  required><br/>
                        <label class="msgForms" id="mensajeFormulario"></label>
                        <div id="antiRobotDiv">
                            <label id="antiRobot" class="antiRobotClass"></label><input id="txtAntiRobot" onblur="validarAntiRobot(this)" placeholder="Digita el resultado" class="requerid antiRobotClass">
                        </div>
                        <label class="msgForms" id="mensajeAntirobot"></label>
                        <input type="submit" onclick="return validarFormularioAdmin();" value="Registrar" class="btn_enviar"><br/>
                        <input type="hidden" id="accion" name="accion" value="registrar">

                    </form>
                </div>
                <form action="GestionAdministrador" method="post" class="icono">
                    <div style="width: 70px; height: 70px; float: right;" >
                        <input class="iconoGrande" type="image" id="btn_img" src="img/icon_salir_admin.png" title="Salir">
                    </div>
                    <input type="hidden" id="accion" name="accion" value="cerrar">
                </form> 
                <div>      
                    <%if (eliminar != null) {%>
                    <div id="div_eliminar">
                        <form action="ContratacionMonitoresServlet" method="POST">
                            <h2>Seguro que desea eliminar el registro del estudiante:</h2>
                            <p>Nombre: <%=eliminar.darPrimerNombre()%> <%=eliminar.darPrimerApellido()%></p>
                            <p>Identificado: <%=eliminar.darIdentificacion()%></p>
                            <select id="select_eliminar" name="select_eliminar">
                                <option value="si">Si</option>
                                <option value="no">No</option>
                            </select>
                            <input type="submit" id="btn_enviar" value="Aceptar">
                            <input type="hidden" id="accion" name="accion" value="confirmarEliminar">
                        </form>
                    </div>
                    <%}%>
                    <%
                        Aspirante aspiBusc = (Aspirante) session.getAttribute("aspiranteBuscado");
                        Monitor monitorBusc = (Monitor) session.getAttribute("monitorBuscado");
                        Estudiante estudianteBusc = (Estudiante) session.getAttribute("estudianteBuscado");
                        if (aspiBusc != null || monitorBusc != null || estudianteBusc != null) {%>
                    <%if (aspiBusc != null) {%>
                    <div id="div_datos">
                        <table class="table_datos_est" id="table_datos_est">
                            <th colspan="2" style="text-align: center;" cellpadding="0" cellspacing="0">Datos del Aspirante</th>
                            <tr style="background: #8bbbfd;">
                                <td>Nombres</td>
                                <td><%=aspiBusc.darPrimerNombre()%> <%=aspiBusc.darSegundoNombre()%></td>
                            </tr>
                            <tr style="background: #bfdef8;">
                                <td>Apellidos</td>
                                <td><%=aspiBusc.darPrimerApellido()%> <%=aspiBusc.darSegundoApellido()%></td>
                            </tr>
                            <tr style="background: #8bbbfd;">
                                <td>Identicacion</td>
                                <td><%=aspiBusc.darIdentificacion()%></td>
                            </tr>
                            <tr style="background: #bfdef8;">
                                <td>Semestre</td>
                                <td><%=aspiBusc.darSemestreActual()%></td>
                            </tr>
                            <tr style="background: #8bbbfd;">
                                <td>Estado Matricula</td>
                                <td><%=aspiBusc.darEstadoMatricula()%></td>
                            </tr>
                            <tr style="background: #bfdef8;">
                                <td>Promedio Acumulado</td>
                                <td><%=aspiBusc.darPromedioAcumulado()%></td>
                            </tr>
                            <tr style="background: #8bbbfd;">
                                <td>Postulaciones</td>
                                <td>
                                    <%if (aspiBusc.darPostulaciones().size() > 0) {%>
                                    <div>
                                        <ul style="list-style: none;">
                                            <%for (Postulacion post : aspiBusc.darPostulaciones()) {%>       
                                            <li>► <%=post.getDependencia().darId()%></li>  
                                                <% }%>
                                        </ul>  
                                    </div>    
                                    <%} else {%>
                                    N/A
                                    <%}%>
                                </td>
                            </tr>
                        </table>                 
                    </div>   
                    <%} else if (monitorBusc != null) {%>
                    <div id="div_datos">
                        <table class="table_datos_est" id="table_datos_est">
                            <th colspan="2" style="text-align: center;" cellpadding="0" cellspacing="0">Datos del Monitor</th>
                            <tr style="background: #8bbbfd;">
                                <td>Nombres</td>
                                <td><%=monitorBusc.darPrimerNombre()%> <%=monitorBusc.darSegundoNombre()%></td>
                            </tr>
                            <tr style="background: #bfdef8;">
                                <td>Apellidos</td>
                                <td><%=monitorBusc.darPrimerApellido()%> <%=monitorBusc.darSegundoApellido()%></td>
                            </tr>
                            <tr style="background: #8bbbfd;">
                                <td>Identicacion</td>
                                <td><%=monitorBusc.darIdentificacion()%></td>
                            </tr>
                            <tr style="background: #bfdef8;">
                                <td>Semestre</td>
                                <td><%=monitorBusc.darSemestreActual()%></td>
                            </tr>
                            <tr style="background: #8bbbfd;">
                                <td>Estado Matricula</td>
                                <td><%=monitorBusc.darEstadoMatricula()%></td>
                            </tr>
                            <tr style="background: #bfdef8;">
                                <td>Promedio Acumulado</td>
                                <td><%=monitorBusc.darPromedioAcumulado()%></td>
                            </tr>
                            <tr style="background: #8bbbfd;">
                                <td>Monitoria en</td>
                                <td><%=monitorBusc.darDependencia().darNombre()%></td>
                            </tr>
                        </table>                 
                    </div>
                    <%} else if (estudianteBusc != null) {%>
                    <div id="div_datos">
                        <table class="table_datos_est" id="table_datos_est">
                            <th colspan="2" style="text-align: center;" cellpadding="0" cellspacing="0">Datos del Estudiante</th>
                            <tr style="background: #8bbbfd;">
                                <td>Nombres</td>
                                <td><%=estudianteBusc.darPrimerNombre()%> <%=estudianteBusc.darSegundoNombre()%></td>
                            </tr>
                            <tr style="background: #bfdef8;">
                                <td>Apellidos</td>
                                <td><%=estudianteBusc.darPrimerApellido()%> <%=estudianteBusc.darSegundoApellido()%></td>
                            </tr>
                            <tr style="background: #8bbbfd;">
                                <td>Identicacion</td>
                                <td><%=estudianteBusc.darIdentificacion()%></td>
                            </tr>
                            <tr style="background: #bfdef8;">
                                <td>Semestre</td>
                                <td><%=estudianteBusc.darSemestreActual()%></td>
                            </tr>
                            <tr style="background: #8bbbfd;">
                                <td>Estado Matricula</td>
                                <td><%=estudianteBusc.darEstadoMatricula()%></td>
                            </tr>
                            <tr style="background: #bfdef8;">
                                <td>Promedio Acumulado</td>
                                <td><%=estudianteBusc.darPromedioAcumulado()%></td>
                            </tr>
                            <tr style="background: #8bbbfd;">
                                <td></td>
                                <td><%if (estudianteBusc.darSemestreActual() >= 3) {
                                        if (estudianteBusc.darPromedioAcumulado() >= 3.5) {%>
                                    <label>Desea registrarse en el Sistema de Contratacion de Monitores ?</label>  
                                    <form action="ContratacionMonitoresServlet" method="POST">
                                        <select id="select_registrar" name="select_registrar">
                                            <option value="si">Si</option>
                                            <option value="no">No</option>
                                        </select>
                                        <input type="hidden" id="accion" name="accion" value="reg_estu">
                                        <input type="submit"  value="Registrar">
                                    </form>
                                    <%} else {%>
                                    <h4>No puedes postularte como aspirante porque tiene un promedio de notas menor a 3.5</h4>
                                    <%}
                                    } else {%>
                                    <h4>Para registrarte como aspirante debes estar en tercer semestre o superior</h4>
                                    <%}%>  </td>
                            </tr>
                        </table>                 
                    </div> 
                    <%}%>
                    </table>
                    <%}%>
                </div><br>      
                <%}%> 
            </div>
        </section>  
        <jsp:include page="footer.jsp"/>
    </body>
</html>

