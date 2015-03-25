<%-- 
    Document   : formulariosMonitores
    Created on : 13/11/2014, 04:29:32 PM
    Author     : Andres
--%>

<%@page import="com.umariana.contratacionmonitores.controladores.GestionMonitores"%>
<%@page import="com.umariana.contratacionmonitores.logica.Monitor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="../js/modales.js" type="text/javascript"></script>
<link rel="stylesheet" href="../css/estilos.css" type="text/css" media="all">

<%String var=request.getParameter("var");
String mensaje = (String)session.getAttribute("mensaje");
if(var!=null){
    if(var.equals("verDependenciaM")){
        String identificacion=request.getParameter("identificacion");
        Monitor buscar=GestionMonitores.buscarMonitor(identificacion);%>
        <fieldset id="fls_mostrar">
            <legend>Informacion Dependencia</legend>  
            <br>
            <div><%=buscar.darDependencia().darNombre() %>, <p><%=buscar.darDependencia().darDescripcion() %></p></div>  
            <div>Jornada:     <%=buscar.darDependencia().darJornadas().get(0).getJornada() %></div>  
            <div>Horario:     <%=buscar.darDependencia().darJornadas().get(0).getHorarios().get(0).getDesde() %> - 
                              <%=buscar.darDependencia().darJornadas().get(0).getHorarios().get(0).getHasta() %></div>
                              <br>
            <div>Cupos Disponibles: <%=buscar.darDependencia().getTotalCuposDisponibles() %></div>                           
            <br>
            <div style="text-align: center;">           
                 <form action="" id="formRegDep" name="formRegDep" method="post" style="display: inline-block; ">
                     <input style="width: 100px;" type="button" onclick="javascript:hiddenThis(divVerDepMon);" id="btnForm" name="btnregDep"  value="Aceptar">
                </form>
            </div>
        </fieldset>
    <%}
    if(var.equals("verResultados")){
        String identificacion=request.getParameter("identificacion");
        Monitor buscar=GestionMonitores.buscarMonitor(identificacion);%>
        <fieldset id="fls_mostrar">
            <legend>Informacion Resultados</legend>  
            <br>
            <div style="text-align: center;">Resultados de la prueba.</div>
            <table id="tbl_ven">
                <tr>
                    <td>Identificación:</td><td><%=buscar.darIdentificacion() %></td>
                </tr>
                <tr>
                    <td>Nombres:</td><td><%=buscar.darPrimerNombre()%> <%=buscar.darPrimerApellido() %></td>
                </tr>
                <tr>
                    <td>Puntaje de la Prueba:</td><td><%=buscar.darPuntaje_prueba()%></td>
                </tr>
            </table>
            <br>
            <div style="text-align: center;">           
                 <form action="" id="formRegDep" name="formRegDep" method="post" style="display: inline-block; ">
                     <input style="width: 100px;" type="button" onclick="javascript:hiddenThis(divVerResMon);" id="btnForm" name="btnregDep"  value="Aceptar">
                </form>
            </div>
        </fieldset>
    <%}
    if(var.equals("eliminarMonitor")){
        String identificacion=request.getParameter("identificacion");
        Monitor eliminar=GestionMonitores.buscarMonitor(identificacion);%>
        <fieldset id="fls_mostrar">
            <legend>Eliminar Monitor</legend>  
            <div style="text-align: center;">¿Desea eliminar el monitor?</div>
            <br>
            <table id="tbl_ven">
                <tr>
                    <td>Identificación:</td><td><%=eliminar.darIdentificacion() %></td>
                </tr>
                <tr>
                    <td>Nombres:</td><td><%=eliminar.darPrimerNombre()%><%if(eliminar.darSegundoNombre()!=null){%> <%=eliminar.darSegundoNombre() %><%}%></td>
                </tr>
                <tr>
                    <td></td><td><%=eliminar.darPrimerApellido() %><%if(eliminar.darSegundoApellido()!=null){%> <%=eliminar.darSegundoApellido() %><%}%></td>
                </tr>
            </table>
            <br>
            <div style="text-align: center;">
                <form action="GestionMonitores" id="formRegDep" name="formRegDep" method="post" style="display: inline-block;">
                  <input style="width: 100px;" type="submit"  id="btnForm" name="btnregDep"  value="Eliminar">
                  <input  id="accion" name="accion" type="hidden" value="eliminarMonitor"/>
                  <input  id="accion" name="identificacion" type="hidden" value="<%=eliminar.darIdentificacion() %>"/>
                </form>
                 <form action="" id="formRegDep" name="formRegDep" method="post" style="display: inline-block; ">
                     <input style="width: 100px;" type="button" onclick="javascript:hiddenThis(divDelMon);" id="btnForm" name="btnregDep"  value="Cancelar">
                </form>
            </div>
        </fieldset>
    <%}
}%>
